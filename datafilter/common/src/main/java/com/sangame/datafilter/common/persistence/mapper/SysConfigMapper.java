package com.sangame.datafilter.common.persistence.mapper;

import com.sangame.datafilter.common.persistence.model.SysConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysConfigMapper extends BaseMapper {
	
	/**
	 * 批量删除
	 * @param Ids
	 */
	public int deleteBatchRules(List<Integer> Ids);

	/**
	 * 获得全部
	 * @param configKey
	 * @return
	 */
	public List<SysConfig> getAll(String configKey);
}
