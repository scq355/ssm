package com.sangame.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author defier.lai
 * 2010-4-13 下午08:04:21
 * @version 1.0
 */
public class DateUtil {

    private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);

    private static final SimpleDateFormat YEAR 				= getFormat("yyyy");

    private static final SimpleDateFormat MONTH 				= getFormat("yyyy-MM");

    private static final SimpleDateFormat DAY 				= getFormat("yyyy-MM-dd");

    private static final SimpleDateFormat HOUR 				= getFormat("yyyy-MM-dd HH");

    private static final SimpleDateFormat MINUTE 				= getFormat("yyyy-MM-dd HH:mm");

    private static final SimpleDateFormat SECOND 				= getFormat("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat DAY_ONLY 			= getFormat("MM:dd");

    private static final SimpleDateFormat MINUTE_ONLY 		= getFormat("HH:mm");

    private static final SimpleDateFormat SECOND_ONLY 		= getFormat("HH:mm:ss");

    private static final SimpleDateFormat YEAR_DAY_NUMBER 	= getFormat("yyMMdd");

    private static final SimpleDateFormat DAY_NUMBER 			= getFormat("yyyyMMdd");

    private static final SimpleDateFormat MINUTE_ANOTHER 		= getFormat("yyyyMMdd-HHmm");

    /**
     * 日期类型格式化为4位年份 yyyy
     * @param date
     * @return
     */
    public static String getYearStr(Date date) {
        synchronized (YEAR) {
            return getStr(date, YEAR);
        }
    }

    /**
     * 时间戳格式化为4位年份 yyyy
     * @param date
     * @return
     */
    public static String getYearStr(long date) {
        synchronized (YEAR) {
            return getStr(new Date(date), YEAR);
        }
    }

    /**
     * 日期类型获取日期时间到分钟为止 yyyyMMdd-HHmm
     * @param date
     * @return
     */
    public static String getMinuteDbStr(Date date) {
        synchronized (MINUTE_ANOTHER) {
            return getStr(date, MINUTE_ANOTHER);
        }
    }

    /**
     * 字符串类型获取时间到分钟 yyyyMMdd-HHmm
     * @param str
     * @return
     */
    public static Date getMinuteDbDate(String str) {
        synchronized (MINUTE_ANOTHER) {
            return getDate(str, MINUTE_ANOTHER);
        }
    }

    /**
     * 将时间以"HH:mm:ss"格式转为字符串
     * @param date
     * @return
     */
    public static String getSecondOnlyStr(Date date) {
        synchronized (SECOND_ONLY) {
            return getStr(date, SECOND_ONLY);
        }
    }

    /**
     * 将毫秒数以"MM:dd"格式转为字符串
     * @param date
     * @return
     */
    public static String getOnlyDayStr(long date) {
        synchronized (DAY_ONLY) {
            return getStr(new Date(date), DAY_ONLY);
        }
    }

    /**
     * 将毫秒数以"yyyy-MM-dd HH:mm:ss"格式转为字符串
     * @param date
     * @return
     */
    public static String getSecondDateStr(long date) {
        return getSecondStr(new Date(date));
    }

    /**
     * 将毫秒数以"HH:mm:ss"格式转为字符串
     * @param date
     * @return
     */
    public static String getSecondStr(long date) {
        return getSecondOnlyStr(new Date(date));
    }

    /**
     * 将日期类型格式化为"yyyy-MM-dd HH:mm"转为字符串
     * @param date
     * @return
     */
    public static String getMinuteStr(Date date) {
        synchronized (MINUTE) {
            return getStr(date, MINUTE);
        }
    }

    /**
     * 将毫秒数格式化为"yyyy-MM-dd HH:mm"转为字符串
     * @param date
     * @return
     */
    public static String getMinuteStr(long date) {
        return getMinuteStr(new Date(date));
    }

    /**
     * 将日期类型式化为"HH:mm"转为字符串
     * @param date
     * @return
     */
    public static String getMinuteOnlyStr(Date date) {
        synchronized (MINUTE_ONLY) {
            return getStr(date, MINUTE_ONLY);
        }
    }

    /**
     * 将日期类型式化为"yyyy-MM-dd HH:mm:ss"转为字符串
     * @param date
     * @return
     */
    public static String getSecondStr(Date date) {
        synchronized (SECOND) {
            return getStr(date, SECOND);
        }
    }

    /**
     * 将日期类型式化为"yyyy-MM-dd"转为字符串
     * @param date
     * @return
     */
    public static String getDayStr(Date date) {
        synchronized (DAY) {
            return getStr(date, DAY);
        }
    }

    /**
     * 将日期类型式化为"yyyyMMdd"转为int型
     * @param date
     * @return
     */
    public static int getDayNumber(Date date) {
        if(date==null){
            return 0;
        }
        synchronized (DAY_NUMBER) {
            return Integer.valueOf(getStr(date, DAY_NUMBER));
        }
    }

    /**
     * 将日期类型式化为"yyMMdd"转为int型
     * @param date
     * @return
     */
    public static int getYYDayNumber(Date date) {
        if(date==null){
            return 0;
        }
        synchronized (YEAR_DAY_NUMBER) {
            return Integer.valueOf(getStr(date, YEAR_DAY_NUMBER));
        }
    }

    /**
     * 将日期类型以"yyyy-MM-dd"格式化
     * @param date
     * @return
     */
    public static Date getDayDate(Date date) {
        return getDayDate(getDayStr(date));
    }

    /**
     * 将毫秒数以"yyyy-MM-dd"格式化转为字符串
     * @param date
     * @return
     */
    public static String getDayStr(long date) {
        return getDayStr(new Date(date));
    }

    /**
     * 将字符串以"yyyy-MM-dd HH:mm:ss"转为日期类型
     * @param dateStr
     * @return
     */
    public static Date getSecondDate(String dateStr) {
        synchronized (SECOND) {
            return getDate(dateStr, SECOND);
        }
    }

    /**
     * 将字符串以"yyyy-MM-dd"转为日期类型
     * @param dateStr
     * @return
     */
    public static Date getDayDate(String dateStr) {
        synchronized (DAY) {
            return getDate(dateStr, DAY);
        }
    }

    /**
     * 将字符串以"HH:mm"转为日期类型
     * @param dateStr
     * @return
     */
    public static Date getMinuteOnlyDate(String dateStr) {
        synchronized (MINUTE_ONLY) {
            return getDate(dateStr, MINUTE_ONLY);
        }
    }

    /**
     * 将字符串以"yyyy-MM-dd HH:mm"转为日期类型
     * @param dateStr
     * @return
     */
    public static Date getMinuteDate(String dateStr) {
        synchronized (MINUTE) {
            return getDate(dateStr, MINUTE);
        }
    }

    /**
     * 将毫秒数以"yyyy-MM-dd HH:mm"转为日期类型
     * @param time
     * @return
     */
    public static Date getMinuteDate(long time) {
        return getMinuteDate(getMinuteStr(time));
    }

    /**
     * 日期运算 Day运算
     * @param date
     * @param offset
     * @return
     */
    public static Date daysAddOrSub(Date date, int offset) {
        return addOrSub(date, Calendar.DAY_OF_MONTH, offset);
    }

    /**
     * 日期运算 Hour运算
     * @param date
     * @param offset
     * @return
     */
    public static Date hoursAddOrSub(Date date, int offset) {
        return addOrSub(date, Calendar.HOUR_OF_DAY, offset);
    }

    /**
     * 日期运算 Minute运算
     * @param date
     * @param offset
     * @return
     */
    public static Date minutesAddOrSub(Date date, int offset) {
        return addOrSub(date, Calendar.MINUTE, offset);
    }

    /**
     * 日期运算 Second运算
     * @param date
     * @param offset
     * @return
     */
    public static Date secondsAddOrSub(Date date, int offset) {
        return addOrSub(date, Calendar.SECOND, offset);
    }

    /**
     * 日期运算
     * @param date
     * @param type
     * @param offset
     * @return
     */
    private static Date addOrSub(Date date, int type, int offset) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.get(type);
        cal.set(type, cal.get(type) + offset);
        return cal.getTime();
    }

    /**
     * 日期类型格式化为字符串
     * @param date
     * @param format 格式
     * @return
     */
    private static String getStr(Date date, SimpleDateFormat format) {
        if (date == null) {
            return "";
        }
        return format.format(date);
    }

    /**
     * 获取日期格式表达式
     * @param format
     * @return
     */
    private static SimpleDateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /**
     * 将字符串类型日期格式化并转为日期类型
     * @param dateStr
     * @param format
     * @return
     */
    private static Date getDate(String dateStr, SimpleDateFormat format) {
        if ("".equals(dateStr) || dateStr == null)
            return null;
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            LOG.error("format yyyy-MM-dd HH:mm:ss error:", e);
        }
        return null;
    }

    /**
     * 是否为TimeString 样式的String 00:00 - 23:59
     *
     * @param toCheck
     * @return
     */
    public static boolean isTimeString(String toCheck) {
        if(!StringUtils.isNotBlank(toCheck)){
            return false;
        }
        if (toCheck.trim().matches("([0-1][0-9]|2[0-3]):[0-5][0-9]|24:00"))
            return true;
        else
            return false;
    }

    /**
     * 比较两个TimeString 的大小
     *
     * @param ts1
     * @param ts2
     * @return
     */
    public static int compareHHmmInString(String ts1, String ts2) {
        return ts1.compareTo(ts2);
    }

    /**
     * 检测是否在开始与结束之间，前闭后开区间 -1： start 不小于end 0: 不在start 与end之间 1: 在start与end之间
     *
     * @param ts
     * @param start
     * @param end
     * @return
     */
    public static int betweenHHmmInString(String ts, String start, String end) {
        if (compareHHmmInString(start, end) >= 0)
            return -1;
        if (compareHHmmInString(ts, start) < 0)
            return 0;
        if (compareHHmmInString(end, ts) <= 0)
            return 0;
        return 1;
    }

    /**
     * 检测两个时间是否相等, 00:00 == 24:00
     *
     * @param ts1
     * @param ts2
     * @return
     */
    public static boolean equalsInTimeString(String ts1, String ts2) {
        if (ts1.equals(ts2))
            return true;
        if (ts1.equals("00:00") || ts1.equals("24:00")) {
            if (ts2.equals("00:00") || ts2.equals("24:00")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测是否为同一个小时和分钟. 1: cal>c; 0:cal=c; -1:cal<c
     */
    public static int compareHHmm(Calendar cal, Calendar c) {
        if (cal.get(Calendar.HOUR_OF_DAY) > c.get(Calendar.HOUR_OF_DAY)) {
            return 1;
        } else if (cal.get(Calendar.HOUR_OF_DAY) == c.get(Calendar.HOUR_OF_DAY)) {
            if (cal.get(Calendar.MINUTE) > c.get(Calendar.MINUTE))
                return 1;
            else if (cal.get(Calendar.MINUTE) == c.get(Calendar.MINUTE))
                return 0;
            else
                return -1;
        } else
            return -1;
    }

    /**
     * 检测是否在开始与结束之间，闭区间 -1： start 不小于end 0: 不在start 与end之间 1: 在start与end之间
     *
     * @param cal
     * @param start
     * @param end
     * @return
     */
    public static int betweenHHmm(Calendar cal, Calendar start, Calendar end) {
        if (compareHHmm(start, end) != -1)
            return -1;
        if (compareHHmm(cal, start) == -1)
            return 0;
        if (compareHHmm(end, cal) == -1)
            return 0;
        return 1;
    }

    /**
     * 检测是否为同在一天. 1: cal>c; 0:cal=c; -1:cal<c
     */
    public static boolean compareDay(Calendar cal, Calendar c) {
        if (cal.get(Calendar.MONTH) == c.get(Calendar.MONTH)
                && cal.get(Calendar.DAY_OF_MONTH) == c
                .get(Calendar.DAY_OF_MONTH))
            return true;
        else
            return false;
    }

    /**
     * 将00:00格式的字符串转为Calender
     *
     * @param timeString
     * @return Calender
     * @throws Exception
     */
    public static Calendar string2calendar(String timeString) throws Exception {
        if (!DateUtil.isTimeString(timeString))
            throw new Exception("Wrong argument : timeString format error "
                    + timeString);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, getHour(timeString));
        cal.set(Calendar.MINUTE, getMinute(timeString));
        return cal;
    }

    /**
     * 将calendar转为HH:MM格式的字符串
     *
     * @param cal
     * @return
     */
    public static String calendar2TimeString(Calendar cal) {
        return (cal.get(Calendar.HOUR_OF_DAY) > 9 ? cal.get(Calendar.HOUR_OF_DAY) : "0"
                + cal.get(Calendar.HOUR_OF_DAY))
                + ":"
                + (cal.get(Calendar.MINUTE) > 9 ? cal.get(Calendar.MINUTE) : "0"
                + cal.get(Calendar.MINUTE));
    }

    /**
     * TimeString 中得到小时信息
     *
     * @param timeString
     * @return
     */
    public static int getHour(String timeString) {
        return Integer.parseInt(timeString.substring(0, 2));
    }

    /**
     * TimeString 中得到分钟信息
     *
     * @param timeString
     * @return
     */
    public static int getMinute(String timeString) {
        return Integer.parseInt(timeString.substring(3, 5));
    }

    /**
     * 获取日期中的分钟
     * @param minute
     * @return
     */
    public static String getDateStringFromMinute(String minute) {
        return minute.substring(5, 10);
    }

    /**
     * 获取日期中的小时和分钟
     * @param minute
     * @return
     */
    public static String getMiniteOnlyFromMinute(String minute) {
        return minute.substring(11, 16);
    }

    /**
     * 判断字符串是否为"HH:mm"格式日期
     * @param str
     * @return
     */
    public static boolean isMiniteDate(String str) {
        if (str == null) {
            return false;
        }
        try {
            MINUTE_ONLY.parse(str);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    public static long getMiniteDate(Date date,String str) {
        if(str==null)
            return 0;
        return getMinuteDate(getDayStr(date)+" "+str).getTime();
    }
    /**
     * 从Date的字符串中只得到月日信息
     *
     * @param dateString
     * @return
     */
    public static String getDateOnlyFromDate(String dateString) {
        return dateString.substring(5, 10);
    }

    // /////////////////////////////date tools////////////////////////////
    /**
     * 将calendar转为MM:DD格式的字符串
     *
     * @param cal
     * @return
     */
    public static String calendar2DateString(Calendar cal) {
        return (cal.get(Calendar.MONTH) + 1 > 9 ? cal.get(Calendar.MONTH) + 1 : "0"
                + (cal.get(Calendar.MONTH) + 1))
                + ":"
                + (cal.get(Calendar.DAY_OF_MONTH) > 9 ? cal.get(Calendar.DAY_OF_MONTH) : "0"
                + cal.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 判断字符串是否为日期类型字符串
     * @param toCheck
     * @return
     */
    public static boolean isDateString(String toCheck) {
        if (toCheck == null)
            return false;
        if (toCheck.trim().matches("(0[0-9]|1[0-2]):([0-2][0-9]|3[0-1])"))
            return true;
        else
            return false;
    }

    /**
     * 将日期格式的字符串转换为日期类型
     * @param date 支持 yyyy, yyyy-MM, yyyy-MM-dd, yyyy-MM-dd HH, yyyy-MM-dd HH:mm, yyyy-MM-dd HH:mm:dd
     * @return
     */
    public static Date toDate(String date) {
        try {
            if(date.length() == 4) {
                return YEAR.parse(date);
            } else if(date.length() == 7) {
                return MONTH.parse(date);
            } else if(date.length() == 10) {
                return DAY.parse(date);
            } else if(date.length() == 13) {
                return HOUR.parse(date);
            } else if(date.length() == 16) {
                return MINUTE.parse(date);
            } else if(date.length() == 19) {
                return SECOND.parse(date);
            } else {
                throw new IllegalArgumentException("unknown date format("+ date + ")");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("unknown date format("+ date + ")");
        }
    }
}