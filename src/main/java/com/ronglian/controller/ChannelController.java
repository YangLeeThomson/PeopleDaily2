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
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017-12-22
 */
@RestController
@RequestMapping("/api")
public class ChannelController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);
	@Autowired
	private ChannelService channelService;
	/**
	 * 栏目列表输出接口
	 * */
	@RequestMapping(value="/1.0/channels",method=RequestMethod.GET)
	public RongLianResult getChannel(){
		   logger.info("获取栏目列表");
	        return channelService.getChannelList();
	}
	/**
	 * iMedia设置channel
	 * */
	@RequestMapping(value="/1.0/setChannel",method=RequestMethod.POST)
	public RongLianResult addChannel(@RequestBody RongLianRequest<Map> requestBody){
		return channelService.addChannelMap(requestBody.getData());
	}
	
}
