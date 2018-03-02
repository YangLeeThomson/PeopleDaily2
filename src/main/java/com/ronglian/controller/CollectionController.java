/**
 * 
 */
package com.ronglian.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.Collection;
import com.ronglian.entity.User;
import com.ronglian.service.CollectionService;
import com.ronglian.service.UserService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
@RestController
@RequestMapping("/api")
public class CollectionController {

	@Autowired
	private CollectionService collectionService;
	@Autowired
	private UserService userService;

	/**
	 * 用户内容收藏接口
	 */
	@RequestMapping(value = "/1.0/usercollection", method = RequestMethod.POST)
	public RongLianResult addCollection(@RequestBody RongLianRequest<Collection> collectionBody) {
		Collection collection = null;
		String accessToken = null;
		String userId = null;
		if (collectionBody != null) {
			collection = collectionBody.getData();
			accessToken = collectionBody.getAccessToken();
		}
		// 获取请求的userId
		if (collection != null) {
			userId = collection.getUserId();
		}
		// 登录信息校验
		if (StringUtils.isNotBlank(accessToken)) {
			RongLianResult result = this.userService.getUserInfo(accessToken);
			if (result.getData() == null) {
				return RongLianResult.build(200, "accessToken is error");
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
			return collectionService.insertUserCollection(collection);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "save failed");
		}
	}

	@RequestMapping(value = "/1.0/collectionList", method = RequestMethod.GET)
	public RongLianResult getCollection(String deviceId, String userId, String accessToken) {
		// 登录信息校验
		if (StringUtils.isNotBlank(accessToken)) {
			RongLianResult result = this.userService.getUserInfo(accessToken);
			if (result.getData() == null) {
				return RongLianResult.build(200, "accessToken is error");
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
			return this.collectionService.getUserCollection(userId, deviceId);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "server error");
		}

	}

}
