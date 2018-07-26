/**   
 * Copyright © 2018 北京荣之联科技股份有限公司 All rights reserved.
 * 
 * @Package: com.ronglian.controller 
 * @author: YeohLee   
 * @date: 2018年7月26日 上午11:01:36 
 */
package com.ronglian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.Device;
import com.ronglian.service.GoogleService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

 /** 
 * @ClassName: GoogleController 
 * @Description: 推送谷歌用户信息
 * @author: YeohLee
 * @date: 2018年7月26日 上午11:01:36  
 */
@RestController
@RequestMapping("/api/1.0")
public class GoogleController {

	@Autowired
	private GoogleService googleService;
	@RequestMapping(value="/setDeviceInfo",method=RequestMethod.POST)
	public RongLianResult pushToGoogle(@RequestBody RongLianRequest<Device> request){
		return this.googleService.pushToGoogle(request.getData());
	}
}
