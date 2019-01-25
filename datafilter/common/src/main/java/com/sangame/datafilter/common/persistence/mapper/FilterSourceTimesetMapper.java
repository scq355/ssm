package com.sangame.datafilter.common.persistence.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface FilterSourceTimesetMapper extends BaseMapper {
	public int deleteBySourceId(Long id);
}
