/**
 * 
 */
package com.ronglian.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.UserChannelConfig;
import com.ronglian.service.UserChannelConfigService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
@RestController
@RequestMapping("/api")
public class ChannelChooseController {

	@Autowired
	private UserChannelConfigService configService;
	/**
	 * 用户订阅栏目管理接口
	 * */
	@RequestMapping(value="/1.0/channelfavorites",method=RequestMethod.POST)
	public RongLianResult chooseChannels(@RequestBody RongLianRequest<List<UserChannelConfig>> listBody){
		List<UserChannelConfig> list = null;
		if(listBody != null){
			list = listBody.getData();
		}
		return configService.addUserChannelConfig(list);
	}
	
	/**
	 * 获取用户自定义订阅栏目
	 */ 
	@RequestMapping(value="/1.0/getUserChannelFavorites",method=RequestMethod.GET)
	public RongLianResult getChannels(String deviceId,String userId){
		return this.configService.getUserChannelConfig(deviceId,userId);
	}
}
