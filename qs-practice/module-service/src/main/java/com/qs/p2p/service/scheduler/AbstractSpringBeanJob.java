package com.qs.p2p.service.scheduler;

import com.qs.p2p.service.TaskSchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by scq on 2018-01-19 10:48:43
 */
public abstract class AbstractSpringBeanJob implements InitializingBean {
	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractSpringBeanJob.class);

	@Autowired
	private TaskSchedulerService taskSchedulerService;
	
	protected abstract String getModule();

	protected String getTaskName() {
		return this.getClass().getSimpleName() + ".doJob()";
	}

	protected abstract void doJob();

	public void run() {
		if (taskSchedulerService.canRunOnTheComputer(getModule(), SchedulerUtils.getLocalHostName())) {
			taskSchedulerService.startInvoke(getModule(), SchedulerUtils.getLocalHostName(), getTaskName(), new Date());
			try {
				LOGGER.info("doJob start...");
				doJob();
				LOGGER.info("doJob end...");
				taskSchedulerService.completeInvoke(getModule(), SchedulerUtils.getLocalHostName(), getTaskName(), new Date() , true);
			} catch (Exception e) {
				taskSchedulerService.completeInvoke(getModule(), SchedulerUtils.getLocalHostName(), getTaskName(), new Date() , false);
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 注册本机
		// catch住异常，可能bean初始化时数据库没启动，注册失败不应该导致bean的初始化失败
		try {
			taskSchedulerService.registerComputer(getModule(), SchedulerUtils.getLocalHostName(), SchedulerUtils.getLocalHostIps());
		} catch (Exception e) {
			LOGGER.warn("registerComputer fail", e);
		}
	}
}
