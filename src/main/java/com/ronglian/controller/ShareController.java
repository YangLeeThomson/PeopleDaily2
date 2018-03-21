/**
 * 
 */
package com.ronglian.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.NewsShare;
import com.ronglian.entity.User;
import com.ronglian.service.NewsShareService;
import com.ronglian.service.UserService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2018年1月2日
 */
@RestController
@RequestMapping("/api")
public class ShareController {

	
	@Autowired
	private NewsShareService newsShareService;
	@Autowired
	private UserService userService;
	/**
	 * 用户分享文章计数接口
	 * */
	@RequestMapping(value="/1.0/sharecount",method=RequestMethod.POST)
	public RongLianResult ShareCount(@RequestBody RongLianRequest<NewsShare> newsShareBody){
		NewsShare newsShare = null;
		String accessToken = null;
		String userId = null;
		if(newsShareBody != null){
			newsShare = newsShareBody.getData();
			accessToken = newsShareBody.getAccessToken();
		}
		//获取请求的userId
		if(newsShare != null){
			userId = newsShare.getUserId();
		}
		//登录信息校验
		if(StringUtils.isNotBlank(accessToken)){
			RongLianResult  result = this.userService.getUserInfo(accessToken);
			if(result.getData() == null){
				return RongLianResult.build(106, "accessToken is error");
			}
			User user = (User) result.getData();
			String userId2 = user.getUserId();
			if(!userId2.equals(userId)){
				return RongLianResult.build(200, "maybe param userId is error");
			}	
		}
		//未登录时，信息校验
		if(StringUtils.isBlank(accessToken) && StringUtils.isNotBlank(userId)){
			return RongLianResult.build(200,"you have not logined ,so userId should be null ");
		}
		try {
			return this.newsShareService.countNewsShare(newsShare);
		} catch (Exception e) {
			return RongLianResult.build(500, "统计服务失败！");
		}
	}
}
