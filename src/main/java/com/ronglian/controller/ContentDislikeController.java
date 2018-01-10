/**
 * 
 */
package com.ronglian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.NewsInfoDislike;
import com.ronglian.service.ContentDislikeService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��29��
 */
@RestController
@RequestMapping("/api")
public class ContentDislikeController {

	/**
	 * �û������ݽ���disLike����
	 * @param newsId
	 * @param deviceId
	 * @param userId
	 * @return
	 */
	@Autowired
	private ContentDislikeService contentDislikeService;
	@RequestMapping(value="/1.0/contentdislike",method=RequestMethod.POST)
	public RongLianResult contentDisLike(@RequestBody NewsInfoDislike newsinfoDislike){
		return contentDislikeService.addContentDislike(newsinfoDislike);
	}
}
