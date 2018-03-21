package com.ronglian.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.service.APPManagerConfigService;
import com.ronglian.utils.RongLianResult;
/**
 * @author liyang
 * @createtimeStamp 2017-12-20
 * @modifytimeStamp 2017-12-26
 * 获取令牌tokenId
 * */
@RestController
@RequestMapping("/oauth")
public class TokenController {

	@Autowired
	private APPManagerConfigService configService;
	//根据appId获取令牌
	@RequestMapping(value="/token",method=RequestMethod.POST)
	public RongLianResult getTokenIdByAppId(@RequestBody Map params){
//		public RongLianResult getTokenIdByAppId(@RequestBody String appId, @RequestBody String timeStamp,@RequestBody String sign){
		String appId = (String) params.get("appId");
//		String num = params.get("timeStamp").toString();
		
//		long bigNum = (long)num;
//		String timeStamp = Long.toString(bigNum);
		String timeStamp = params.get("timeStamp").toString();
		String sign = (String) params.get("sign");
		String tokenId = configService.getTokenByAppId(appId, timeStamp, sign);
		Map resultMap = new HashMap<>();
		if(!StringUtils.isBlank(tokenId)){
			resultMap.put("tokenId", tokenId);
			return RongLianResult.ok(resultMap);
		}else{
			return RongLianResult.build(107, "appID账号或签名sign有误！");
		}
	}	
}
