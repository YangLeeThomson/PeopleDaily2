package com.ronglian.utils;

/**
 * @author liyang
 * @createTime 2017-12-18
 * */
public class RongLianConstant {

	//���ܵ���salt
	public static final String SALT = "ronglian";
	
	//�ӿ���֤ʱ����İ�ȫ�����120���ӣ�
	public static final long INTERVAL = 120*60;
	
	//redis����(����tokenId)ʧЧʱ�䣨30���ӣ�
	public static final int REDIS_KEY_EXPIRE = 1800;
	
	//���������е�uri(��ȡ����tokenId���������)
	public static final String TOKEN_URI = "/oauth/token";
	
	//redis����(��¼accessToken)ʧЧʱ�䣨1�ܣ�
	public static final int REDIS_ACCESS_TOKEN_EXPIRE = 604800;
}
