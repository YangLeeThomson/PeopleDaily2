/**
 * 
 */
package com.ronglian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.UserDao;
import com.ronglian.service.UserService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��29��
 */
@Service
public class UserServiceImpl implements UserService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.UserService#login(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Autowired
	private UserDao userDao;
	@Override
	public RongLianResult login(String photoUrl, String nickName, String openId,String deviceId) {
		//ȷ��deviceId����,���openid��������ϢΪnull������deviceId���ٵ�¼��
		
		//�ж�����ע�ᣬ��ע�ᣬֱ�ӵ�¼
		
		//���ûע�ᣬ��ע�ᣬ�ٵ�½
		return null;
	}

}
