/**
	 * 判断是否超过24小时最大提交量
	 *
	 * @param blackUser
	 * @return
	 */
	public boolean isBeyondMaxSubmit(FilterBlackUser blackUser) {
		String userValueFromCache = RedisHelper.getString(RedisCacheKey.DAY_SUBMIT_COUNT_MAX + blackUser.getUserId());
		List<FilterBlackUser> userList = new ArrayList<FilterBlackUser>();
		userList.add(blackUser);
		Object userObject = JSON.toJSON(userList);
		String userString = userObject + "";		//把user格式设置为JSONString类型便于后期调用
		blackUser.setCreator("系统");
		blackUser.setModifier("系统");
		if (StringUtils.isBlank(userValueFromCache)) {
			Map<String, String> userCacheInfo = new HashMap<String, String>();
			userCacheInfo.put(RedisCacheKey.DAY_SUBMIT_COUNT_MAX + blackUser.getUserId(), "1:0:0:0:0:0");		//最大提交量：最大提交量超出次数：敏感词：敏感词超出次数：非法词：非法次超出次数

			log.info( "缓存中之前不存在的用户信息：" + userCacheInfo.toString());
			RedisHelper.setString(RedisCacheKey.DAY_SUBMIT_COUNT_MAX + blackUser.getUserId(), "1:0:0:0:0:0", RedisCacheTime.ONE_MONTH_STORE);
			log.info("============用户信息：" + blackUser.toString());
			blackUser.setCreator("系统");
			blackUser.setModifier("系统");
			if (StringUtils.isNoneBlank(blackUser.getUserId() + "")) {		//根据IP或者userId冻结用户
				filterBlackUserService.batchFreezeBlackUserByUserId(userString);
				log.info("执行插入黑名单用户操作============================");
			} else {
				filterBlackUserService.batchFreezeBlackUserByIP(userString);
			}
			String getInfoFromLog = RedisHelper.getString(RedisCacheKey.DAY_SUBMIT_COUNT_MAX + blackUser.getUserId());
			log.info("刚刚存入缓存的用户信息：" + getInfoFromLog);
		} else {
			log.info("缓存中之前存在的用户信息：" + userValueFromCache);
			String[] info = userValueFromCache.split(":");
//			SysConfig sysConfig = sysConfigMapper.getById(38L);
			SysConfig sysConfig = getConfigByKey(RedisCacheKey.DAY_SUBMIT_COUNT_MAX);
			log.info("系统配置：" + sysConfig.toString());
			int maxSubmit = Integer.parseInt(info[0]);
			int sysConfigValue = Integer.parseInt(sysConfig.getConfigValue());
			int beyondCount = Integer.parseInt(info[1]);
			if (++maxSubmit > sysConfigValue) {		//冻结 -> 插入 -> 数据清零
				log.info("用户的对象格式：" + userObject.toString());
				log.info("用户的json格式：" + userString);
				filterBlackUserService.batchFreezeBlackUserByUserId(userString);		//此处要使用jsonString格式
				if (Integer.parseInt(info[1]) == 2) {
					Date newEndTime = DateUtil.addDay(blackUser.getBlackEndTime(), 3);	//冻3天
					beyondCount++;
					blackUser.setBlackEndTime(newEndTime);
				} else if (Integer.parseInt(info[1]) == 3) {
					Date newEndTime = DateUtil.addDay(blackUser.getBlackEndTime(), 30);	//冻3天
					beyondCount++;
					blackUser.setBlackEndTime(newEndTime);
				} else if (Integer.parseInt(info[1]) == 4) {
					Date newEndTime = DateUtil.parse(ConsoleConstant.FOREVER);	//永久冻结
					blackUser.setBlackEndTime(newEndTime);
					info[1] = "0";
				}
				filterBlackUserService.update(blackUser);
				RedisHelper.setString(RedisCacheKey.DAY_SUBMIT_COUNT_MAX + blackUser.getUserId(), 0 + ":" + beyondCount + ":" + info[2] + ":" + info[3] + ":" + info[4] + ":" + info[5], RedisCacheTime.ONE_MONTH_STORE);
				return true;
			} else {	//缓存数据记录+1
				RedisHelper.setString(RedisCacheKey.DAY_SUBMIT_COUNT_MAX + blackUser.getUserId(), maxSubmit + ":" + ++beyondCount + ":" + info[2]+ ":" + info[3] + ":" + info[4] + ":" + info[5], RedisCacheTime.ONE_MONTH_STORE);
				String getInfo = RedisHelper.getString(blackUser.getUserId() + "");
				log.info("用户记录加一之后的缓存数据：" + getInfo);
				return false;
			}
		}
		return false;
	}

	/**
	 * 判断是否超过24小时最大敏感词次数
	 *
	 * @param blackUser
	 * @return
	 */
	public boolean isBeyondMaxSensitiveWord(FilterBlackUser blackUser) {
		String userValueFromCache = RedisHelper.getString(RedisCacheKey.DAY_SENSITIVE_WORD_MAX + blackUser.getUserId());
		List<FilterBlackUser> userList = new ArrayList<FilterBlackUser>();
		userList.add(blackUser);
		Object userObject = JSON.toJSON(userList);
		String userString = userObject + "";		//把user格式设置为JSONString类型便于后期调用
		blackUser.setCreator("系统");
		blackUser.setModifier("系统");
		if (StringUtils.isBlank(userValueFromCache)) {
			Map<String, String> userCacheInfo = new HashMap<String, String>();
			userCacheInfo.put(RedisCacheKey.DAY_SENSITIVE_WORD_MAX + blackUser.getUserId(), "0:0:1:0:0:0");		//最大提交量：最大提交量超出次数：铭感次：铭感次超出次数：非法词：非法次超出次数

			log.info( "缓存中之前不存在的用户信息：" + userCacheInfo.toString());
			RedisHelper.setString(RedisCacheKey.DAY_SENSITIVE_WORD_MAX + blackUser.getUserId(), "0:0:1:0:0:0", RedisCacheTime.ONE_MONTH_STORE);
			log.info("============用户信息：" + blackUser.toString());
			blackUser.setCreator("系统");
			blackUser.setModifier("系统");
			if (StringUtils.isNoneBlank(blackUser.getUserId() + "")) {		//根据IP或者userId冻结用户
				filterBlackUserService.batchFreezeBlackUserByUserId(userString);
				log.info("执行插入黑名单用户操作============================");
			} else {
				filterBlackUserService.batchFreezeBlackUserByIP(userString);
			}
			String getInfoFromLog = RedisHelper.getString(RedisCacheKey.DAY_SENSITIVE_WORD_MAX + blackUser.getUserId());
			log.info("刚刚存入缓存的用户信息：" + getInfoFromLog);
		} else {
			log.info("缓存中之前存在的用户信息：" + userValueFromCache);
			String[] info = userValueFromCache.split(":");
//			SysConfig sysConfig = sysConfigMapper.getById(38L);
			SysConfig sysConfig = getConfigByKey(RedisCacheKey.DAY_SENSITIVE_WORD_MAX);
			log.info("系统配置：" + sysConfig.toString());
			int maxSubmit = Integer.parseInt(info[2]);
			int sysConfigValue = Integer.parseInt(sysConfig.getConfigValue());
			int beyondCount = Integer.parseInt(info[3]);
			if (++maxSubmit > sysConfigValue) {		//冻结 -> 插入 -> 数据清零
				log.info("用户的对象格式：" + userObject.toString());
				log.info("用户的json格式：" + userString);
				filterBlackUserService.batchFreezeBlackUserByUserId(userString);		//此处要使用jsonString格式
				if (Integer.parseInt(info[3]) == 1) {
					Date newEndTime = DateUtil.addHour(blackUser.getBlackEndTime(), 12);	//冻12h
					beyondCount++;
					blackUser.setBlackEndTime(newEndTime);
				} else if (Integer.parseInt(info[3]) == 2) {
					Date newEndTime = DateUtil.addDay(blackUser.getBlackEndTime(), 3);	//冻3D
					beyondCount++;
					blackUser.setBlackEndTime(newEndTime);
				} else if (Integer.parseInt(info[3]) == 3) {
					Date newEndTime = DateUtil.addDay(blackUser.getBlackEndTime(), 30);	//冻30d
					beyondCount++;
					blackUser.setBlackEndTime(newEndTime);
				}
				else if (Integer.parseInt(info[3]) == 4) {
					Date newEndTime = DateUtil.parse(ConsoleConstant.FOREVER);				//永久
					blackUser.setBlackEndTime(newEndTime);
					info[3] = "0";
				}
				filterBlackUserService.update(blackUser);
				RedisHelper.setString(RedisCacheKey.DAY_SENSITIVE_WORD_MAX + blackUser.getUserId(), info[0] + ":" +  info[1] + ":" + 0 + ":" + beyondCount + ":" + info[4] + ":" + info[5], RedisCacheTime.ONE_MONTH_STORE);
				return true;
			} else {	//缓存数据记录+1
				RedisHelper.setString(RedisCacheKey.DAY_SENSITIVE_WORD_MAX + blackUser.getUserId(), info[0] + ":" + info[1] + ":" + maxSubmit + ":" + ++beyondCount + ":" + info[4] + ":" + info[5], RedisCacheTime.ONE_MONTH_STORE);
				String getInfo = RedisHelper.getString(RedisCacheKey.DAY_SENSITIVE_WORD_MAX + blackUser.getUserId());
				log.info("用户记录加一之后的缓存数据：" + getInfo);
				return false;
			}
		}
		return false;
	}

	/**
	 * 判断24小时最大非法词次数
	 *
	 * @param blackUser
	 * @return
	 */
	public boolean isBeyondMaxIllegal(FilterBlackUser blackUser) {
		String userValueFromCache = RedisHelper.getString(RedisCacheKey.DAY_ILLEGAL_WORD_MAX + blackUser.getUserId());
		List<FilterBlackUser> userList = new ArrayList<FilterBlackUser>();
		userList.add(blackUser);
		Object userObject = JSON.toJSON(userList);
		String userString = userObject + "";		//把user格式设置为JSONString类型便于后期调用
		blackUser.setCreator("系统");
		blackUser.setModifier("系统");
		if (StringUtils.isBlank(userValueFromCache)) {
			Map<String, String> userCacheInfo = new HashMap<String, String>();
			userCacheInfo.put(blackUser.getUserId() + "", "0:0:0:0:1:0");		//最大提交量：最大提交量超出次数：铭感次：铭感次超出次数：非法词：非法次超出次数
			log.info( "缓存中之前不存在的用户信息：" + userCacheInfo.toString());
			RedisHelper.setString(RedisCacheKey.DAY_ILLEGAL_WORD_MAX + blackUser.getUserId(), "0:0:0:0:1:0", RedisCacheTime.ONE_MONTH_STORE);
			log.info("============用户信息：" + blackUser.toString());
			blackUser.setCreator("系统");
			blackUser.setModifier("系统");
			if (StringUtils.isNoneBlank(blackUser.getUserId() + "")) {		//根据IP或者userId冻结用户
				filterBlackUserService.batchFreezeBlackUserByUserId(userString);
				log.info("执行插入黑名单用户操作============================");
			} else {
				filterBlackUserService.batchFreezeBlackUserByIP(userString);
			}
			String getInfoFromLog = RedisHelper.getString(RedisCacheKey.DAY_ILLEGAL_WORD_MAX + blackUser.getUserId());
			log.info("刚刚存入缓存的用户信息：" + getInfoFromLog);
		} else {
			log.info("缓存中之前存在的用户信息：" + userValueFromCache);
			String[] info = userValueFromCache.split(":");
//			SysConfig sysConfig = sysConfigMapper.getById(38L);
			SysConfig sysConfig = getConfigByKey(RedisCacheKey.DAY_ILLEGAL_WORD_MAX);
			log.info("系统配置：" + sysConfig.toString());
			int maxSubmit = Integer.parseInt(info[4]);
			int sysConfigValue = Integer.parseInt(sysConfig.getConfigValue());
			int beyondCount = Integer.parseInt(info[5]);
			if (++maxSubmit > sysConfigValue) {        //冻结 -> 插入 -> 数据清零
				log.info("用户的对象格式：" + userObject.toString());
				log.info("用户的json格式：" + userString);
				filterBlackUserService.batchFreezeBlackUserByUserId(userString);        //此处要使用jsonString格式
				if (Integer.parseInt(info[5]) == 1) {
					Date newEndTime = DateUtil.addDay(blackUser.getBlackEndTime(), 3);    //冻3天
					beyondCount++;
					blackUser.setBlackEndTime(newEndTime);
				} else if (Integer.parseInt(info[5]) == 2) {
					Date newEndTime = DateUtil.addDay(blackUser.getBlackEndTime(), 7);    //冻3天
					beyondCount++;
					blackUser.setBlackEndTime(newEndTime);
				} else if (Integer.parseInt(info[5]) == 3) {
					Date newEndTime = DateUtil.addDay(blackUser.getBlackEndTime(), 30);    //冻3天
					beyondCount++;
					blackUser.setBlackEndTime(newEndTime);
				} else if (Integer.parseInt(info[5]) == 4) {
					Date newEndTime = DateUtil.parse(ConsoleConstant.FOREVER);				  //冻3天
					blackUser.setBlackEndTime(newEndTime);
					info[5] = "0";
				}
				filterBlackUserService.update(blackUser);
				RedisHelper.setString(RedisCacheKey.DAY_ILLEGAL_WORD_MAX + blackUser.getUserId(), info[0] + ":" + info[1] + ":" + info[2] + ":" + info[3] + ":" + 0 + ":" + beyondCount, RedisCacheTime.ONE_MONTH_STORE);
				return true;
			} else {    //缓存数据记录+1
				RedisHelper.setString(RedisCacheKey.DAY_ILLEGAL_WORD_MAX + blackUser.getUserId(), info[0] + ":" + info[1] + ":" + info[2] + ":" + info[3] + ":" + maxSubmit + ":" + ++beyondCount, RedisCacheTime.ONE_MONTH_STORE);
				String getInfo = RedisHelper.getString(RedisCacheKey.DAY_ILLEGAL_WORD_MAX + blackUser.getUserId());
				log.info("用户记录加一之后的缓存数据：" + getInfo);
				return false;
			}
		}
		return false;
	}