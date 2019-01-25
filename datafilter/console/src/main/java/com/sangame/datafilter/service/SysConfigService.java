package com.sangame.datafilter.service;

import com.sangame.datafilter.common.persistence.mapper.SysConfigMapper;
import com.sangame.datafilter.common.persistence.model.FilterBlackUser;
import com.sangame.datafilter.common.persistence.model.SysConfig;
import com.sangame.datafilter.common.util.DateUtil;
import com.sangame.datafilter.common.util.PageUtil;
import com.sangame.datafilter.constant.ConsoleConstant;
import com.sangame.datafilter.constant.ConsoleConstant.deleteFlagType;
import com.sangame.datafilter.redis.RedisCacheKey;
import com.sangame.datafilter.redis.RedisCacheTime;
import com.sangame.datafilter.redis.RedisHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysConfigService {

	private static final Logger log = LoggerFactory.getLogger(SysConfigService.class);

	@Autowired
	private SysConfigMapper sysConfigMapper;

	@Autowired
	private FilterBlackUserService filterBlackUserService;

	public int insert(SysConfig sysConfig) {
		return sysConfigMapper.insert(sysConfig);
	}

	public int update(SysConfig sysConfig) {
		return sysConfigMapper.update(sysConfig);
	}

	public int delete(Long id) {
		return sysConfigMapper.delete(id);
	}

	public SysConfig getById(Long id) {
		return sysConfigMapper.getById(id);
	}

	public int countByParm(SysConfig sysConfig) {
		Map<String, Object> parm = new HashMap<String, Object>();
		return sysConfigMapper.countByParm(parm);
	}

	public int countByParm(Map<String, Object> parm) {
		return sysConfigMapper.countByParm(parm);
	}

	/**
	 * 根据参数获取对象列表
	 * @param parm
	 * @return
	 */
	public List<SysConfig> getListByParm(Map<String, Object> parm) {
		return sysConfigMapper.getListByParm(parm, null);
	}

	/**
	 * @desc 批量删除
	 * @param ids
	 */
	public int deleteBatchRules(List<Integer> ids) {
		return sysConfigMapper.deleteBatchRules(ids);
	}

	/**
	 * @desc 获取全部
	 * @param configKey
	 * @return
	 */
	public List<SysConfig> getAll(String configKey) {
		return sysConfigMapper.getAll(configKey);
	}
	
	/**
	 * 根据配置关键字(configKey)查询配置(SysConfig)
	 * @param configKey
	 * @return
	 */
	public SysConfig getConfigByKey(String configKey) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("deleteFlag", deleteFlagType.FALSE.getValue());
		parm.put("configKey", configKey);
		List<SysConfig> list = sysConfigMapper.getListByParm(parm, null);
		if (list == null || list.size() < 1) return null;
		return list.get(0);
	}
	
	/**
	 * 根据配置关键字(configKey)查询配置值(configValue)
	 * @param configKey
	 * @return
	 */
	public String getConfigValueByKey(String configKey) {
		SysConfig sysConfig = this.getConfigByKey(configKey);
		if (sysConfig == null)
			return null;
		else
			return sysConfig.getConfigValue();
	}
	
	/**
	 * 判断是否超过24小时最大提交量
	 *
	 * @param blackUser
	 * @return
	 */
	public boolean isBeyondMaxSubmit(FilterBlackUser blackUser) {
		String dateStr = DateUtil.formatDate(new Date(), "yyyyMMdd");
		String userKey = RedisCacheKey.DAY_SUBMIT_COUNT_MAX + "." + dateStr + "." + blackUser.getUserId();		//拼接缓存key
		String userValueFromCache = RedisHelper.getString(userKey);							//查询缓存

		blackUser.setCreator("系统");
		blackUser.setModifier("系统");

		if (StringUtils.isBlank(userValueFromCache)) {
			RedisHelper.setString(userKey, "1", RedisCacheTime.ONE_DAY_STORE);		//一条用户信息存一天
		} else {
			String sysConfigKey = ConsoleConstant.SystemConfigKey.DAY_SUBMIT_COUNT_MAX.getValue();
			String sysConfigValue = RedisHelper.getString(sysConfigKey);

			if (StringUtils.isBlank(sysConfigValue)) {
				SysConfig sysConfig = getConfigByKey(sysConfigKey);
				RedisHelper.setString(sysConfigKey, sysConfig.getConfigValue(), RedisCacheTime.ONE_DAY_STORE); //存一天
				sysConfigValue = sysConfig.getConfigValue();
			}
			int intConfigValue = Integer.parseInt(sysConfigValue);
			int maxSubmit = Integer.parseInt(userValueFromCache);
			if (++maxSubmit > intConfigValue) {       										//冻结 -> 插入 -> 数据清零
				Date endTime = DateUtil.addDay(new Date(), 3);			//冻结日期
				blackUser.setBlackEndTime(endTime);
				if (blackUser.getBlackWay() == ConsoleConstant.BlackWhiteWay.USERID.getValue()) {//根据IP或者userId冻结用户
					Long id = filterBlackUserService.isBlackUserByUserId(blackUser.getUserId());
					if (id == null) {
						filterBlackUserService.insert(blackUser);
					} else {
						blackUser.setId(id);
						filterBlackUserService.update(blackUser);
					}
				} else {
					Long id = filterBlackUserService.isBlackUserByIP(blackUser.getIp());
					if (id == null) {
						filterBlackUserService.insert(blackUser);
					} else {
						blackUser.setId(id);
						filterBlackUserService.update(blackUser);
					}
				}
				RedisHelper.setString(userKey, "0", RedisCacheTime.ONE_DAY_STORE);
			} else {
				RedisHelper.setString(userKey,maxSubmit + "", RedisCacheTime.ONE_DAY_STORE);
			}
			return true;
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
		String dateStr = DateUtil.formatDate(new Date(), "yyyyMMdd");
		String userKey = RedisCacheKey.DAY_SENSITIVE_WORD_MAX + "." + dateStr + "." + blackUser.getUserId();		//拼接缓存key
		String userValueFromCache = RedisHelper.getString(userKey);							//查询缓存
		blackUser.setCreator("系统");
		blackUser.setModifier("系统");
		if (StringUtils.isBlank(userValueFromCache)) {
			RedisHelper.setString(userKey, "1", RedisCacheTime.ONE_DAY_STORE);		//一条用户信息存一天
		} else {
			String sysConfigKey = ConsoleConstant.SystemConfigKey.DAY_SENSITIVE_WORD_MAX.getValue();
			String sysConfigValue = RedisHelper.getString(sysConfigKey);

			if (StringUtils.isBlank(sysConfigValue)) {
				SysConfig sysConfig = getConfigByKey(sysConfigKey);
				RedisHelper.setString(sysConfigKey, sysConfig.getConfigValue(), RedisCacheTime.ONE_DAY_STORE); //存一天
				sysConfigValue = sysConfig.getConfigValue();
			}

			int intConfigValue = Integer.parseInt(sysConfigValue);
			int maxSensitive = Integer.parseInt(userValueFromCache);
			if (++maxSensitive > intConfigValue) {       										//冻结 -> 插入 -> 数据清零
				Date endTime = DateUtil.addHour(new Date(), 12);			//冻结日期
				blackUser.setBlackEndTime(endTime);
				blackUser.setBlackState(1);
				if (blackUser.getBlackWay() == ConsoleConstant.BlackWhiteWay.USERID.getValue()) {//根据IP或者userId冻结用户
					Long id = filterBlackUserService.isBlackUserByUserId(blackUser.getUserId());
					if (id == null) {
						filterBlackUserService.insert(blackUser);
					} else {
						blackUser.setId(id);
						filterBlackUserService.update(blackUser);
					}
				} else {
					Long id = filterBlackUserService.isBlackUserByIP(blackUser.getIp());
					if (id == null) {
						filterBlackUserService.insert(blackUser);
					} else {
						blackUser.setId(id);
						filterBlackUserService.update(blackUser);
					}
				}
				RedisHelper.setString(userKey, "0", RedisCacheTime.ONE_DAY_STORE);
			} else {
				RedisHelper.setString(userKey, maxSensitive + "", RedisCacheTime.ONE_DAY_STORE);
			}
			return true;
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
		String dateStr = DateUtil.formatDate(new Date(), "yyyyMMdd");
		String userKey = RedisCacheKey.DAY_ILLEGAL_WORD_MAX+ "." + dateStr + "." + blackUser.getUserId();		//拼接缓存key
		String userValueFromCache = RedisHelper.getString(userKey);							//查询缓存
		blackUser.setCreator("系统");
		blackUser.setModifier("系统");
		if (StringUtils.isBlank(userValueFromCache)) {
			RedisHelper.setString(userKey, "1", RedisCacheTime.ONE_DAY_STORE);		//一条用户信息存一天
		} else {
			String sysConfigKey = ConsoleConstant.SystemConfigKey.DAY_ILLEGAL_WORD_MAX.getValue();
			String sysConfigValue = RedisHelper.getString(sysConfigKey);
			if (StringUtils.isBlank(sysConfigValue)) {
				SysConfig sysConfig = getConfigByKey(sysConfigKey);
				RedisHelper.setString(sysConfigKey, sysConfig.getConfigValue(), RedisCacheTime.ONE_DAY_STORE); //存一天
				sysConfigValue = sysConfig.getConfigValue();
			}
			int intConfigValue = Integer.parseInt(sysConfigValue);
			int maxSensitive = Integer.parseInt(userValueFromCache);
			if (++maxSensitive > intConfigValue) {       										//冻结 -> 插入 -> 数据清零
				Date endTime = DateUtil.addDay(new Date(), 3);			//冻结日期
				blackUser.setBlackEndTime(endTime);
				if (blackUser.getBlackWay() == ConsoleConstant.BlackWhiteWay.USERID.getValue()) {//根据IP或者userId冻结用户
					Long id = filterBlackUserService.isBlackUserByUserId(blackUser.getUserId());
					if (id == null) {
						filterBlackUserService.insert(blackUser);
					} else {
						blackUser.setId(id);
						filterBlackUserService.update(blackUser);
					}
				} else {
					Long id = filterBlackUserService.isBlackUserByIP(blackUser.getIp());
					if (id == null) {
						filterBlackUserService.insert(blackUser);
					} else {
						blackUser.setId(id);
						filterBlackUserService.update(blackUser);
					}
				}
				RedisHelper.setString(userKey, "0", RedisCacheTime.ONE_DAY_STORE);
			} else {
				RedisHelper.setString(userKey, maxSensitive + "", RedisCacheTime.ONE_DAY_STORE);
			}
			return true;
		}
		return false;
	}

	public PageUtil getListByParm(Map<String, Object> parm, int pageStart, Integer pageSize) {
		PageUtil pageUtil = new PageUtil(pageStart, pageSize);
		int count = sysConfigMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if (count != 0) {
			List<Object> list = sysConfigMapper.getListByParm(parm, pageUtil);
			log.info("===========Service:" + list.toString());
			pageUtil.setList(list);
		}
		return pageUtil;
	}
}
