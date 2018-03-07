/**
 * 
 */
package com.ronglian.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.NewsInfoApprise;
import com.ronglian.entity.User;
import com.ronglian.service.AppriseService;
import com.ronglian.service.UserService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2018年1月2日
 */
@RestController
@RequestMapping("/api")
public class AppriseController {

	@Autowired
	private AppriseService appriseService;
	@Autowired
	private UserService userService;

	/**
	 * 点赞接口
	 * 
	 * good:1,点赞 good：-1，吐槽
	 */
	@RequestMapping(value = "/1.0/connectapprises", method = RequestMethod.POST)
	public RongLianResult addApprise(@RequestBody RongLianRequest<NewsInfoApprise> appriseBody) {
		NewsInfoApprise apprise = null;
		String accessToken = null;
		String userId = null;
		if (appriseBody != null) {
			apprise = appriseBody.getData();
			accessToken = appriseBody.getAccessToken();
		}
		// 获取请求的userId
		if (apprise != null) {
			userId = apprise.getUserId();
		}
		// 登录信息校验
		if (StringUtils.isNotBlank(accessToken)) {
			RongLianResult result = this.userService.getUserInfo(accessToken);
			if (result.getData() == null) {
				return RongLianResult.build(106, "accessToken is error");
			}
			User user = (User) result.getData();
			String userId2 = user.getUserId();
			if (!userId2.equals(userId)) {
				return RongLianResult.build(200, "maybe param userId is error");
			}
		}
		// 未登录时，信息校验
		if (StringUtils.isBlank(accessToken) && StringUtils.isNotBlank(userId)) {
			return RongLianResult.build(200, "you have not logined ,so userId should be null ");
		}
		try {
			return this.appriseService.addNewsInfoApprise(apprise);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "apprise failed");
		}
	}

	/**
	 * 取消点赞接口
	 */
	@RequestMapping(value = "/1.0/cancleapprises", method = RequestMethod.POST)
	public RongLianResult cancleApprise(@RequestBody RongLianRequest<NewsInfoApprise> appriseBody) {
		NewsInfoApprise apprise = null;
		String accessToken = null;
		String userId = null;
		if (appriseBody != null) {
			apprise = appriseBody.getData();
			accessToken = appriseBody.getAccessToken();
		}

		// 获取请求的userId
		if (apprise != null) {
			userId = apprise.getUserId();
		}
		// 登录信息校验
		if (StringUtils.isNotBlank(accessToken)) {
			RongLianResult result = this.userService.getUserInfo(accessToken);
			if (result.getData() == null) {
				return RongLianResult.build(106, "accessToken is error");
			}
			User user = (User) result.getData();
			String userId2 = user.getUserId();
			if (!userId2.equals(userId)) {
				return RongLianResult.build(200, "maybe param userId is error");
			}
		}
		// 未登录时，信息校验
		if (StringUtils.isBlank(accessToken) && StringUtils.isNotBlank(userId)) {
			return RongLianResult.build(200, "you have not logined ,so userId should be null ");
		}
		try {
			return this.appriseService.removeApprise(apprise);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "cancle apprises failed");
		}
	}

	/**
	 * 获取点赞列表
	 */
	@RequestMapping(value = "/1.0/appriselist", method = RequestMethod.GET)
	public RongLianResult getAppriseList(String deviceId, String userId, String accessToken) {
		// 登录信息校验
		if (StringUtils.isNotBlank(accessToken)) {
			RongLianResult result = this.userService.getUserInfo(accessToken);
			if (result.getData() == null) {
				return RongLianResult.build(106, "accessToken is error");
			}
			User user = (User) result.getData();
			String userId2 = user.getUserId();
			if (!userId2.equals(userId)) {
				return RongLianResult.build(200, "maybe param userId is error");
			}
		}
		// 未登录时，信息校验
		if (StringUtils.isBlank(accessToken) && StringUtils.isNotBlank(userId)) {
			return RongLianResult.build(200, "you have not logined ,so userId should be null ");
		}

		try {
			return this.appriseService.getAppriseList(deviceId, userId);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "failed to catch appriselist");
		}
	}
}
