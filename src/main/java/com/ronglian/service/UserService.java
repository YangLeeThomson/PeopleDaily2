/**
 * 
 */
package com.ronglian.service;

import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ29ÈÕ
 */
public interface UserService {

	public RongLianResult login(String photoUrl,String nickName,String openId,String deviceId);
}
