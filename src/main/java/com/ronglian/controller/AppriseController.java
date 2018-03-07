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
 * @createTime 2018��1��2��
 */
@RestController
@RequestMapping("/api")
public class AppriseController {

	@Autowired
	private AppriseService appriseService;
	@Autowired
	private UserService userService;

	/**
	 * ���޽ӿ�
	 * 
	 * good:1,���� good��-1���²�
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
		// ��ȡ�����userId
		if (apprise != null) {
			userId = apprise.getUserId();
		}
		// ��¼��ϢУ��
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
		// δ��¼ʱ����ϢУ��
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
	 * ȡ�����޽ӿ�
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

		// ��ȡ�����userId
		if (apprise != null) {
			userId = apprise.getUserId();
		}
		// ��¼��ϢУ��
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
		// δ��¼ʱ����ϢУ��
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
	 * ��ȡ�����б�
	 */
	@RequestMapping(value = "/1.0/appriselist", method = RequestMethod.GET)
	public RongLianResult getAppriseList(String deviceId, String userId, String accessToken) {
		// ��¼��ϢУ��
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
		// δ��¼ʱ����ϢУ��
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
