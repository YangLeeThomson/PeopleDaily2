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
 * @createTime 2017骞�12鏈�22鏃�
 */
@RestController
@RequestMapping("/api")
public class ChannelController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);
	@Autowired
	private ChannelService channelService;
	/**
	 * 鏍忕洰鍒楄〃鎺ュ彛
	 * */
	@RequestMapping(value="/1.0/channels",method=RequestMethod.GET)
	public RongLianResult getChannel(){
		   logger.info("璇诲彇Channel闆嗗悎");
	        return channelService.getChannelList();
	}
	/**
	 * iMedia设置channel
	 * */
	@RequestMapping(value="/1.0/setChannel",method=RequestMethod.POST)
	public RongLianResult addChannel(@RequestBody Map requestMap){
		return channelService.addChannelMap(requestMap);
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
