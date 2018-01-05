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
 * @createTime 2017Äê12ÔÂ29ÈÕ
 */
@RestController
@RequestMapping("/api")
public class CommentAppriseController {

	@RequestMapping(value="/1.0/commentApprise",method=RequestMethod.POST)
	public RongLianResult addCommentApprise(String commentId,String deviceId,String userId){
		return RongLianResult.ok();
	}
}
