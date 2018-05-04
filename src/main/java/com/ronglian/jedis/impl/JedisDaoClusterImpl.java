package com.ronglian.jedis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ronglian.jedis.JedisDao;

import redis.clients.jedis.JedisCluster;
/**
 * @author liyang
 * @createTime 2017��12��22��
 */
//@Repository��ʱ����spring����������redis��Ⱥʱ�ſ�ע�ͣ��ر�JedisDaoSingleImpl
public class JedisDaoClusterImpl implements JedisDao {
//	@Autowired
	private JedisCluster jedisCluster;
	
	@Override
	public String set(String key, String value) {
		String str = jedisCluster.set(key, value);
		return str;
	}

	@Override
	public String get(String key) {
		String str = jedisCluster.get(key);
		return str;
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		long str = jedisCluster.hset(hkey, key, value);
		return str;
	}

	@Override
	public String hget(String hkey, String key) {
		String str = jedisCluster.hget(hkey, key);
		return str;
	}

	@Override
	public Long expire(String key, int second) {
		long str = jedisCluster.expire(key, second);
		return str;
	}

	@Override
	public Long ttl(String key) {
		long str = jedisCluster.ttl(key);
		return str;
	}

	@Override
	public Long del(String key) {
		long str = jedisCluster.del(key);
		return str;
	}

	@Override
	public Long hdel(String hkey, String key) {
		long str = jedisCluster.hdel(hkey, key);
		return str;
	}

	@Override
	public void remove(String pattern) {
		// TODO Auto-generated method stub
		
	}

}
