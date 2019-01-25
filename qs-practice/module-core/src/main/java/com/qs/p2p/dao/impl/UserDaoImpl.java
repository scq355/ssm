package com.qs.p2p.dao.impl;

import com.qs.p2p.dao.AbstractDAO;
import com.qs.p2p.dao.UserDao;
import com.qs.p2p.model.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by scq on 2018-01-09 10:26:24
 */
@Repository(value = "userDao")
public class UserDaoImpl extends AbstractDAO<User, Integer> implements UserDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	protected SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Override
	protected String getNamespace() {
		return "userDao";
	}
}
