package com.sangame.listener;

import com.sangame.job.core.JobManager;
import com.sangame.utils.DateUtil;
import com.sangame.utils.SpringBeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by admin on 2017/5/11.
 */
public class ApplicationListener implements ServletContextListener {

    private final static Logger LOG = LoggerFactory.getLogger(ApplicationListener.class);

    private static final String LINE_SEP = System.getProperty("line.separator", "\r\n");

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.info("starting filter ................");
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        SpringBeanManager.initContext(context);

        LOG.info("Starting Quartz...............................");
        JobManager jobManager = (JobManager) SpringBeanManager.getBean("jobManager");        //启动任务
        jobManager.startJob();

        printCopyright();
    }

    private void printCopyright() {

        String print =  "-----------------------------------------" + LINE_SEP
                + "  Sangame Web Page Generate System" + LINE_SEP
                + "  Version: " + "1.1.0" + "." + "SNAPSHOT" + LINE_SEP
                + "  Node ID: " + 1 + LINE_SEP
                + "  Server Time: " + DateUtil.getSecondDateStr(System.currentTimeMillis()) + LINE_SEP
                + "  Powered by Sangame.com,(C)2006-" + DateUtil.getYearStr(System.currentTimeMillis()) + LINE_SEP
                + "-----------------------------------------" + LINE_SEP;
        System.err.println(print);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
