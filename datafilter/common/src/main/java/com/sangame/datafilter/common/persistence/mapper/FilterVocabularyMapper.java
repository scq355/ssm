package com.sangame.datafilter.common.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sangame.datafilter.common.persistence.model.FilterVocabulary;

@Repository
public interface FilterVocabularyMapper extends BaseMapper {
	
	public int batchUpdate(@Param("ids")String[] ids,@Param("type") Integer type);
	
	public void batchInsert(List<FilterVocabulary> filterVocabularyList);
}
