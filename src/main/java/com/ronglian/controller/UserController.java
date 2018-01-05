package com.ronglian.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.dao.UserDao;
import com.ronglian.entity.User;
import com.ronglian.service.UserService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017年12月22日
 */
@RestController
@RequestMapping("/api")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	public List<User> getUserList(){
		logger.info("从数据库读取User集合");
		return userDao.getList();
	}
	/**
	 * 用户登录注册接口
	 * */
	@RequestMapping(value="/1.0/userslogin",method=RequestMethod.POST)
	public RongLianResult loginOrRegist(String photoUrl,String nickName,String openId,String deviceId){
		return this.userService.login(photoUrl, nickName, openId,deviceId);
	}
	/**
	 * 用户退出系统
	 * */
	@RequestMapping(value="/1.0/userlogout",method=RequestMethod.POST)
	public RongLianResult quitOut(String tokenId){
		return RongLianResult.ok();
	}
}