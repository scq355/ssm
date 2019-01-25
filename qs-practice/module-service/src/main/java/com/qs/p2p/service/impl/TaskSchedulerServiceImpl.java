package com.qs.p2p.service.impl;

import com.google.common.base.Joiner;
import com.qs.p2p.dao.DAO;
import com.qs.p2p.dao.TaskSchedulerDao;
import com.qs.p2p.model.TaskScheduler;
import com.qs.p2p.service.AbstractBO;
import com.qs.p2p.service.TaskSchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by scq on 2018-01-19 11:25:07
 */
@Service(value = "taskSchedulerService")
public class TaskSchedulerServiceImpl extends AbstractBO<TaskScheduler, Integer> implements TaskSchedulerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskSchedulerServiceImpl.class);

	@Autowired
	private TaskSchedulerDao taskSchedulerDao;

	@Override
	protected DAO<TaskScheduler, Integer> getDAO() {
		return taskSchedulerDao;
	}

	@Override
	public boolean registerComputer(String module, String computerName, String[] ips) {
		try {
			TaskScheduler taskSchedulerVO = new TaskScheduler();
			taskSchedulerVO.setModuleName(module);
			taskSchedulerVO.setHostname(computerName);
			List<TaskScheduler> taskSchedulerList = taskSchedulerDao.find(taskSchedulerVO);
			if (taskSchedulerList.isEmpty()) {
				TaskScheduler taskSchedulerEntity = new TaskScheduler();
				taskSchedulerEntity.setModuleName(module);
				taskSchedulerEntity.setHostname(computerName);
				taskSchedulerEntity.setCreatedAt(new Date());
				taskSchedulerEntity.setUpdatedAt(new Date());
				taskSchedulerEntity.setEnabled(0);
				taskSchedulerEntity.setIpAddress(Joiner.on(",").join(ips));
				taskSchedulerDao.insert(taskSchedulerEntity);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	@Override
	public boolean canRunOnTheComputer(String module, String computerName) {
		try {
			LOGGER.info("canRunOnTheComputer start");
			TaskScheduler taskSchedulerVO = new TaskScheduler();
			taskSchedulerVO.setModuleName(module);
			taskSchedulerVO.setHostname(computerName);
			taskSchedulerVO.setEnabled(1);
			List<TaskScheduler> taskSchedulerList = taskSchedulerDao.find(taskSchedulerVO);
			return !taskSchedulerList.isEmpty();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		}

	}

	@Override
	public void startInvoke(String module, String computerName, String taskName, Date invokeTime) {

	}

	@Override
	public void completeInvoke(String module, String computerName, String taskName, Date completeTime, boolean success) {

	}
}
