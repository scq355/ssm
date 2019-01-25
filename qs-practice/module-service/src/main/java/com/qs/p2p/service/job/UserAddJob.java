package com.qs.p2p.service.job;

import com.qs.p2p.dao.UserDao;
import com.qs.p2p.model.User;
import com.qs.p2p.service.scheduler.AbstractSpringBeanJob;
import com.qs.p2p.service.utils.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by scq on 2018-01-19 11:45:03
 */
@Service(value = "UserAddJob")
public class UserAddJob extends AbstractSpringBeanJob {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAddJob.class);

	@Autowired
	private UserDao userDao;

	@Override
	protected String getModule() {
		return this.getClass().getName();
	}

	@Override
	protected void doJob() {
		LOGGER.info("添加一个用户开始...");
		try {
			User userVO = new User();
			userVO.setUserName("jobTestUser" + new Date().getTime());
			userVO.setIpLogin(IPUtils.getRandomIp());
			userVO.setPassword("testpasswordscq355");
			userVO.setDateLogin(new Date());
			userVO.setAvatar("head");
			userVO.setPhoneNumber("18710755118");
			userVO.setStatus(1);

			userDao.insert(userVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("添加一个用户结束...");
	}
}
