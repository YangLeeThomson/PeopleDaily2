package com.ronglian.jedis;

import java.util.Set;

/**
 * @author liyang
 * @createTime 2017-12-22
 */
public interface JedisDao {
	public String set(String key,String value);
	public String get(String key);
	public Long hset(String hkey,String key,String value);
	public String hget(String hkey,String key);
	public Long expire(String key,int second);
	public Long ttl(String key);
	public Long del(String key);
	public Long hdel(String hkey,String key);
	public void remove(String pattern);
	public Set<String> getKeys(String pattern);
	
}
