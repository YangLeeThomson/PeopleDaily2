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
	public static final int REDIS_KEY_EXPIRE = 3000;
	
	//���������е�uri(��ȡ����tokenId���������)
	public static final String TOKEN_URI = "/oauth/token";
	
	//redis����(��¼accessToken)ʧЧʱ�䣨1�ܣ�
	public static final int REDIS_ACCESS_TOKEN_EXPIRE = 604800;
	
	//ר���б���ʾ��Ŀ��(������4��)
	public static final int TOPIC_NEWS_NUMBER = 4;
}
