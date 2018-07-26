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
		
		//新闻内容失效时间（24小时）***注意***必须配置为24小时，不可更改！否则maybe丢数据
		public static final int REDIS_NEWS_CONTENT_EXPIRE = 24*60*60;
		
		//专题列表显示条目数(不超过4条)
		public static final int TOPIC_NEWS_NUMBER = 4;
		
		//通知Imedia系统的消息
//		public static final String ACCKNOWLEDAGE_IMEDIA_URL = "http://59.110.230.226/app_content_view.jspx";
		public static final String ACCKNOWLEDAGE_IMEDIA_URL = "http://172.16.1.33/app_content_view.jspx";
//		public static final String ACCKNOWLEDAGE_IMEDIA_URL = "http://10.6.4.132:8080/app_content_view.jspx";
		
		//阿里巴巴图片适配服务器
		public static final String PICTURE_URL_START = "https://imedia-peoplesdaily.oss-cn-beijing.aliyuncs.com/";
		
		//阿里巴巴图片适配服务器
		public static final String PICTURE_URL_END = "?x-oss-process=image/info";
		
		//Google adwords服务，推送设备信息
		public static final String GOOGLE_ADWORDS_URL = "https://www.googleadservices.com/pagead/conversion/app/1.0";
		
		//Google link id for ios
		public static final String iOS_KEY = "D3624E74F7002A020C937AB87BF82FC8";
		//Google link id for android
		public static final String Android_KEY = "B89535BA3B846B9773D343674F64CE91";
		//Google dev_token
		public static final String DEV_TOKEN = "uE4EDjoAQKyKWWulrvyqpQ";
		//app_event_type
		public static final String APP_EVENT_TYPE = "first_open";
}
