package com.ronglian.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.dao.UserDao;
import com.ronglian.entity.User;
import com.ronglian.service.UserService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017年12月22日
 */
@RestController
@RequestMapping("/api")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录注册接口
	 * */
	@RequestMapping(value="/1.0/userlogin",method=RequestMethod.POST)
	public RongLianResult loginOrRegist(@RequestBody RongLianRequest<User> userBody){
		User user = null;
		if(userBody != null){
			user = userBody.getData();
		}
		return this.userService.login(user);
	}
	/**
	 * 用户个人信息接口
	 * @param accessToken
	 * @return
	 */
	@RequestMapping(value="/1.0/userinfo",method=RequestMethod.GET)
	public RongLianResult getUserInfo(String accessToken){
		return this.userService.getUserInfo(accessToken);
	}
	/**
	 * 用户退出系统接口
	 * */
	@RequestMapping(value="/1.0/userlogout",method=RequestMethod.GET)
	public RongLianResult quitOut(String accessToken){
		return this.userService.quit(accessToken);
	}
}