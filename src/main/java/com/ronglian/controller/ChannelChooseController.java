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
	 * 设置用户自定义订阅栏目
	 * */
	@RequestMapping(value="/1.0/channelfavorites",method=RequestMethod.POST)
	public RongLianResult chooseChannels(@RequestBody List<UserChannelConfig> list){
//		return configService.addUserChannelConfig(list);
		return RongLianResult.ok();
	}
	
	/**
	 * 获取用户自定义订阅栏目
	 */ 
	@RequestMapping(value="/1.0/getUserChannelFavorites",method=RequestMethod.GET)
	public RongLianResult getChannels(String deviceId,String userId){
//		return this.configService.getUserChannelConfig(deviceId,tokenId,userId);
        Map map = new HashMap();
        map.put("channelId", 4);
        map.put("channelName","TopNews");
        map.put("selfSort",5);
        
        Map map2 = new HashMap();
        map2.put("channelId", 6);
        map2.put("channelName","China");
        map2.put("selfSort",4);
        
        List list = new LinkedList();
        list.add(map2);
        list.add(map);
		return RongLianResult.ok(list);
	}
}
