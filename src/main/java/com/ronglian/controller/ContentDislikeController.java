/**
 * 
 */
package com.ronglian.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.NewsInfoDislike;
import com.ronglian.entity.User;
import com.ronglian.service.ContentDislikeService;
import com.ronglian.service.UserService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
@RestController
@RequestMapping("/api")
public class ContentDislikeController {

	/**
	 * 用户对内容进行disLike操作
	 * @param newsId
	 * @param deviceId
	 * @param userId
	 * @return
	 */
	@Autowired
	private ContentDislikeService contentDislikeService;
	@Autowired
	private UserService userService;
	@RequestMapping(value="/1.0/contentdislike",method=RequestMethod.POST)
	public RongLianResult contentDisLike(@RequestBody RongLianRequest<NewsInfoDislike> newsinfoDislikeBody){
		NewsInfoDislike newsinfoDislike = null;
		String accessToken = null;
		String userId = null;
		if(newsinfoDislikeBody != null){
			newsinfoDislike = newsinfoDislikeBody.getData();
			accessToken = newsinfoDislikeBody.getAccessToken();
		}
		//获取请求的userId
		if(newsinfoDislike != null){
			userId = newsinfoDislike.getUserId();
		}
		//登录信息校验
		if(StringUtils.isNotBlank(accessToken)){
			RongLianResult  result = this.userService.getUserInfo(accessToken);
			if(result.getData() == null){
				return RongLianResult.build(200, "accessToken is error");
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
		return contentDislikeService.addContentDislike(newsinfoDislike);
	}
}
