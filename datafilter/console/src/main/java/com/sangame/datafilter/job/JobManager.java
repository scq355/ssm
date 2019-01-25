package com.sangame.datafilter.job;

import com.sangame.datafilter.job.blackuser.UnFreezeBlackUserJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author scq
 * @Date 2017/5/12.
 */
@Component
public class JobManager {
    protected static final Logger LOG = LoggerFactory.getLogger(JobManager.class);

    public static final String JOB_GROUP = "default_job_group";                 // 默认组
    public static final String BLACKUSER_JOB_GROUP = "blackuser_job_group";     // 黑名单默认组

    public void startJob() {
        try {
            //黑名单定时解冻
            Scheduler userUnfreezeJobQuartzJob = new StdSchedulerFactory("quartz/quartz_userUnfreezeJob.properties").getScheduler();
            buildUnFreezeBlackUserJob(userUnfreezeJobQuartzJob);
            userUnfreezeJobQuartzJob.start();

            //黑名单定时刷新,暂时不用
//            Scheduler userUpdateJobQuartzJob = new StdSchedulerFactory("quartz/quartz_userUpdateJob.properties").getScheduler();
//            buildUpdateBlackUserJob(userUpdateJobQuartzJob);
//            userUpdateJobQuartzJob.start();

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
        JobDetail job = JobBuilder.newJob(UnFreezeBlackUserJob.class)
                .withIdentity("unfreezeUserDetail", BLACKUSER_JOB_GROUP)
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("unfreezeUserTrigger", BLACKUSER_JOB_GROUP)
                .startAt(new Date())
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 */1 * * ?"))       //每小时
                .build();
        scheduler.scheduleJob(job, trigger);
    }

    /**
     * 更新用户列表
     * @param scheduler
     * @throws SchedulerException
     */
    public void buildUpdateBlackUserJob(Scheduler scheduler) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(UnFreezeBlackUserJob.class)
                .withIdentity("updateUserDetail", BLACKUSER_JOB_GROUP)
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("updateUserTrigger", BLACKUSER_JOB_GROUP)
                .startAt(new Date())
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 */1 * * ?"))       //每小时
                .build();
        scheduler.scheduleJob(job, trigger);
    }
}
