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
import com.ronglian.service.CommentService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017年12月22日
 */
@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	/**
	 * 用户个人评论查询接口
	 * */
	@RequestMapping(value="/1.0/usercomments",method=RequestMethod.GET)
	public RongLianResult getComments(String deviceId,String userId){
		return  this.commentService.getComments(deviceId, userId);
	}
	/**
	 * 添加评论接口
	 * */
	@RequestMapping(value="/1.0/contentcomment",method=RequestMethod.POST)
	public RongLianResult addComment(@RequestBody NewsComment comment){
		return this.commentService.addComment(comment);
	}
	/**
	 * 新闻（所有）评论列表查询
	 * */
	@RequestMapping(value="/1.0/comments",method=RequestMethod.GET)
	public RongLianResult getCommentList(String userId,String newsId,String deviceId){
		return this.commentService.getCommentList(userId,newsId,deviceId);
	}
	
	//删除用户评论接口
	@RequestMapping(value="/1.0/deletecomment",method=RequestMethod.POST)
	public RongLianResult delComment(@RequestBody Map requestMap){
		String commentId = (String) requestMap.get("commentId");
		try {
			return this.commentService.delCommentById(commentId);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "删除失败，可能是该评论已被清除");
		}
	}
	//审核评论相关的接口（供imedia交互）
	@RequestMapping(value="/1.0/comment/{commentId}/{status}",method=RequestMethod.PUT)
	public RongLianResult getCheckComment(@PathVariable("status")Integer status,@PathVariable("commentId")String commentId){
		
		return this.commentService.checkComment(status,commentId);
	}
	//评论相关的接口（供imedia交互）
	@RequestMapping(value="/1.0/comment",method=RequestMethod.GET)
	public RongLianResult searchCommentList(Integer status,String newsTitle,
			@RequestParam(value="pageNo",defaultValue="1",required=false)int pageNo,
			@RequestParam(value="pageSize",defaultValue="10",required=false)int pageSize){
		return this.commentService.fingCommentList(status, newsTitle, pageNo, pageSize);
	}
}
