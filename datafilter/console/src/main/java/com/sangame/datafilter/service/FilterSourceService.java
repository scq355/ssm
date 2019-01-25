package com.sangame.datafilter.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangame.datafilter.common.persistence.model.FilterSource;
import com.sangame.datafilter.common.persistence.model.FilterSourceTimeset;
import com.sangame.datafilter.common.persistence.mapper.FilterSourceMapper;
import com.sangame.datafilter.common.persistence.mapper.FilterSourceTimesetMapper;
import com.sangame.datafilter.common.util.PageUtil;
import com.sangame.datafilter.constant.ConsoleConstant;
import com.sangame.datafilter.redis.RedisCacheKey;
import com.sangame.datafilter.redis.RedisCacheTime;
import com.sangame.datafilter.redis.RedisHelper;

@Service  
public class FilterSourceService {
	
	@Autowired
    private FilterSourceMapper filterSourceMapper;
	@Autowired
	private FilterSourceTimesetMapper sourceTimesetMapper;
    
    public int insert(FilterSource filterSource) {
		return filterSourceMapper.insert(filterSource);
	}
    
    public int update(FilterSource filterSource) {
		return filterSourceMapper.update(filterSource);
	}
	
	public int delete(Long id) {
		return filterSourceMapper.delete(id);
	}

	public FilterSource getById(Long id) {
		return filterSourceMapper.getById(id);
	}
	
	public int countByParm(FilterSource filterSource) {
		Map<String, Object> parm = new HashMap<String, Object>();
		return filterSourceMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return filterSourceMapper.countByParm(parm);
	}
	
	/**
	*根据参数获取对象列表
	**/
	public List<FilterSource> getListByParm(Map<String, Object> parm) {
		return filterSourceMapper.getListByParm(parm, null);
	}

	public PageUtil getListByParm(Map<String, Object> parm, int pageStart, Integer pageSize) {
		PageUtil pageUtil = new PageUtil(pageStart, pageSize);
		int count = filterSourceMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if (count != 0) {
			List<Object> list = filterSourceMapper.getListByParm(parm, pageUtil);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	/**
	 * 根据来源key获取其审核规则
	 * @param key
	 * @return
	 */
	public Integer getAuditRuleByKey(String key){
		//先根据缓存判断
		Integer auditRule = getAuditRuleByKeyFromRedis(key);
		if (auditRule != null)
			return auditRule;
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("sourceKey", key);
		List<FilterSource> sourceList = filterSourceMapper.getListByParm(parm, null);
		//如果查找不到该key的来源，则返回空
		if (sourceList == null || sourceList.size() < 1) return null;
		FilterSource source = sourceList.get(0);
		auditRule = source.getAuditRule();
		//如果不是【动态审核】，则返回其审核规则设置
		if (auditRule != ConsoleConstant.AuditRuleType.DYNAMIC_CHECK.getValue()) {
			RedisHelper.setObjectByJSON(RedisCacheKey.FILTER_SOURCE + key, source , RedisCacheTime.ONE_MONTH_STORE);
			return auditRule;
		}
		//如果是动态审核，则获取其时间设置，设置之外的时间为先发后审
		parm = new HashMap<String, Object>();
		parm.put("sourceId", source.getId());
		List<FilterSourceTimeset> timesetList = sourceTimesetMapper.getListByParm(parm, null);
		source.setTimesetList(timesetList);
		RedisHelper.setObjectByJSON(RedisCacheKey.FILTER_SOURCE + key, source , RedisCacheTime.ONE_MONTH_STORE);
		return getAuditRuleByTimeset(timesetList);
	}
	
	/**
	 * 根据时间设置返回审核规则，时间设置内的为先审后发
	 * @param timesetList
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Integer getAuditRuleByTimeset(List<FilterSourceTimeset> timesetList) {
		if (timesetList != null && timesetList.size() > 0) {
			Date d = new Date();
			int hours = d.getHours();
			for (FilterSourceTimeset timeset : timesetList) {
				Integer start = timeset.getStartHour();
				Integer end = timeset.getEndHour();
				if (start != null && end != null && hours >= start && hours < end) {
					return ConsoleConstant.AuditRuleType.CHECK_FIRST.getValue();
				}
			}
		}
		return ConsoleConstant.AuditRuleType.SEND_FIRST.getValue();
	}
	
	/**
	 * 根据redis缓存获取审核规则
	 * @param key
	 * @return
	 */
	public Integer getAuditRuleByKeyFromRedis(String key) {
		FilterSource source = (FilterSource)RedisHelper.getObjectByClass(RedisCacheKey.FILTER_SOURCE + key, FilterSource.class);
		if (source == null) return null;
		Integer auditRule = source.getAuditRule();
		if (auditRule != ConsoleConstant.AuditRuleType.DYNAMIC_CHECK.getValue())
			return auditRule;
		List<FilterSourceTimeset> timesetList = source.getTimesetList();
		return getAuditRuleByTimeset(timesetList);
	}
}
