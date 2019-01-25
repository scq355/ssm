package com.sangame.datafilter.common.persistence.mapper;

import com.sangame.datafilter.common.util.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BaseMapper {

	<T> int insert(T t);
	
	<T> int update(T t);
	
	<T> int delete(Long id);
	
	<T> int batchDelete(String[] ids);
	
	<T> T getById(Long id);
	
	int countByParm(@Param("parm") Map<String, Object> parm);
	
	<T> List<T> getListByParm(@Param("parm") Map<String, Object> parm, @Param("page") PageUtil pageUtil);
	
	List<Map<String, Object>> getMapListByParm(@Param("parm") Map<String, Object> parm, @Param("page") PageUtil pageUtil);
	
}
