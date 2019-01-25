package com.sangame.datafilter.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sangame.datafilter.common.persistence.model.FilterSource;
import com.sangame.datafilter.common.persistence.model.FilterSourceTimeset;
import com.sangame.datafilter.common.util.PageUtil;
import com.sangame.datafilter.constant.ConsoleConstant;
import com.sangame.datafilter.constant.ConsoleConstant.AuditRuleType;
import com.sangame.datafilter.redis.RedisCacheKey;
import com.sangame.datafilter.redis.RedisCacheTime;
import com.sangame.datafilter.redis.RedisHelper;
import com.sangame.datafilter.constant.PageConstant;
import com.sangame.datafilter.service.FilterSourceService;
import com.sangame.datafilter.service.FilterSourceTimesetService;
import com.sangame.datafilter.util.Render;

@Controller
@RequestMapping(value="/source", method=RequestMethod.GET)
public class SourceController {

	private final static Logger Log = LoggerFactory.getLogger(SourceController.class);
	
	@Autowired
	private FilterSourceService sourceService;
	@Autowired
	private FilterSourceTimesetService timesetService;
	
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String index(ModelMap model) {
		return PageConstant.SOURCE_INDEX_PAGE;
	}
	
	@ResponseBody
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	public Render list(
			@RequestParam(value = "pageStart", required = false, defaultValue = "1") Integer pageStart,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("deleteFlag", ConsoleConstant.deleteFlagType.FALSE.getValue());
		PageUtil page = sourceService.getListByParm(parm, pageStart, pageSize);
		return new Render(true, "获取列表数据成功！", page.getTotalRecords(), page.getList());
	}
	
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public String edit(ModelMap model,
			@RequestParam(value = "id", required = true) Long id) {
		FilterSource source = sourceService.getById(id);
		model.addAttribute("source", source);
		//如果是动态审核则添加时间设置数据
		if (source.getAuditRule() != null && source.getAuditRule() == AuditRuleType.DYNAMIC_CHECK.getValue()) {
			List<FilterSourceTimeset> timesetList = timesetService.getTimesetListBySourceId(id);
			if (timesetList != null)
				model.addAttribute("timesetList", timesetList);
		}
		return PageConstant.SOURCE_EDIT_PAGE;
	}
	
	@ResponseBody
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public Render update(@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "sourceKey", required = true) String sourceKey,
			@RequestParam(value = "sourceName", required = true) String sourceName,
			@RequestParam(value = "auditRule", required = true) Integer auditRule,
			@RequestParam(value = "startHour", required = false) Integer[] startHour,
			@RequestParam(value = "endHour", required = false) Integer[] endHour
			) {
		FilterSource source = new FilterSource();
		source.setId(id);
		source.setSourceKey(sourceKey);
		source.setSourceName(sourceName);
		source.setAuditRule(auditRule);
		source.setModifier("");//修改人设置为空，拦截器进行自动处理
		sourceService.update(source);
		//把该来源之前的时间设置删除
		timesetService.deleteBySourceId(id);
		List<FilterSourceTimeset> timesetList = new ArrayList<FilterSourceTimeset>();
		if (startHour != null && endHour != null && startHour.length > 0 
				&& startHour.length == endHour.length) {
			//用循环批量添加，数量一般不会多，最多五条左右
			for (int i=0; i<startHour.length ; i++) {
				FilterSourceTimeset timeset = new FilterSourceTimeset();
				timeset.setSourceId(id);
				timeset.setStartHour(startHour[i]);
				timeset.setEndHour(endHour[i]);
				timesetService.insert(timeset);
				timesetList.add(timeset);
			}
		}
		source.setTimesetList(timesetList);
		RedisHelper.setObjectByJSON(RedisCacheKey.FILTER_SOURCE + sourceKey, source , RedisCacheTime.ONE_MONTH_STORE);
		return new Render(true, "更新成功！");
	}
}
