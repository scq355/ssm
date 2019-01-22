package com.sangame.service.impl;

import com.calanger.common.bo.AbstractBO;
import com.calanger.common.dao.DAO;
import com.sangame.dao.UserDao;
import com.sangame.model.User;
import com.sangame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nudtn on 2017/4/30.
 */
@Service("userService")
public class UserSerivceImpl extends AbstractBO<User, Integer> implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	protected DAO<User, Integer> getDAO() {
		return userDao;
	}
}
