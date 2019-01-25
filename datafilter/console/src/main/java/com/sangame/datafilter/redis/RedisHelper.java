package com.sangame.datafilter.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sangame.datafilter.common.util.SerializeUtil;
import com.sangame.datafilter.util.SpringBeanManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**   
* @Description: Redis使用辅助类，初始化、put存数据、get取数据
* @author yeqingfeng
* @date 2017年3月29日        
*/
public class RedisHelper {

	private static final Logger LOG = LoggerFactory.getLogger(RedisHelper.class);
	
	private static JedisPool jedisPool;

	public static void initJedisPool() {
		try {
			if (jedisPool == null)
				jedisPool = SpringBeanManager.getBean("jedisPool", JedisPool.class);
		} catch (Exception e) {
			LOG.error("初始化jedisPool异常", e);
		}
	}

	private static Jedis getJedis() throws Exception {
		return jedisPool.getResource();
	}

	/*private static String buildKey(String key) {
		return DigestUtils.md5Hex(key);
	}*/

	/**
	 * 根据key存储String
	 * @param key
	 * @param value
	 * @param redisCacheTime
	 * @return
	 */
	public static boolean setString(String key, String value, RedisCacheTime redisCacheTime) {
		Jedis jedis = null;
		try {
			if (StringUtils.isBlank(key) || value == null) {
				if (LOG.isDebugEnabled())
					LOG.debug("invalid key or value to redis! key:{} value:{} ", key, value);
				return false;
			}
			jedis = getJedis();
			jedis.set(key, value);
			if (redisCacheTime != null && redisCacheTime != RedisCacheTime.PERMANENT_STORE)
				jedis.expire(key, redisCacheTime.getSeconds());
			return true;
		} catch (Exception e) {
			LOG.error("redis set error", e);
			return false;
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}
	
	/**
	 * 根据key获取String
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key))
			return null;
		try {
			jedis = getJedis();
			return jedis.get(key);
		} catch (Exception e) {
			LOG.error("redis get error", e);
			return null;
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	
	/**
	 * 使用序列化的方式，根据key存储Object
	 * @param key
	 * @param object
	 * @param redisCacheTime
	 * @return
	 */
	public static boolean setObjectBySerialize(String key, Object object, RedisCacheTime redisCacheTime) {
		Jedis jedis = null;
		try {
			if (StringUtils.isBlank(key) || object == null) {
				if (LOG.isDebugEnabled())
					LOG.debug("invalid key or object to redis! key:{} Object:{} ", key, object);
				return false;
			}
			jedis = getJedis();
			jedis.set(key.getBytes(), SerializeUtil.serialize(object));
			/*String jsonValue = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
			jedis.set(key, jsonValue);*/
			if (redisCacheTime != null && redisCacheTime != RedisCacheTime.PERMANENT_STORE)
				jedis.expire(key.getBytes(), redisCacheTime.getSeconds());
			return true;
		} catch (Exception e) {
			LOG.error("redis set error", e);
			return false;
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}
	
	/**
	 * 使用反序列化的方式，根据key获取Object
	 * @param key
	 * @return
	 */
	public static Object getObjectByDeserialize(String key) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key))
			return null;
		try {
			jedis = getJedis();
			return SerializeUtil.deserialize(jedis.get(key.getBytes())); 
		} catch (Exception e) {
			LOG.error("redis get error", e);
			return null;
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}
	
