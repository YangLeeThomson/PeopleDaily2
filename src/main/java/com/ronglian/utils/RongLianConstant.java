package com.ronglian.utils;

/**
 * @author liyang
 * @createTime 2017-12-18
 * */
public class RongLianConstant {

	//���ܵ���salt
	public static final String SALT = "ronglian";
	
	//�ӿ���֤ʱ����İ�ȫ�����30���ӣ�
	public static final long INTERVAL = 30*60;
	
	//redis����ʧЧʱ�䣨30���ӣ�
	public static final int REDIS_KEY_EXPIRE = 1800;
	
	//���������е�uri(��ȡ����tokenId���������)
	public static final String TOKEN_URI = "/oauth/token";
}
