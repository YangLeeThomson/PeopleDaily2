package com.ronglian.jedis;
/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ22ÈÕ
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
}
