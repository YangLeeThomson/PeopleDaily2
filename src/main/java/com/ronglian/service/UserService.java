/**
 * 
 */
package com.ronglian.service;

import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��29��
 */
public interface UserService {

	public RongLianResult login(String photoUrl,String nickName,String openId,String deviceId);
}
