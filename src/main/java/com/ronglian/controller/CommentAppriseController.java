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
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
@RestController
@RequestMapping("/api")
public class CommentAppriseController {

	@Autowired
	private CommentAppriseService commentAppriseService;
	
	/**
	 * 用户对评论进行点赞接口
	 * */
	@RequestMapping(value="/1.0/commentApprise",method=RequestMethod.POST)
	public RongLianResult addCommentApprise(@RequestBody RongLianRequest<NewsCommentApprise> commentAppriseBody){
		NewsCommentApprise commentApprise = null;
		if(commentAppriseBody != null){
			commentApprise = commentAppriseBody.getObj();
		}
		return this.commentAppriseService.addCommentApprise(commentApprise);
	}
}
