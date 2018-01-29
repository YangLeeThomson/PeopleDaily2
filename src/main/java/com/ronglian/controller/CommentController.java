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
import com.ronglian.utils.PageCountResult;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017锟斤拷12锟斤拷22锟斤拷
 */
@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	/**
	 * 锟矫伙拷锟斤拷锟斤拷锟斤拷锟桔诧拷询锟接匡拷
	 * */
	@RequestMapping(value="/1.0/usercomments",method=RequestMethod.GET)
	public RongLianResult getComments(String deviceId,String userId){
		return  this.commentService.getComments(deviceId, userId);
	}
	/**
	 * 锟斤拷锟斤拷锟斤拷劢涌锟�
	 * */
	@RequestMapping(value="/1.0/contentcomment",method=RequestMethod.POST)
	public RongLianResult addComment(@RequestBody RongLianRequest<NewsComment> commentBody){
		NewsComment comment = null;
		if(commentBody != null){
			comment = commentBody.getData();
		}
		return this.commentService.addComment(comment);
	}
	/**
	 * 锟斤拷锟脚ｏ拷锟斤拷锟叫ｏ拷锟斤拷锟斤拷锟叫憋拷锟窖�
	 * */
	@RequestMapping(value="/1.0/comments",method=RequestMethod.GET)
	public RongLianResult getCommentList(String userId,String newsId,String deviceId){
		return this.commentService.getCommentList(userId,newsId,deviceId);
	}
	
	//删锟斤拷锟矫伙拷锟斤拷锟桔接匡拷
	@RequestMapping(value="/1.0/deletecomment",method=RequestMethod.POST)
	public RongLianResult delComment(@RequestBody RongLianRequest<Map> requestBody){
		Map requestMap = null;
		String commentId = null;
		if(requestBody != null){
			requestMap = requestBody.getData();
		}
		if(requestMap.get("commentId") != null){
			commentId = (String) requestMap.get("commentId");
		}
		try {
				return this.commentService.delCommentById(commentId);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "删锟斤拷失锟杰ｏ拷锟斤拷锟斤拷锟角革拷锟斤拷锟斤拷锟窖憋拷锟斤拷锟�");
		}
	}
	//锟斤拷锟斤拷锟斤拷锟斤拷锟截的接口ｏ拷锟斤拷imedia锟斤拷锟斤拷锟斤拷
	@RequestMapping(value="/1.0/commentCheck",method=RequestMethod.POST)
	public RongLianResult getCheckComment(@RequestBody RongLianRequest<Map> requestBody){
		Map requestMap = null;
		String commentId = null;
		Integer status=null;
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
			return RongLianResult.build(500, "删锟斤拷失锟杰ｏ拷锟斤拷锟斤拷锟角革拷锟斤拷锟斤拷锟窖憋拷锟斤拷锟�");
		}
	}
	//锟斤拷锟斤拷锟斤拷氐慕涌冢锟斤拷锟絠media锟斤拷锟斤拷锟斤拷
	@RequestMapping(value="/1.0/comment",method=RequestMethod.GET)
	public PageCountResult searchCommentList(Integer status,String newsTitle,
			@RequestParam(value="pageNo",defaultValue="1",required=false)int pageNo,
			@RequestParam(value="pageSize",defaultValue="10",required=false)int pageSize){
		return this.commentService.fingCommentList(status, newsTitle, pageNo, pageSize);
	}
}