	/**
	 * 通过将对象转换为JSONString的方式，根据key存储Object
	 * @param key
	 * @param redisCacheTime
	 * @return
	 */
	public static boolean setObjectByJSON(String key, Object object, RedisCacheTime redisCacheTime) {
		Jedis jedis = null;
		try {
			if (StringUtils.isBlank(key) || object == null) {
				if (LOG.isDebugEnabled())
					LOG.debug("invalid key or object to redis! key:{} Object:{} ", key, object);
				return false;
			}
			jedis = getJedis();
			String jsonValue = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
			jedis.set(key, jsonValue);
			if (redisCacheTime != null && redisCacheTime != RedisCacheTime.PERMANENT_STORE)
				jedis.expire(key, redisCacheTime.getSeconds());
			return true;
		} catch (Exception e) {
			LOG.error("redis set error", e);
			return false;
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}
	
	/**
	 * 通过传入的Class将JSONString转换为对象，根据key和className获取Object
	 * @param key
	 * @param className
	 * @return
	 */
	public static Object getObjectByClass(String key, Class className) {
		Jedis jedis = null;
		if (StringUtils.isBlank(key))
			return null;
		try {
			jedis = getJedis();
			String jsonString = jedis.get(key);
			return JSON.parseObject(jsonString, className);
		} catch (Exception e) {
			LOG.error("redis get error", e);
			return null;
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}
	
    /**
     * 清空key对应的值
     *
     * @param key
     */
    protected void clearValue(String key) {
        Jedis jedis = null;
        try {

            //获得客户端
            jedis = jedisPool.getResource();

            jedis.del(key);
        } catch (Exception e) {
			LOG.error("redis clearValue error", e);
		} finally {
            if(jedis != null)
                jedis.close();
        }
    }

    /**
     * 设置Hash
     */
    public static void setHash(String key, Map<String,String> hash, RedisCacheTime redisCacheTime) {
        Jedis jedis = null;
        try {

            //获得客户端
            jedis = jedisPool.getResource();
            if (hash == null) {
                return;
            }
            jedis.hmset(key, hash);
            if (redisCacheTime != null && redisCacheTime != RedisCacheTime.PERMANENT_STORE)
				jedis.expire(key, redisCacheTime.getSeconds());
            
        } catch (Exception e) {
			LOG.error("redis clearValue error", e);
		} finally {
            if(jedis != null)
                jedis.close();
        }
    }
    
    /**
     * 获取hash里面指定字段对应的值
     */
    public static String getHashValue(String key, String field) {
        Jedis jedis = null;
        try {
            //获得客户端
            jedis = jedisPool.getResource();
            return jedis.hget(key, field);
        } catch (Exception e) {
			LOG.error("redis getHashValue error", e);
			return null;
		} finally {
            if(jedis != null)
                jedis.close();
        }
    }

    /**
     * 获取hash中全部的域和值,以Map<String, String> 的形式返回
     */
	public static Map<String, String> getHashALL(String key) {
        Jedis jedis = null;
        try {

            //获得客户端
            jedis = jedisPool.getResource();

            return jedis.hgetAll(key);
        } catch (Exception e) {
			LOG.error("redis getHashALL error", e);
			return null;
		} finally {
            if(jedis != null)
                jedis.close();
        }
    }

    /**
     * 获取hash的所有元素(key值)
     */
    public static Set<String> getHashAllKey(String key) {
        Jedis jedis = null;
        try {
            //获得客户端
            jedis = jedisPool.getResource();
            return jedis.hkeys(key);
        } catch (Exception e) {
			LOG.error("redis getHashAllKey error", e);
			return null;
		} finally {
            if(jedis != null)
                jedis.close();
        }
    }

    /**
     * 获取hash中所有的key对应的value值
     */
    public static List<String> getHashAllValue(String key) {
        Jedis jedis = null;
        try {
            //获得客户端
            jedis = jedisPool.getResource();
            return jedis.hvals(key);
        } catch (Exception e) {
			LOG.error("redis getHashAllValue error", e);
			return null;
		} finally {
            if(jedis != null)
                jedis.close();
        }
    }

    /**
     * 获取hash里所有元素的数量
     */
    public static Long getHashNum(String key) {
        Jedis jedis = null;
        try {
            //获得客户端
            jedis = jedisPool.getResource();
            return jedis.hlen(key);
        } catch (Exception e) {
			LOG.error("redis getHashNum error", e);
			return null;
		} finally {
            if(jedis != null)
                jedis.close();
        }
    }

    /**
     * 判断给定key值是否存在于哈希集中
     */
    public static Boolean hexists(String key, String field) {
        Jedis jedis = null;
        try {
            //获得客户端
            jedis = jedisPool.getResource();
            return jedis.hexists(key, field);
        } catch (Exception e) {
			LOG.error("redis hexists error", e);
			return false;
		} finally {
            if(jedis != null)
                jedis.close();
        }
    }

    /**
     * 获取hash里面指定字段对应的值
     */
    public static List<String> hmget(String key, String... field) {
        Jedis jedis = null;
        try {
            //获得客户端
            jedis = jedisPool.getResource();
            return jedis.hmget(key, field);
        } catch (Exception e) {
			LOG.error("redis hmget error", e);
			return null;
		} finally {
            if(jedis != null)
                jedis.close();
        }
    }

    

    /**
     * 删除指定的值
     */
    public static Long delHashValue(String key, String... field) {
        Jedis jedis = null;
        try {
            //获得客户端
            jedis = jedisPool.getResource();
            return jedis.hdel(key, field);
        } catch (Exception e) {
			LOG.error("redis delHashValue error", e);
			return null;
		} finally {
            if(jedis != null)
                jedis.close();
        }
    }
    
	/**
	 * 设置过期时间
	 * @param key
	 * @param seconds
	 */
	public static void expire(String key, int seconds) {
		if (StringUtils.isBlank(key) || seconds < 1) {
			return;
		}
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.expire(key, seconds);
		} catch (Exception e) {
			LOG.error("redis expire error", e);
		} finally {
			if (jedis != null){
				jedis.close();
			}
		}
	}
	
	/**
	 * 向set集合添加一个或多个成员
	 * @param key
	 * @param members
	 * @return
	 */
	public static boolean addSet(String key, String[] members, RedisCacheTime redisCacheTime) {
		if(StringUtils.isBlank(key) || members==null || members.length==0){
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.sadd(key, members);
			if (redisCacheTime != null && redisCacheTime != RedisCacheTime.PERMANENT_STORE)
				jedis.expire(key, redisCacheTime.getSeconds());
			return true;
		} catch (Exception e) {
			LOG.error("redis sadd error", e);
		} finally {
			if (jedis != null){
				jedis.close();
			}
		}
		return false;
	}
	
	/**
	 * 移除set集合中一个或多个成员
	 * @param key
	 * @param members
	 * @return
	 */
	public static boolean removeSet(String key, String[] members) {
		if(StringUtils.isBlank(key) || members==null || members.length==0){
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.srem(key, members);
			return true;
		} catch (Exception e) {
			LOG.error("redis srem error", e);
		} finally {
			if (jedis != null){
				jedis.close();
			}
		}
		return false;
	}
	
	/**
	 * 判断 member元素是否是set集合key的成员
	 * @param key
	 * @param member
	 * @return
	 */
	public static boolean isSetMember(String key, String member) {
		if(StringUtils.isBlank(key) || member==null){
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.sismember(key, member);
		} catch (Exception e) {
			LOG.error("redis sismember error", e);
		} finally {
			if (jedis != null){
				jedis.close();
			}
		}
		return false;
	}
	
	/**
	 * 返回集合中的所有成员
	 * @param key
	 * @return
	 */
	public static Set<String> getSetAll(String key) {
		Set<String> arr = null;
		if(StringUtils.isBlank(key)){
			return arr;
		}
		Jedis jedis = null;
		try {
			jedis = getJedis();
			arr = jedis.smembers(key);
		} catch (Exception e) {
			LOG.error("redis sismember error", e);
		} finally {
			if (jedis != null){
				jedis.close();
			}
		}
		return arr;
	}
	
}
