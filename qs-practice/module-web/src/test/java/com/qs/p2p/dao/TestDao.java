package com.qs.p2p.dao;

import com.qs.p2p.model.SysLog;
import com.qs.p2p.model.TaskScheduler;
import com.qs.p2p.model.User;
import com.qs.p2p.service.SysLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by scq on 2018-01-10 15:29:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/spring-mybatis.xml", "classpath:spring/spring-service.xml"})
public class TestDao {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TaskSchedulerDao taskSchedulerDao;
	@Autowired
	private SysLogDao sysLogDao;
	@Autowired
	private SysLogService sysLogService;

	@Test
	public void testFind() {
		List<User> userList = userDao.find();
		System.out.println(userList.toString());

		List<TaskScheduler> taskSchedulerList = taskSchedulerDao.find();
		System.out.println(taskSchedulerList + "=============");

		AggregateResult result = userDao.aggregate(new String[] {"max"}, new String[] {"id"});
		System.out.println(result.get("max_id").toString() + "******");
	}

	@Test
	public void testIn() {
		User user = new User();
		List<Integer> idList = new ArrayList<>();
		idList.add(12);
		user.and(Expressions.in("id", idList));
		List<User> userList = userDao.find(user);
		System.out.println(userList);
	}

	/**
	 * 测试添加
	 */
	@Test
	public void testAdd() {
		try {
			User userVO = new User();
			userVO.setUserName("jobTestUser" + new Date().getTime());
			userVO.setIpLogin(InetAddress.getLocalHost().getHostName());
			userVO.setPassword(Math.random() * 1000L + "");
			userVO.setDateLogin(new Date());
			userVO.setPhoneNumber("18710755118");
			userVO.setAvatar("head");
			userVO.setStatus(1);

			userDao.insert(userVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testTask() {
		TaskScheduler taskScheduler = new TaskScheduler();
		taskScheduler.setIpAddress("localhost");
		taskScheduler.setEnabled(1);
		taskScheduler.setUpdatedAt(new Date());
		taskScheduler.setModuleName("testModel");
		taskScheduler.setHostname("scq");
		taskScheduler.setCreatedAt(new Date());
		taskSchedulerDao.insert(taskScheduler);
	}

	@Test
	public void testSysLog() {
		List<SysLog> sysLogList = sysLogDao.find();
		System.out.println(sysLogList.toString());
		SysLog sysLogVO = new SysLog();
		sysLogVO.setName("新闻修改保存");
		OrderBy orderBy = new OrderBy().add("id", false);
		List<SysLog> sysLogs = sysLogDao.find(sysLogVO, orderBy,1, 3);
		System.out.println(sysLogs);
		AggregateResult aggregateResult = sysLogService.aggregate(sysLogVO, new String[] {"SUM"}, new String[] {"module_type"});
		BigDecimal res = aggregateResult.getBigDecimal("sum_module_type");
		System.out.println(res.doubleValue());
	}

}
