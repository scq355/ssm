package com.sangame.datafilter.job.blackuser;

import com.sangame.datafilter.job.base.BaseJob;
import com.sangame.datafilter.redis.RedisCacheTime;
import com.sangame.datafilter.redis.RedisHelper;
import com.sangame.datafilter.service.FilterBlackUserService;
import com.sangame.datafilter.util.SpringBeanManager;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by admin on 2017/5/12.
 * 暂时不用
 */
public class UpdateBlackUserJob extends BaseJob {

    private static final Logger LOG = LoggerFactory.getLogger(UnFreezeBlackUserJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        FilterBlackUserService blackUserService = SpringBeanManager.getBean(FilterBlackUserService.class);
        List<Long> ids = blackUserService.getIdList();
        if (!(ids.isEmpty())) {
            String blackUserIds = ids.toString();
            String subStr = blackUserIds.substring(1, blackUserIds.length() - 1);
            RedisHelper.setString("black_user_ids", subStr, RedisCacheTime.THREE_MONTH_STORE);
        }
    }
}
