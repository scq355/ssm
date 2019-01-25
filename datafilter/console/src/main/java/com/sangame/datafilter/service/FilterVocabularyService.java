package com.sangame.datafilter.service;

import com.sangame.datafilter.common.persistence.mapper.FilterVocabularyMapper;
import com.sangame.datafilter.common.persistence.model.FilterVocabulary;
import com.sangame.datafilter.common.util.PageUtil;
import com.sangame.datafilter.constant.ConsoleConstant;
import com.sangame.datafilter.redis.RedisCacheKey;
import com.sangame.datafilter.redis.RedisCacheTime;
import com.sangame.datafilter.redis.RedisHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class FilterVocabularyService {

	private static final Logger LOG = LoggerFactory.getLogger(FilterVocabularyService.class);

	@Autowired
    private FilterVocabularyMapper filterVocabularyMapper;

	private Map<String, Object> nowMap;	//暂时存放DFA模型

    public int insert(FilterVocabulary filterVocabulary) {
		return filterVocabularyMapper.insert(filterVocabulary);
	}

    public int update(FilterVocabulary filterVocabulary) {
		return filterVocabularyMapper.update(filterVocabulary);
	}

	public int delete(Long id) {
		return filterVocabularyMapper.delete(id);
	}

	public void batchDelete(String[] ids) {
		filterVocabularyMapper.batchDelete(ids);
	}

	public FilterVocabulary getById(Long id) {
		return filterVocabularyMapper.getById(id);
	}

	public int countByParm(FilterVocabulary filterVocabulary) {
		Map<String, Object> parm = new HashMap<String, Object>();
		return filterVocabularyMapper.countByParm(parm);
	}

	public int countByParm(Map<String, Object> parm) {
		return filterVocabularyMapper.countByParm(parm);
	}
	
	/**
	 * 批量新增
	 * @param filterVocabularyList
	 */
	public void batchInsert(List<FilterVocabulary> filterVocabularyList) {
		filterVocabularyMapper.batchInsert(filterVocabularyList);
	}
	
	
	/**
	*根据参数获取对象列表
	**/
	public List<FilterVocabulary> getListByParm(Map<String, Object> parm) {
		return filterVocabularyMapper.getListByParm(parm, null);
	}

	public PageUtil getListByParm(Map<String, Object> parm, int pageStart, Integer pageSize) {
		PageUtil pageUtil = new PageUtil(pageStart, pageSize);
		int count = filterVocabularyMapper.countByParm(parm);
		pageUtil.setTotalRecords(count);
		pageUtil.setOrderBy("updated_at DESC");
		if (count != 0) {
			List<Object> list = filterVocabularyMapper.getListByParm(parm, pageUtil);
			pageUtil.setList(list);
		}
		return pageUtil;
	}
	
	/**
	 * 判断某个过滤词是否存在
	 * @param content
	 * @param type
	 * @return
	 */
	public boolean isExistVocabulary(String content, Integer type) {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("content", content);
		parm.put("type", type);
		List<FilterVocabulary> list = filterVocabularyMapper.getListByParm(parm, null);
		if (list != null && list.size() > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * 批量更改类型，非法词/敏感词
	 * @param ids
	 * @param type
	 * @return
	 */
	public void batchUpdate(String[] ids, Integer type){
		filterVocabularyMapper.batchUpdate(ids, type);
	}


	/**
	 * 过滤词判断
	 * @param txt
	 * @param wordType
	 * @return
	 */
	public boolean isContaintSensitiveWord(String txt, int wordType) {
		boolean flag = false;
		for(int i = 0 ; i < txt.length() ; i++){
			int matchFlag = checkSensitiveWord(txt, i, wordType); //判断是否包含敏感字符
			if(matchFlag > 0){    //大于0存在，返回true
				flag = true;
			}
		}
		return flag;
	}

	/**
	  * 方法描述：获取文字中包含的所有敏感词
	  * @param: txt 评论内容
	  * @return: 包含的敏感词
	 */
	public Set<String> getSensitiveWord(String txt, int wordType){
		Set<String> sensitiveWordList = new HashSet<String>();
		for(int i = 0 ; i < txt.length() ; i++){
			int length = checkSensitiveWord(txt, i, wordType);    //判断是否包含敏感字符
			if(length > 0){    //存在,加入list中
				sensitiveWordList.add(txt.substring(i, i+length));
				i = i + length - 1;    //减1的原因，是因为for会自增
			}
		}
		return sensitiveWordList;
	}


	/**
	  * 方法描述：检查文字中是否包含敏感字符
	  * @param txt
	  * @param beginIndex
	  * @return: 如果存在，则返回敏感词字符的长度，不存在返回0
	 */
	@SuppressWarnings("unchecked")
	public int checkSensitiveWord(String txt,int beginIndex, int wordType){
		boolean  flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
		int matchFlag = 0;     //匹配标识数默认为0
		char word = 0;
		nowMap = getSensitiveWordDFA(wordType);
		try {
			for(int i = beginIndex; i < txt.length() ; i++){
				word = txt.charAt(i);
				nowMap = (Map<String, Object>) nowMap.get(word);     //获取指定key
				if(nowMap != null){     //存在，则判断是否为最后一个
					matchFlag++;     //找到相应key，匹配标识+1
					if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
						flag = true;       //结束标志位为true
						break;
					}
				}
				else{     //不存在，直接返回
					break;
				}
			}
			if(!flag) matchFlag = 0;     //长度必须大于等于1，为词
		} catch (Exception e) {
			LOG.error("检查文字中是否包含敏感字符出错", e);
		}
		return matchFlag;
	}

	/**
	  * 方法描述：读取敏感词库,并构建DFA模型
	 */
	@SuppressWarnings("rawtypes")
	public Map getSensitiveWordDFA(int wordType){
		Map sensitiveWordMap = null;
		try {
			sensitiveWordMap  = addSensitiveWordToHashMap(getMapFromWordCache(wordType));//将敏感词库加入到HashMap中
		} catch (Exception e) {
			LOG.error("将敏感词库加入到HashMap中出错", e);
		}
		return sensitiveWordMap;
	}



	/**
	 * 方法描述：将敏感词放入Map中，构建一个DFA算法模型
	 * @param: sensitiveWordMap
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map addSensitiveWordToHashMap(Map<String, String> sensitiveWordMap) {
		Map resultMap = new HashMap(sensitiveWordMap.size());     //初始化敏感词容器，减少扩容操作
		String key = null;
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		try {
			Iterator<Map.Entry<String, String>> iterator = sensitiveWordMap.entrySet().iterator();//迭代keyWordSet
			while(iterator.hasNext()){
				key = iterator.next().getKey();    //关键字
				nowMap = resultMap;
				for(int i = 0 ; i < key.length() ; i++){
					char keyChar = key.charAt(i);       //转换成char型
					Object wordMap = nowMap.get(keyChar);       //获取
					if(wordMap != null){        //如果存在该key，直接赋值
						nowMap = (Map) wordMap;
					}
					else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
						newWorMap = new HashMap<String,String>();
						newWorMap.put("isEnd", "0");     //不是最后一个
						nowMap.put(keyChar, newWorMap);
						nowMap = newWorMap;
					}
					if(i == key.length() - 1){
						nowMap.put("isEnd", "1");    //最后一个
					}
				}
			}
		} catch (Exception e) {
			LOG.error("构建DFA算法模型出错", e);
		}
		return resultMap;
	}

	/**
	  * 方法描述：从缓存中获取所有敏感词的map
	 */
	private Map<String, String> getMapFromWordCache(int wordType) {
		Map<String, String> result = new HashMap<String, String>();
		if (wordType == ConsoleConstant.VocabularyType.SENSITIVE.getValue()) {
			result = RedisHelper.getHashALL(RedisCacheKey.WORD_SENSITIVE);
		} else if (wordType == ConsoleConstant.VocabularyType.ILLEGAL.getValue()) {
			result = RedisHelper.getHashALL(RedisCacheKey.WORD_ILLEGAL);
		}
		if (result == null || result.size() < 1) {
			result = initWordCache(wordType);
		}
		return result;
	}

	/**
	 * 当没有敏感词缓存时，进行初始化操作
	 */
	private HashMap<String, String> initWordCache(int wordType) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		try {
			List<FilterVocabulary> dbWordList = null;
			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("deleteFlag", ConsoleConstant.deleteFlagType.FALSE.getValue());
			parm.put("type", wordType);
			dbWordList = filterVocabularyMapper.getListByParm(parm, null);
			if (dbWordList != null && !dbWordList.isEmpty()) {
				for (FilterVocabulary w : dbWordList) {
					resultMap.put(w.getContent(), w.getContent());
				}
				if (wordType == ConsoleConstant.VocabularyType.SENSITIVE.getValue()) {
					RedisHelper.setHash(RedisCacheKey.WORD_SENSITIVE, resultMap, RedisCacheTime.ONE_MONTH_STORE);
				} else if (wordType == ConsoleConstant.VocabularyType.ILLEGAL.getValue()) {
					RedisHelper.setHash(RedisCacheKey.WORD_ILLEGAL, resultMap, RedisCacheTime.ONE_MONTH_STORE);
				}
			}
		} catch (Exception e) {
			LOG.error("初始化缓存出错", e);
		}
		return resultMap;
	}
}
