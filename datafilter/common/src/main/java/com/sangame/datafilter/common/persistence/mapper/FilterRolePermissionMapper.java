package com.sangame.datafilter.common.persistence.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface FilterRolePermissionMapper extends BaseMapper {
	public List<Long> getPerIdListByRoleId(Long roleId);
}
