package com.sangame.job.core;

import com.sangame.job.user.UserJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author scq
 * @Date 2017/5/13.
 */
@Component
public class JobManager {
    protected static final Logger LOG = LoggerFactory.getLogger(JobManager.class);

    public static final String JOB_GROUP = "default_job_group";                 // 默认组
    public static final String USER_JOB_GROUP = "user_job_group";          // 用户默认组

    public void startJob() {
        try {
            //黑名单定时解冻
            Scheduler userJobQuartzJob = new StdSchedulerFactory("quartz/quartz_user_job.properties").getScheduler();
            buildUnFreezeBlackUserJob(userJobQuartzJob);
            userJobQuartzJob.start();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            System.exit(-1);
        }
    }

    /**
     * 解冻用户
     * @param scheduler
     * @throws SchedulerException
     */
    public void buildUnFreezeBlackUserJob(Scheduler scheduler) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(UserJob.class)
                .withIdentity("userDetail", USER_JOB_GROUP)
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("userTrigger", USER_JOB_GROUP)
                .startAt(new Date())
                .withSchedule(CronScheduleBuilder.cronSchedule("* 0/60 * * * ?"))       //3s
                .build();
        scheduler.scheduleJob(job, trigger);
    }

}
