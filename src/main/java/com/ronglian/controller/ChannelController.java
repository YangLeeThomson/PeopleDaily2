package com.ronglian.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.Channel;
import com.ronglian.service.ChannelService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017年12月22日
 */
@RestController
@RequestMapping("/api")
public class ChannelController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);
	@Autowired
	private ChannelService channelService;
	/**
	 * 栏目列表接口
	 * */
	@RequestMapping(value="/1.0/channels",method=RequestMethod.GET)
	public RongLianResult getChannel(){
		   logger.info("读取Channel集合");
	        return channelService.getChannelList();
	}
	/**
	 * iMedia后台同步栏目接口
	 * */
	@RequestMapping(value="/1.0/setChannel",method=RequestMethod.POST)
	public RongLianResult addChannel(@RequestBody Map requestMap){
		//获取request请求参数
		int id = (int) requestMap.get("id");
		String name = (String) requestMap.get("name");
		String uniqueID = (String) requestMap.get("uniqueID");
		int sort = (int) requestMap.get("sort");
		int dataStatus = (int) requestMap.get("dataStatus");
		//封装channel,补全数据
		Channel channel = new Channel();
		Date createTime = new Date();
		Date modiyTime = new Date();
		channel.setChannelId(id);
		channel.setChannelName(name);
		channel.setChannelSort(sort);
		channel.setIsShow(dataStatus);
		channel.setCreateTime(createTime);
		channel.setModiyTime(modiyTime);
		
//		return channelService.addChannel(channel);
		return RongLianResult.ok();
	}
	@RequestMapping(value="/1.0/channels",method=RequestMethod.DELETE)
	public String deleteChannel(HttpServletRequest request){
		return "";
	}
	@RequestMapping(value="/1.0/channels",method=RequestMethod.PUT)
	public String updateChannel(HttpServletRequest request){
		return "";
	}
}
