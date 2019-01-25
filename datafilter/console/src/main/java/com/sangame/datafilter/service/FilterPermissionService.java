package com.sangame.datafilter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangame.datafilter.common.persistence.model.FilterPermission;
import com.sangame.datafilter.common.persistence.mapper.FilterPermissionMapper;
import com.sangame.datafilter.common.util.PageUtil;

@Service  
public class FilterPermissionService {
	
	@Autowired
    private FilterPermissionMapper filterPermissionMapper;
    
    public int insert(FilterPermission filterPermission) {
		return filterPermissionMapper.insert(filterPermission);
	}
    
    public int update(FilterPermission filterPermission) {
		return filterPermissionMapper.update(filterPermission);
	}
	
	public int delete(Long id) {
		return filterPermissionMapper.delete(id);
	}

	public FilterPermission getById(Long id) {
		return filterPermissionMapper.getById(id);
	}
	
	public int countByParm(FilterPermission filterPermission) {
		Map<String, Object> parm = new HashMap<String, Object>();
		return filterPermissionMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return filterPermissionMapper.countByParm(parm);
	}
	
	/**
	*根据参数获取对象列表
	**/
	public List<FilterPermission> getListByParm(Map<String, Object> parm) {
		return filterPermissionMapper.getListByParm(parm, null);
	}

	public PageUtil getListByParm(Map<String, Object> parm, int pageStart, Integer pageSize) {
		PageUtil pageUtil = new PageUtil(pageStart, pageSize);
		int count = filterPermissionMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if (count != 0) {
			List<Object> list = filterPermissionMapper.getListByParm(parm, pageUtil);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	/**
	 * 根据ids获取权限列表
	 */
	public List<FilterPermission> getPerListByIds(List<Long> ids){
		if (ids == null || ids.size() < 1) return null; 
		return filterPermissionMapper.getPerListByIds(ids);
	}
}
