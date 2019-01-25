package com.qs.p2p.dao.impl;

import com.qs.p2p.dao.AbstractDAO;
import com.qs.p2p.dao.SysLogDao;
import com.qs.p2p.model.SysLog;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by scq on 2018-01-29 14:38:59
 */
@Repository(value = "sysLogDao")
public class SysLogDaoImpl extends AbstractDAO<SysLog, Integer> implements SysLogDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	protected SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Override
	protected String getNamespace() {
		return "sysLogDao";
	}
}
