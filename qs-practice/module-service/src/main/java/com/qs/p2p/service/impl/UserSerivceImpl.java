package com.qs.p2p.service.impl;

import com.qs.p2p.dao.DAO;
import com.qs.p2p.dao.UserDao;
import com.qs.p2p.model.User;
import com.qs.p2p.service.UserService;
import com.qs.p2p.service.AbstractBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nudtn on 2017/4/30.
 */
@Service(value = "userService")
public class UserSerivceImpl extends AbstractBO<User, Integer> implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	protected DAO<User, Integer> getDAO() {
		return userDao;
	}
}
