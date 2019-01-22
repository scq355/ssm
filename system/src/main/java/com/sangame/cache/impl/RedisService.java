package com.sangame.cache.impl;

import com.sangame.cache.CacheService;
import com.sangame.utils.JedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2017/5/11.
 */
@Service
public class RedisService implements CacheService, InitializingBean {
    private static JedisUtil jedisUtil = JedisUtil.getInstance();

    private static Logger log = LoggerFactory.getLogger(RedisService.class);

    private String condition = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        condition = System.getProperty("condition");
        if (StringUtils.isBlank(condition)) {
            condition = "release";
        }
    }

    @Override
    public boolean lock(String key, int expire) {
        if ("test".equals(condition.toLowerCase())) {
            key = "TST-" + key;
        }
        Jedis client = jedisUtil.getJedis();
        try{
            long ret = client.setnx(key, "1");
            if (ret == 1) {
                client.expire(key, expire);
            }
            return ret == 1;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return false;
        } finally {
            client.close();
        }
    }

    @Override
    public void unlock(String key) {
        if ("test".equals(condition.toLowerCase())) {
            key = "TST-" + key;
        }
        Jedis client = jedisUtil.getJedis();
        try{
            client.del(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    @Override
    public void put(String key, String value, int expire) {
        if ("test".equals(condition.toLowerCase())) {
            key = "TST-" + key;
        }
        Jedis client = jedisUtil.getJedis();
        try {
            client.set(key, value);
            if (expire != -1) {
                client.expire(key, expire);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        } finally {
            client.close();
        }
    }

    @Override
    public String get(String key) {
        if ("test".equals(condition.toLowerCase())) {
            key = "TST-" + key;
        }
        Jedis client = jedisUtil.getJedis();
        try {
            return client.get(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return null;
        } finally {
            client.close();
        }
    }

    @Override
    public Set<String> keys(String pattern) {
        Jedis client = jedisUtil.getJedis();
        try {
            return client.keys(pattern);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return null;
        } finally {
            client.close();
        }
    }

    @Override
    public void delete(String key) {
        if ("test".equals(condition.toLowerCase())) {
            key = "TST-" + key;
        }
        Jedis client = jedisUtil.getJedis();
        try {
            client.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        } finally {
            client.close();
        }
    }

    @Override
    public void hset(String key, String field, String value, int expire) {
        if ("test".equals(condition.toLowerCase())) {
            key = "TST-" + key;
        }
        Jedis client = jedisUtil.getJedis();
        try {
            client.hset(key, field, value);
            if (expire != -1) {
                client.expire(key, expire);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        } finally {
            client.close();
        }
    }

    @Override
    public String hget(String key, String field) {
        if ("test".equals(condition.toLowerCase())) {
            key = "TST-" + key;
        }
        Jedis client = jedisUtil.getJedis();
        try {
            return client.hget(key, field);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return null;
        } finally {
            client.close();
        }
    }

    @Override
    public long hlen(String key) {
        Jedis client = jedisUtil.getJedis();
        try {
            return client.hlen(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return 0l;
        } finally {
            client.close();
        }
    }

    @Override
    public void hdelete(String key, String field) {
        if ("test".equals(condition.toLowerCase())) {
            key = "TST-" + key;
        }
        Jedis client = jedisUtil.getJedis();
        try {
            client.hdel(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        } finally {
            client.close();
        }
    }

    @Override
    public Map<String, String> hgetAll(String key, String field) {
        if ("test".equals(condition.toLowerCase())) {
            key = "TST-" + key;
        }
        Jedis client = jedisUtil.getJedis();
        try {
            Map<String, String> res = client.hgetAll(key);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return Collections.emptyMap();
        } finally {
            client.close();
        }
    }

    @Override
    public boolean setnx(String key, String value, int expire) {
        if ("test".equals(condition.toLowerCase())) {
            key = "TST-" + key;
        }
        Jedis client = jedisUtil.getJedis();
        try {
            long ret = client.setnx(key, value);
            if (ret == 1) {
                client.expire(key, expire);
            }
            return ret == 1;

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return false;
        } finally {
            client.close();
        }
    }

    @Override
    public String get(String key, String feild) {
        if ("test".equals(condition.toLowerCase())) {
            key = "TST-" + key;
        }
        Jedis client = jedisUtil.getJedis();
        try {
            return client.get(key + "-" + feild);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return null;
        } finally {
            client.close();
        }
    }

    @Override
    public void put(String key, String field, String value, int expire) {
        if ("test".equals(condition.toLowerCase())) {
            key = "TST-" + key;
        }
        Jedis client = jedisUtil.getJedis();
        try {
            client.set(key + "-" + field, value);
            if (expire != -1) {
                client.expire(key, expire);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        } finally {
            client.close();
        }
    }
}
