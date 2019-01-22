package com.sangame.cache;

import java.util.Map;
import java.util.Set;

/**
 * 缓存服务接口
 * Created by admin on 2017/5/11.
 */
public interface CacheService {

    String ACT_KEY = "ACT";

    /**
     * 加锁
     * @param key
     * @param expire
     * @return
     */
    boolean lock(String key, int expire);

    /**
     * 释放锁
     * @param key
     */
    void unlock(String key);

    /**
     *
     * @param key
     * @param value
     * @param expire
     */
    void put(String key, String value, int expire);

    String get(String key);


    Set<String> keys(String pattern);

    /**
     *
     * @param key
     */
    void delete(String key);

    /**
     *
     * @param key
     * @param field
     * @param value
     * @param expire
     */
    void hset(String key, String field, String value, int expire);

    /**
     *
     * @param key
     * @param field
     * @return
     */
    String hget(String key, String field);

    /**
     *
     * @param key
     * @return
     */
    long hlen(String key);

    /**
     *
     * @param key
     * @param field
     */
    void hdelete(String key, String field);

    /**
     *
     * @param key
     * @param field
     * @return
     */
    Map<String, String> hgetAll(String key, String field);

    /**
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    boolean setnx(String key, String value, int expire);

    /**
     *
     * @param actKey
     * @param activityId
     * @return
     */
    String get(String actKey, String activityId);

    /**
     *
     * @param actKey
     * @param activityId
     * @param fromBean
     * @param i
     */
    void put(String actKey, String activityId, String fromBean, int i);
}
