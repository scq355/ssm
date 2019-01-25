package com.sangame.datafilter.common.persistence.mapper;

import org.apache.ibatis.annotations.Param;


/**
 * 查询系统版本信息
 */
public interface SysVersionMapper {
	
	/**
	 * 查询当前版本
	 * @return
	 */
	public String findVersion();
	
	/**
	 * 插入版本号
	 * @param version
	 */
	public void insert(String version);

	/**
	 * 更新版本
	 * @param version
	 */
	public void update(String version);
	
	/**
	 * 执行sql语句
	 * @param sql
	 */
	public void execute(@Param("sql") String sql);
	
}
