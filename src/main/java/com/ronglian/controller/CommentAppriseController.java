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

import com.ronglian.entity.NewsCommentApprise;
import com.ronglian.entity.User;
import com.ronglian.service.CommentAppriseService;
import com.ronglian.service.UserService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
@RestController
@RequestMapping("/api")
public class CommentAppriseController {

	@Autowired
	private CommentAppriseService commentAppriseService;
	@Autowired
	private UserService userService;
	/**
	 * 用户对评论进行点赞接口
	 * */
	@RequestMapping(value="/1.0/commentApprise",method=RequestMethod.POST)
	public RongLianResult addCommentApprise(@RequestBody RongLianRequest<NewsCommentApprise> commentAppriseBody){
		NewsCommentApprise commentApprise = null;
		String accessToken = null;
		String userId = null;
		if(commentAppriseBody != null){
			commentApprise = commentAppriseBody.getData();
			accessToken = commentAppriseBody.getAccessToken();
		}
		//获取请求的userId
		if(commentApprise != null){
			userId = commentApprise.getUserId();
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
		return this.commentAppriseService.addCommentApprise(commentApprise);
	}
}
