package com.sangame.datafilter.redis;

/**
 * @Description: redis缓存的key
 * @author yeqingfeng
 * @date 2017年4月28日 
 */
public class RedisCacheKey {
	//敏感词
	public static final String WORD_SENSITIVE = "filter.word.sensitive";
	//非法词
	public static final String WORD_ILLEGAL = "filter.word.illegal";

	//数据总览
	public static final String DATA_PANDECT = "filter.data.pandect";
	//数据总览图
	public static final String DATA_PANDECT_STATIC = "filter.data.pandect.static.";

	/**
	 * 用户缓存信息
	 */
	public static final String DAY_SUBMIT_COUNT_MAX = "filter.day.submit.count.max"; 			//24最大提交量
	public static final String DAY_SENSITIVE_WORD_MAX = "filter.day.sensitive.word.max";		//24小时敏感词数
	public static final String DAY_ILLEGAL_WORD_MAX = "filter.day.illegal.word.max";			//24小时非法词数
	
	public static final String FILTER_SOURCE = "filter.source.";								//过滤来源
}
