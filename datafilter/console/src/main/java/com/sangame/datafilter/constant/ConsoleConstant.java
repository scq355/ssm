package com.sangame.datafilter.constant;

import java.io.File;

public class ConsoleConstant {

    public static final char SPT = '/';                                 // 路径分隔符

    public static final char FILE_SPT = File.separatorChar;             // 系统路径分隔符

    public static final Long SUPER_ROLE = -1L;                          // 超级管理员角色

    public static String CONSOLE_DOMAIN = null;                         // 控制台域名

    public static Long SECRET_KEY = null;                               // 谷歌密钥加解密私钥

	public static String FOREVER = "2099-01-01 00:00:00";				//永久期限
	/**
     * 假删除标识,0为未删除，1为已删除
     */
    public enum deleteFlagType {

        FALSE(0, "未删除"), TRUE(1, "已删除");

        private Integer value;
        private String name;

        private deleteFlagType(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
    
    /**
	 * 过滤词类型
	 */
	public enum VocabularyType {

		SENSITIVE(1, "敏感词"),
		ILLEGAL(2, "非法词");

		private int value;
		private String name;

		private VocabularyType(int value, String name) {
			this.value = value;
			this.name = name;
		}
		
		public int getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}
	

	/**
	 * 审核规则类型
	 */
	public enum AuditRuleType {
		
		SEND_FIRST(1, "先发后审"), 
		CHECK_FIRST(2, "先审后发"),
		DYNAMIC_CHECK(3, "动态审核");
		
		private int value;
		private String name;
		
		private AuditRuleType(int value, String name) {
			this.value = value;
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}
	
	/**
	 * @author MaoRenwei
	 * @description 项目类型
	 * @date 2017/4/25
	 */
	public enum ProjectType {

		TOTAL("总况", -1,"total"),
		COMMENT("评论", 0,"comment"),
		INVESTMENT("投资圈", 1,"investment"),
		JIEPAN("解盘", 2,"jiepan"),
		QUIZ("问答", 3,"quiz"),
		MARKET("商城", 4,"market");


		private String name;
		private int value;
		private String keyWord;

		ProjectType(String name, int value,String keyWord) {
			this.name = name;
			this.value = value;
			this.keyWord = keyWord;

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getKeyWord() {
			return keyWord;
		}

		public void setKeyWord(String keyWord) {
			this.keyWord = keyWord;
		}
	}

	/**
	 * @author MaoRenwei
	 * @description 数据操作
	 * @date 2017/4/25
	 */
	public enum DataOperation {
		PASS("通过", 0),
		REJECT("拒绝", 1),
		RECOVER("恢复", 2);

		private String name;
		private int value;

		DataOperation(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	/**
	 * @author MaoRenwei
	 * @description 投资圈数据类型
	 * @date 2017/4/25
	 */
	public enum InvestmentDataType {
		CCHECK("说说", 0),
		TALK("评论", 1);

		private String name;
		private int value;

		InvestmentDataType(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	/**
	 * @author MaoRenwei
	 * @description 问答数据类型
	 * @date 2017/4/25
	 */
	public enum QuizDataType {
		QUESTION("问题", 0),
		ANSWER("答案", 1);

		private String name;
		private int value;

		QuizDataType(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}
	
	/**
	* @author MaoRenwei
	* @description 冻结时间
	* @date 2017/5/8
	*/
	public enum FreezeTime {
		FOREVER("永远", -1),
		ONE_DAY("一天",1),
		THREE_DAY("三天",3),
		ONE_WEEK("一周",7),
		ONE_MONTH("一个月",30),
		THREE_MONTH("三个月",90),
		SIX_MONTH("六个月",180),
		ONE_YEAR("一年",365);

		private String name;
		private int value;

		FreezeTime(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	/**
	* @author MaoRenwei
	* @description 异常数据状态
	* @date 2017/5/9
	*/
	public enum ExceptionStatus{
		TREATED("处理",0),
		UNTREATED("未处理",1);

		private String name;
		private int value;

		ExceptionStatus(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	/**
	 * @author MaoRenwei
	 * @description 异常处理方式
	 * @date 2017/5/9
	 */
	public enum ExceptionDealType{
		SEND("发送",0),
		INSERT("插入",1);

		private String name;
		private int value;

		ExceptionDealType(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}
	
	
	/**
	 * @author 叶青锋
	 * @description 黑名单的冻结状态
	 * @date 2017/5/11
	 */
	public enum BlackStatus{
		BLOCK("冻结", 1),
		UNBLOCK("解冻", 2);

		private String name;
		private int value;

		BlackStatus(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}
	

	/**
	 * @author 叶青锋
	 * @description 黑白名单的添加方法
	 * @date 2017/5/11
	 */
	public enum BlackWhiteWay{
		USERID("用户ID", 1),
		IP("IP", 2);

		private String name;
		private int value;

		BlackWhiteWay(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 系统配置项
	 */
	public enum SystemConfigKey {
		
		MAX_LOGIN_COUNT("loginFailMaxCount", "登录错误最大次数"),
		CHECK_AUTH_CODE("checkAuthCode", "是否启用动态密码验证"),
		DAY_SUBMIT_COUNT_MAX("daySubmitCountMax", "24小时最大提交量"),
		DAY_SENSITIVE_WORD_MAX("daySensitiveWordMax", "24小时最大敏感词次数"),
		DAY_ILLEGAL_WORD_MAX("dayIllegalWordMax", "24小时最大非法词次数");
		private SystemConfigKey(String value, String memo) {
			this.value = value;
			this.memo = memo;
		}

		private String value;
		private String memo;

		public String getValue() {
			return value;
		}

		public String getMemo() {
			return memo;
		}
	}
}
