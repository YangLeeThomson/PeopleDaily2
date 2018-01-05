/**
 * 
 */
package com.ronglian.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
@RestController
@RequestMapping("/api")
public class ContentAppriseController {

//	public RongLianResult contentLike(){
//		
//	}
	/**
	 * 用户对内容进行disLike操作
	 * @param newsId
	 * @param deviceId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/1.0/contentdislike",method=RequestMethod.POST)
	public RongLianResult contentDisLike(String newsId,String deviceId,String userId){
		return RongLianResult.ok();
	}
}
