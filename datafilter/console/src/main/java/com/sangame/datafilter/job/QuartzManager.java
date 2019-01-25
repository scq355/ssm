package com.sangame.datafilter.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by admin on 2017/5/12.
 */
public class QuartzManager {
    private static final Logger LOG = LoggerFactory.getLogger(QuartzManager.class);

    private Scheduler scheduler;

    public QuartzManager() {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            scheduler= schedulerFactory.getScheduler();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            System.exit(-1);
        }
    }

    /**
     * 关闭任务
     * @throws SchedulerException
     */
    public void shutdown() throws SchedulerException {
        scheduler.shutdown();
    }

    /**
     * 添加任务
     * @param job
     * @param trigger
     * @throws SchedulerException
     */
    public void scheduleJob(JobDetail job, Trigger trigger) throws SchedulerException {
        scheduler.scheduleJob(job, trigger);
    }
}
