package com.sangame.datafilter.constant;

/**
 * @desc web层页面跳转相关常量
 * Created by scq on 2017/5/5.
 */
public class PageConstant {

    public static final String ERROR_PAGE = "system/error";                                     //默认错误页面
    
    public final static String LOGIN_PAGE = "login";											//登录页
    public final static String INDEX_PAGE = "index";											//首页
    
    public static final String BLACKUSER_INDEX_PAGE = "blackuser/index";                        //黑名单默认
    public static final String BLACKUSER_EDIT_USERID_PAGE = "blackuser/blacklist";              //黑名单用户编辑
    public static final String BLACKUSER_EDIT_IP_PAGE = "blackuser/blackIP";                    //黑名单IP编辑
    public static final String BLACKUSER_ADD = "blackuser/add";                                 //添加黑名单用户/ip

    public static final String SYSCONFIG_INDEX_PAGE = "sysconfig/index";                        //系统规则默认
    public static final String SYSCONFIG_EDIT_PAGE = "sysconfig/edit";                          //系统规则修改
    public static final String SYSCONFIG_ADD_PAGE = "sysconfig/add";                      		//系统规则新增

    public static final String WHITEUSER_INDEX_PAGE = "whiteuser/index";                        //白名单默认
    public static final String WHITEUSER_ADD = "whiteuser/add";                                 //添加白名单用户/ip

    public static final String COMMENT_INDEX_PAGE = "audit/comment/index";                       //评论首页
    public static final String INVESTMENT_INDEX_PAGE = "audit/investment/index";                 //投资圈首页
    public static final String JIEPAN_INDEX_PAGE = "audit/jiepan/index";                         //解盘首页
    public static final String MARKET_INDEX_PAGE = "audit/market/index";                         //商城首页
    public static final String QUIZ_INDEX_PAGE = "audit/quiz/index";                             //问答首页
    public static final String EXCEPTION_INDEX_PAGE = "audit/exception/index";                   //异常首页
    public static final String PANDECT_INDEX_PAGE = "audit/pandect/index";                       //数据总览首页
    public static final String COMMON_LOOK_PAGE = "audit/look";                                  //查看用户过滤信息界面
    
    public final static String VOCABULARY_INDEX_PAGE = "vocabulary/index";						//过滤词库列表页
    public final static String VOCABULARY_ADD_PAGE = "vocabulary/add";							//过滤词库新增页
    public final static String VOCABULARY_EDIT_PAGE = "vocabulary/edit";						//过滤词库修改页
    public final static String VOCABULARY_BATCH_ADD_PAGE = "vocabulary/batchAdd";				//过滤词库批量新增页
    
    public final static String SOURCE_INDEX_PAGE = "source/index";								//来源列表页
    public final static String SOURCE_EDIT_PAGE = "source/edit";								//来源修改页
}
