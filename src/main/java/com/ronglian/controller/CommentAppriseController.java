/**
 * 
 */
package com.ronglian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.NewsCommentApprise;
import com.ronglian.service.CommentAppriseService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��29��
 */
@RestController
@RequestMapping("/api")
public class CommentAppriseController {

	@Autowired
	private CommentAppriseService commentAppriseService;
	
	/**
	 * �û������۽��е��޽ӿ�
	 * */
	@RequestMapping(value="/1.0/commentApprise",method=RequestMethod.POST)
	public RongLianResult addCommentApprise(@RequestBody NewsCommentApprise commentApprise){
		return this.commentAppriseService.addCommentApprise(commentApprise);
	}
}
