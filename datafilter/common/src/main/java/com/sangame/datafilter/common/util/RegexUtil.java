package com.sangame.datafilter.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @desc 正则工具类
 * Created by scq on 2017/5/8.
 */
public class RegexUtil {

    /**
     * 邮箱
     * @param email
     * @return
     */
    public static boolean testEmail(String email) {
        String emailRegex = "^(?:[a-z\\d]+[_\\-\\+\\.]?)*[a-z\\d]+@(?:([a-z\\d]+\\-?)*[a-z\\d]+\\.)+([a-z]{2,})+$";
        return Pattern.matches(emailRegex, email);
    }

    /**
     * 电话号码
     * @param mobile
     * @return
     */
    public static boolean testMobile(String mobile) {
        String mobileRegex = "^[1][3-8][0-9]{9}$";
        return StringUtils.isNotBlank(mobile) && Pattern.matches(mobileRegex, mobile);
    }

    /**
     * 数字
     * @param front
     * @return
     */
    public static boolean testNumber(String front) {
        if (StringUtils.isBlank(front)) {
            return false;
        }
        String frontRegex = "^[0-9]+$";
        return StringUtils.isNotBlank(front) && Pattern.matches(frontRegex, front);
    }

    /**
     * XSS跨站脚本过滤
     * @param content
     * @return
     */
    public static boolean containsXSS(String content) {
        if (StringUtils.isBlank(content) || content.indexOf("<") > -1 || content.indexOf("'") > -1
                || content.indexOf(">") > -1 || content.indexOf("/") > -1) {
            return true;
        }
        return false;
    }
}
