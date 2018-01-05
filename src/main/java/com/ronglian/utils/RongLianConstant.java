package com.ronglian.utils;

/**
 * @author liyang
 * @createTime 2017-12-18
 * */
public class RongLianConstant {

	//加密的盐salt
	public static final String SALT = "ronglian";
	
	//接口认证时间戳的安全间隔（30分钟）
	public static final long INTERVAL = 30*60;
	
	//redis缓存失效时间（30分钟）
	public static final int REDIS_KEY_EXPIRE = 1800;
	
	//拦截器放行的uri(获取令牌tokenId的请求放行)
	public static final String TOKEN_URI = "/oauth/token";
}
