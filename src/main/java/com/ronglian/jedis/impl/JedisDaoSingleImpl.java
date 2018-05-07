package com.ronglian.jedis.impl;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ronglian.jedis.JedisDao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * @author liyang
 * @createTime 2017��12��22��
 */
@Repository
public class JedisDaoSingleImpl implements JedisDao {
	
	@Autowired
	private JedisPool jedisPool;
	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String str = jedis.set(key, value);
		jedis.close();
		return str;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String str = jedis.get(key);
		jedis.close();
		return str;
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		long str = jedis.hset(hkey, key, value);
		jedis.close();
		return str;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String str = jedis.hget(hkey, key);
		jedis.close();
		return str;
	}

	@Override
	public Long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		long str = jedis.expire(key, second);
		jedis.close();
		return str;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		long str = jedis.ttl(key);
		jedis.close();
		return str;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = jedisPool.getResource();
		long str = jedis.del(key);
		jedis.close();
		return str;
	}

	@Override
	public Long hdel(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		long str = jedis.hdel(hkey, key);
		jedis.close();
		return str;
	}
	/**
	 * remove(pattern),移除统配的key
	 * pattern必须含有“*”符号
	 */
	@Override
	public void remove(String pattern) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Set<String> set = jedis.keys(pattern);
		Iterator<String> it = set.iterator();
        while(it.hasNext()){  
            String keyStr = it.next();  
            jedis.del(keyStr);  
        }  
        jedis.close();
	}

	
}
