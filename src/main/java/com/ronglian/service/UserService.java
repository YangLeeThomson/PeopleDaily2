/**
 * 
 */
package com.ronglian.service;

import com.ronglian.entity.User;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��29��
 */
public interface UserService {
	/**
	 * @param user
	 * @return
	 */
	public RongLianResult  login(User user);

	public RongLianResult getUserInfo(String accessToken);
	
	public RongLianResult quit(String accessToken);
}
