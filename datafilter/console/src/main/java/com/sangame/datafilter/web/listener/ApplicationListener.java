package com.sangame.datafilter.web.listener;

import com.sangame.datafilter.job.JobManager;
import com.sangame.datafilter.redis.RedisHelper;
import com.sangame.datafilter.update.UpdateApplication;
import com.sangame.datafilter.util.SpringBeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Mao Renwei
 * @Description
 * @date 2017/4/27.
 */
public class ApplicationListener implements ServletContextListener {

    private final static Logger LOG = LoggerFactory.getLogger(ApplicationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.info("starting filter ................");
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext());
        SpringBeanManager.initContext(context);
        
    	// 调用升级程序
 		String webPath = servletContextEvent.getServletContext().getRealPath("/");
 		UpdateApplication updating = UpdateApplication.getInstance();
 		updating.executeUpdate(webPath);
        
        RedisHelper.initJedisPool();
        LOG.info("Starting Quartz...............................");
        //启动任务
        JobManager jobManager = (JobManager) SpringBeanManager.getBean("jobManager");
        jobManager.startJob();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
