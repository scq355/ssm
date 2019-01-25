package com.sangame.datafilter.common.persistence.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sangame.datafilter.common.persistence.model.FilterPermission;

@Repository
public interface FilterPermissionMapper extends BaseMapper {
	
	public List<FilterPermission> getPerListByIds(List<Long> ids);
	
}
