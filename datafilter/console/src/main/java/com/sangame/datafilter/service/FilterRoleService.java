package com.sangame.datafilter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangame.datafilter.common.persistence.model.FilterRole;
import com.sangame.datafilter.common.persistence.mapper.FilterRoleMapper;
import com.sangame.datafilter.common.util.PageUtil;

@Service  
public class FilterRoleService {
	
	@Autowired
    private FilterRoleMapper filterRoleMapper;
    
    public int insert(FilterRole filterRole) {
		return filterRoleMapper.insert(filterRole);
	}
    
    public int update(FilterRole filterRole) {
		return filterRoleMapper.update(filterRole);
	}
	
	public int delete(Long id) {
		return filterRoleMapper.delete(id);
	}

	public FilterRole getById(Long id) {
		return filterRoleMapper.getById(id);
	}
	
	public int countByParm(FilterRole filterRole) {
		Map<String, Object> parm = new HashMap<String, Object>();
		return filterRoleMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return filterRoleMapper.countByParm(parm);
	}
	
	/**
	*根据参数获取对象列表
	**/
	public List<FilterRole> getListByParm(Map<String, Object> parm) {
		return filterRoleMapper.getListByParm(parm, null);
	}

	public PageUtil getListByParm(Map<String, Object> parm, int pageStart, Integer pageSize) {
		PageUtil pageUtil = new PageUtil(pageStart, pageSize);
		int count = filterRoleMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if (count != 0) {
			List<Object> list = filterRoleMapper.getListByParm(parm, pageUtil);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
}
