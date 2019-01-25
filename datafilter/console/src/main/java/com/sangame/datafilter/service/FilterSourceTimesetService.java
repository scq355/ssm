package com.sangame.datafilter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangame.datafilter.common.persistence.model.FilterSourceTimeset;
import com.sangame.datafilter.common.persistence.mapper.FilterSourceTimesetMapper;
import com.sangame.datafilter.common.util.PageUtil;

@Service  
public class FilterSourceTimesetService {
	
	@Autowired
    private FilterSourceTimesetMapper filterSourceTimesetMapper;
    
    public int insert(FilterSourceTimeset filterSourceTimeset) {
		return filterSourceTimesetMapper.insert(filterSourceTimeset);
	}
    
    public int update(FilterSourceTimeset filterSourceTimeset) {
		return filterSourceTimesetMapper.update(filterSourceTimeset);
	}
	
	public int delete(Long id) {
		return filterSourceTimesetMapper.delete(id);
	}

	public FilterSourceTimeset getById(Long id) {
		return filterSourceTimesetMapper.getById(id);
	}
	
	public int countByParm(FilterSourceTimeset filterSourceTimeset) {
		Map<String, Object> parm = new HashMap<String, Object>();
		return filterSourceTimesetMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return filterSourceTimesetMapper.countByParm(parm);
	}
	
	/**
	*根据参数获取对象列表
	**/
	public List<FilterSourceTimeset> getListByParm(Map<String, Object> parm) {
		return filterSourceTimesetMapper.getListByParm(parm, null);
	}

	public PageUtil getListByParm(Map<String, Object> parm, int pageStart, Integer pageSize) {
		PageUtil pageUtil = new PageUtil(pageStart, pageSize);
		int count = filterSourceTimesetMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if (count != 0) {
			List<Object> list = filterSourceTimesetMapper.getListByParm(parm, pageUtil);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	/**
	 * 根据来源ID获取时间设置
	 * @param sourceId
	 * @return
	 */
	public List<FilterSourceTimeset> getTimesetListBySourceId(Long sourceId) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm = new HashMap<String, Object>();
		parm.put("sourceId", sourceId);
		return filterSourceTimesetMapper.getListByParm(parm, null);
	}
	
	/**
	 * 根据来源ID删除时间设置
	 * 
	 */
	public int deleteBySourceId(Long sourceId) {
		return filterSourceTimesetMapper.deleteBySourceId(sourceId);
	}
	
}
