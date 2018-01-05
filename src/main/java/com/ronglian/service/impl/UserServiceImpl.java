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
 * @createTime 2017年12月29日
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
		//确保deviceId存在,如果openid等三方信息为null，利用deviceId“假登录”
		
		//判断有无注册，有注册，直接登录
		
		//如果没注册，先注册，再登陆
		return null;
	}

}
