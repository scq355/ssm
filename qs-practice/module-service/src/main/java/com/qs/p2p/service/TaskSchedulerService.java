package com.qs.p2p.service;

import com.qs.p2p.model.TaskScheduler;

import java.util.Date;

/**
 * Created by scq on 2018-01-19 11:24:41
 */
public interface TaskSchedulerService extends BO<TaskScheduler, Integer> {
	boolean registerComputer(String module, String computerName, String[] ips);

	boolean canRunOnTheComputer(String module, String computerName);

	void startInvoke(String module, String computerName, String taskName, Date invokeTime);

	void completeInvoke(String module, String computerName, String taskName, Date completeTime, boolean success);
}
