package com.ronglian.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * @author liyang
 * 
 * @createTime 2017-12-21
 * Redis
 */
@Configuration
@EnableAutoConfiguration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport{

		@Bean(name= "jedis.pool")  
	    @Autowired  
	    public JedisPool jedisPool(@Qualifier("jedis.pool.config") JedisPoolConfig config,   
	                @Value("${jedis.pool.host}")String host,   
	                @Value("${jedis.pool.port}")int port,
	                @Value("${jedis.pool.timeout}")int timeout,
	                @Value("${jedis.pool.password}")String password,
	                @Value("${jedis.pool.database}")int database) {  
		
	        return new JedisPool(config, host, port, timeout, password, database);
	        
	    }  
	      
	    @Bean(name= "jedis.pool.config")  
	    public JedisPoolConfig jedisPoolConfig (@Value("${jedis.pool.config.maxTotal}")int maxTotal,  
	                                @Value("${jedis.pool.config.maxIdle}")int maxIdle,  
	                                @Value("${jedis.pool.config.maxWaitMillis}")int maxWaitMillis) {  
	        JedisPoolConfig config = new JedisPoolConfig();  
	        config.setMaxTotal(maxTotal);  
	        config.setMaxIdle(maxIdle);  
	        config.setMaxWaitMillis(maxWaitMillis);  
	        return config;  
	    }  
}
