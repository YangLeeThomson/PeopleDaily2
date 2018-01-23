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
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
@RestController
@RequestMapping("/api")
public class ContentDislikeController {

	/**
	 * 用户对内容进行disLike操作
	 * @param newsId
	 * @param deviceId
	 * @param userId
	 * @return
	 */
	@Autowired
	private ContentDislikeService contentDislikeService;
	@RequestMapping(value="/1.0/contentdislike",method=RequestMethod.POST)
	public RongLianResult contentDisLike(@RequestBody RongLianRequest<NewsInfoDislike> newsinfoDislikeBody){
		NewsInfoDislike newsinfoDislike = null;
		if(newsinfoDislikeBody != null){
			newsinfoDislike = newsinfoDislikeBody.getObj();
		}
		return contentDislikeService.addContentDislike(newsinfoDislike);
	}
}
