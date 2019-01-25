package com.sangame.datafilter.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @desc 日期工具类
 * Created by scq on 2017/5/8.
 */
public class DateUtil {

    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    private static String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
    private static String defaultShortDatePattern = "yyyy-MM-dd";

    public static String defaultDatePatternShort = "yyyy年MM月dd日 HH:mm";
    public static String yyyyMMdd = "yyyyMMdd";
    public static String hhmmssSSS = "HHmmssSSS";
    public static String HHmm = "HH:mm";

    /**
     * 格式化日期
     * @param strDate
     * @return
     */
    public static Date parse(String strDate) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultDatePattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(strDate);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 简写格式化日期
     * @param strDate
     * @return
     */
    public static Date parseShort(String strDate) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultShortDatePattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(strDate);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 日期格式转String格式
     * @param date
     * @return
     */
    public static String parse(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultDatePattern);
        return simpleDateFormat.format(date);
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyyMMdd";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取日期之间的分钟数
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Long getBetweenMinutes(Date beginTime, Date endTime) {
        if (endTime == null || beginTime == null) {
            return 0L;
        }
        return (endTime.getTime() - beginTime.getTime()) / (60 * 1000);
    }

    /**
     * 获取本周末的日期
     * @return
     */
    public static Date getSundayOfThisWeek() {
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        calendar.add(Calendar.DATE, -day_of_week + 7);
        return calendar.getTime();
    }

    /**
     * 当前时间之后几分钟
     * @param date
     * @param n
     * @return
     */
    public static Date addMiniute(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, n);
        return calendar.getTime();
    }

    /**
     * 当前时间之后几天
     * @param date
     * @param n
     * @return
     */
    public static Date addDay(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, n);
        return calendar.getTime();
    }

    /**
     * 当前时间之后几小时
     * @param date
     * @param n
     * @return
     */
    public static Date addHour(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, n);
        return calendar.getTime();
    }

    /**
     * 获取当前时间，精确到分钟
     * @return
     */
    public static String getNowTimeToMinutes() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        return simpleDateFormat.format(nowTime);
    }

    /**
     * 比较俩个时间点先后
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean compareToTime(Date beginTime, Date endTime) {
        return beginTime.compareTo(endTime) < 0;
    }
}

