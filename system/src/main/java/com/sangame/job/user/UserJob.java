package com.sangame.job.user;

import com.sangame.job.base.BaseJob;
import com.sangame.model.User;
import com.sangame.service.UserService;
import com.sangame.service.impl.UserSerivceImpl;
import com.sangame.utils.SpringBeanManager;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by admin on 2017/5/11.
 */
public class UserJob extends BaseJob {

    private static Logger log = LoggerFactory.getLogger(UserJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        UserService userService = SpringBeanManager.getBean(UserSerivceImpl.class);
        User user = userService.get(1);
        log.info("log info : " + user.toString());
    }
}
