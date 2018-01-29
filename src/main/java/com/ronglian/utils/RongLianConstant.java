package com.ronglian.utils;

/**
 * @author liyang
 * @createTime 2017-12-18
 * */
public class RongLianConstant {

	//加密的盐salt
	public static final String SALT = "ronglian";
	
	//接口认证时间戳的安全间隔（120分钟）
	public static final long INTERVAL = 120*60;
	
	//redis缓存(令牌tokenId)失效时间（30分钟）
	public static final int REDIS_KEY_EXPIRE = 1800;
	
	//拦截器放行的uri(获取令牌tokenId的请求放行)
	public static final String TOKEN_URI = "/oauth/token";
	
	//redis缓存(登录accessToken)失效时间（1周）
	public static final int REDIS_ACCESS_TOKEN_EXPIRE = 604800;
}
