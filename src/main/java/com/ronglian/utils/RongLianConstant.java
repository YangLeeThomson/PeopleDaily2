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
		public static final int REDIS_KEY_EXPIRE = 3000;
		
		//拦截器放行的uri(获取令牌tokenId的请求放行)
		public static final String TOKEN_URI = "/oauth/token";
		
		//redis缓存(登录accessToken)失效时间（1周）
		public static final int REDIS_ACCESS_TOKEN_EXPIRE = 604800;
		
		//栏目新闻列表失效时间（10秒钟--并发量500-1000）（半分钟--并发量200以下）
		public static final int REDIS_NEWS_EXPIRE = 1000;
		
		//新闻内容失效时间（30分钟）
		public static final int REDIS_NEWS_CONTENT_EXPIRE = 1800;
		
		//专题列表显示条目数(不超过4条)
		public static final int TOPIC_NEWS_NUMBER = 4;
		
		//通知Imedia系统的消息
//		public static final String ACCKNOWLEDAGE_IMEDIA_URL = "http://59.110.230.226/app_content_view.jspx";
		public static final String ACCKNOWLEDAGE_IMEDIA_URL = "http://172.16.1.33/app_content_view.jspx";
		
		//阿里巴巴图片适配服务器
		public static final String PICTURE_URL_START = "https://imedia-peoplesdaily.oss-cn-beijing.aliyuncs.com/";
		
		//阿里巴巴图片适配服务器
		public static final String PICTURE_URL_END = "?x-oss-process=image/info";
		
}
