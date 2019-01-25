package com.qs.p2p.dao.impl;

import com.qs.p2p.dao.AbstractDAO;
import com.qs.p2p.dao.TaskSchedulerDao;
import com.qs.p2p.model.TaskScheduler;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by scq on 2018-01-19 11:05:06
 */
@Repository(value = "taskSchedulerDao")
public class TaskSchedulerDaoImpl extends AbstractDAO<TaskScheduler, Integer> implements TaskSchedulerDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	protected SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Override
	protected String getNamespace() {
		return "taskSchedulerDao";
	}
}
