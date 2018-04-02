/**
 * 
 */
package com.ronglian.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.NewsComment;
import com.ronglian.entity.User;
import com.ronglian.service.CommentService;
import com.ronglian.service.UserService;
import com.ronglian.utils.PageCountResult;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017��12��22��
 */
@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	/**
	 * 用户评论列表接口
	 * */
	@RequestMapping(value="/1.0/usercomments",method=RequestMethod.GET)
	public RongLianResult getComments(String deviceId,String userId,String accessToken){
		if(StringUtils.isNotBlank(accessToken)){
			RongLianResult  result = this.userService.getUserInfo(accessToken);
			if(result.getData() == null){
				return RongLianResult.build(106, "accessToken is error");
			}
			User user = (User) result.getData();
			String userId2 = user.getUserId();
			if(!userId2.equals(userId)){
				return RongLianResult.build(200, "maybe param userId is error");
			}	
		}
		if(StringUtils.isBlank(accessToken) && StringUtils.isNotBlank(userId)){
			return RongLianResult.build(200,"you have not logined ,so userId should be null ");
		}
		return  this.commentService.getComments(deviceId, userId);
	}
	/**
	 * 用户评论内容接口
	 * */
	@RequestMapping(value="/1.0/contentcomment",method=RequestMethod.POST)
	public RongLianResult addComment(@RequestBody RongLianRequest<NewsComment> commentBody){
		NewsComment comment = null;
		String accessToken = null;
		String userId = null;
		if(commentBody != null){
			comment = commentBody.getData();
			accessToken = commentBody.getAccessToken();
		}
		if(comment != null){
			userId = comment.getUserId();
		}
		if(StringUtils.isNotBlank(accessToken)){
			RongLianResult  result = this.userService.getUserInfo(accessToken);
			if(result.getData() == null){
				return RongLianResult.build(106, "accessToken is error");
			}
			User user = (User) result.getData();
			String userId2 = user.getUserId();
			if(!userId2.equals(userId)){
				return RongLianResult.build(200, "maybe param userId is error");
			}	
		}
		if(StringUtils.isBlank(accessToken) && StringUtils.isNotBlank(userId)){
			return RongLianResult.build(200,"you have not logined ,so userId should be null ");
		}
		return this.commentService.addComment(comment);
	}
	/**
	 * 新闻对应评论的接口
	 * */
	@RequestMapping(value="/1.0/comments",method=RequestMethod.GET)
	public RongLianResult getCommentList(String userId,String newsId,String deviceId,String commentId,
			@RequestParam(value="pageNo",defaultValue="1",required=false)int pageNo,
			@RequestParam(value="pageSize",defaultValue="10",required=false)int pageSize,
			@RequestParam(value="isHotComments",defaultValue="false",required=false)Boolean isHotComments
			){
//		return this.commentService.getCommentList(userId,newsId,deviceId);
		int start = (pageNo - 1)*pageSize;
		return this.commentService.getCommentList(userId, newsId, deviceId, start, pageSize,isHotComments,commentId);
	}
	
	/**
	 * 删除评论的接口
	 * */
	@RequestMapping(value="/1.0/deletecomment",method=RequestMethod.POST)
	public RongLianResult delComment(@RequestBody RongLianRequest<Map> requestBody){
		Map requestMap = null;
		String commentId = null;
		String accessToken = null;
		String userId = null;
		if(requestBody != null){
			requestMap = requestBody.getData();
			accessToken = requestBody.getAccessToken();
		}
		if(requestMap.get("commentId") != null){
			commentId = (String) requestMap.get("commentId");
		}
		if(requestMap != null){
			userId = (String) requestMap.get("userId");
		}
		if(StringUtils.isNotBlank(accessToken)){
			RongLianResult  result = this.userService.getUserInfo(accessToken);
			if(result.getData() == null){
				return RongLianResult.build(106, "accessToken is error");
			}
			User user = (User) result.getData();
			String userId2 = user.getUserId();
			if(!userId2.equals(userId)){
				return RongLianResult.build(200, "maybe param userId is error");
			}	
		}
		if(StringUtils.isBlank(accessToken) && StringUtils.isNotBlank(userId)){
			return RongLianResult.build(200,"you have not logined ,so userId should be null ");
		}
		try {
				return this.commentService.delCommentById(commentId);
		} catch (Exception e) {
			return RongLianResult.build(500, "the server maybe error");
		}
	}
	/**
	 * 评论审核接口
	 *
	 */
	@RequestMapping(value="/1.0/commentCheck",method=RequestMethod.POST)
	public RongLianResult getCheckComment(@RequestBody RongLianRequest<Map> requestBody){
		Map requestMap = null;
		String commentId = null;
		Integer status = null;
		if(requestBody != null){
			requestMap = requestBody.getData();
		}
		if(requestMap.get("commentId") != null&&requestMap.get("status") != null){
			commentId =  requestMap.get("commentId").toString();
			status = Integer.valueOf(requestMap.get("status").toString());
		}
		try {
			return this.commentService.checkComment(status,commentId);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "error");
		}
	}
	/**
	 * imedia搜索评论接口
	 * @param status
	 * @param newsTitle
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/1.0/comment",method=RequestMethod.GET)
	public PageCountResult searchCommentList(Integer status,String newsTitle,
			@RequestParam(value="pageNo",defaultValue="1",required=false)int pageNo,
			@RequestParam(value="pageSize",defaultValue="10",required=false)int pageSize){
		return this.commentService.fingCommentList(status, newsTitle, pageNo, pageSize);
	}
}
