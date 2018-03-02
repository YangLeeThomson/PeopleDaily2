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
 * @createTime 2018��1��2��
 */
@RestController
@RequestMapping("/api")
public class ShareController {

	/**
	 * �û��������¼����ӿ�
	 * */
	@Autowired
	private NewsShareService newsShareService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/1.0/sharecount",method=RequestMethod.POST)
	public RongLianResult ShareCount(@RequestBody RongLianRequest<NewsShare> newsShareBody){
		NewsShare newsShare = null;
		String accessToken = null;
		String userId = null;
		if(newsShareBody != null){
			newsShare = newsShareBody.getData();
			accessToken = newsShareBody.getAccessToken();
		}
		//��ȡ�����userId
		if(newsShare != null){
			userId = newsShare.getUserId();
		}
		//��¼��ϢУ��
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
		//δ��¼ʱ����ϢУ��
		if(StringUtils.isBlank(accessToken) && StringUtils.isNotBlank(userId)){
			return RongLianResult.build(200,"you have not logined ,so userId should be null ");
		}
		try {
			return this.newsShareService.countNewsShare(newsShare);
		} catch (Exception e) {
			// TODO: handle exception
//			System.out.println(e.getMessage());
			return RongLianResult.build(500, "ͳ�Ʒ���ʧ�ܣ�");
		}
	}
}
