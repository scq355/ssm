package com.sangame.datafilter.job.blackuser;

import com.sangame.datafilter.common.persistence.model.FilterBlackUser;
import com.sangame.datafilter.constant.ConsoleConstant.BlackStatus;
import com.sangame.datafilter.job.base.BaseJob;
import com.sangame.datafilter.service.FilterBlackUserService;
import com.sangame.datafilter.util.SpringBeanManager;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 定时解冻
 * Created by admin on 2017/5/10.
 */
@Component
public class UnFreezeBlackUserJob extends BaseJob{

    @SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(UnFreezeBlackUserJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        FilterBlackUserService blackUserService = SpringBeanManager.getBean(FilterBlackUserService.class);
        List<FilterBlackUser> unFreezeUsers = blackUserService.getBlackUsersBeforeNowTime(new Date());
        if (!(unFreezeUsers.isEmpty())) {
            for (FilterBlackUser user: unFreezeUsers) {
                user.setBlackState(BlackStatus.UNBLOCK.getValue());              //解冻->更新
                blackUserService.update(user);
            }
        }
    }
}
