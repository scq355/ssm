package com.sangame.datafilter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangame.datafilter.common.persistence.model.FilterRolePermission;
import com.sangame.datafilter.common.persistence.mapper.FilterRolePermissionMapper;
import com.sangame.datafilter.common.util.PageUtil;

@Service  
public class FilterRolePermissionService {
	
	@Autowired
    private FilterRolePermissionMapper filterRolePermissionMapper;
    
    public int insert(FilterRolePermission filterRolePermission) {
		return filterRolePermissionMapper.insert(filterRolePermission);
	}
    
    public int update(FilterRolePermission filterRolePermission) {
		return filterRolePermissionMapper.update(filterRolePermission);
	}
	
	public int delete(Long id) {
		return filterRolePermissionMapper.delete(id);
	}

	public FilterRolePermission getById(Long id) {
		return filterRolePermissionMapper.getById(id);
	}
	
	public int countByParm(FilterRolePermission filterRolePermission) {
		Map<String, Object> parm = new HashMap<String, Object>();
		return filterRolePermissionMapper.countByParm(parm);
	}
	
	public int countByParm(Map<String, Object> parm) {
		return filterRolePermissionMapper.countByParm(parm);
	}
	
	/**
	*根据参数获取对象列表
	**/
	public List<FilterRolePermission> getListByParm(Map<String, Object> parm) {
		return filterRolePermissionMapper.getListByParm(parm, null);
	}

	public PageUtil getListByParm(Map<String, Object> parm, int pageStart, Integer pageSize) {
		PageUtil pageUtil = new PageUtil(pageStart, pageSize);
		int count = filterRolePermissionMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		if (count != 0) {
			List<Object> list = filterRolePermissionMapper.getListByParm(parm, pageUtil);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	
	public List<Long> getPerIdListByRoleId(Long roleId) {
//		Map<String, Object> parm = new HashMap<String, Object>();
//		parm.put("role_id", roleId);
//		List<FilterRolePermission> rolePerList = filterRolePermissionMapper.getListByParm(parm, null);
//		if (rolePerList == null || rolePerList.size() < 1) return null;
		return filterRolePermissionMapper.getPerIdListByRoleId(roleId);
	}
	
}
