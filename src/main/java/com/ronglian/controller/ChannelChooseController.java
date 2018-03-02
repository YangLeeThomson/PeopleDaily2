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

import com.ronglian.entity.User;
import com.ronglian.entity.UserChannelConfig;
import com.ronglian.service.UserChannelConfigService;
import com.ronglian.service.UserService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017��12��29��
 */
@RestController
@RequestMapping("/api")
public class ChannelChooseController {

	@Autowired
	private UserChannelConfigService configService;
	@Autowired
	private UserService userService;
	/**
	 * �û�������Ŀ����ӿ�
	 * */
	@RequestMapping(value="/1.0/channelfavorites",method=RequestMethod.POST)
	public RongLianResult chooseChannels(@RequestBody RongLianRequest<List<UserChannelConfig>> listBody){
		List<UserChannelConfig> list = null;
		String accessToken = null;
		String userId = null;
		if(listBody != null){
			list = listBody.getData();
			accessToken = listBody.getAccessToken();
		}

	
		//��ȡ�����userId
		if(list != null){
			userId = list.get(0).getUserId();
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
		return configService.addUserChannelConfig(list);
	}
	
	/**
	 * ��ȡ�û��Զ��嶩����Ŀ
	 */ 
	@RequestMapping(value="/1.0/getUserChannelFavorites",method=RequestMethod.GET)
	public RongLianResult getChannels(String deviceId,String userId,String accessToken){
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
		return this.configService.getUserChannelConfig(deviceId,userId);
	}
}
