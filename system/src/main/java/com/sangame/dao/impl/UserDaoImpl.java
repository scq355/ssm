package com.sangame.dao.impl;

import com.calanger.common.dao.AbstractDAO;
import com.sangame.dao.UserDao;
import com.sangame.model.User;
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
