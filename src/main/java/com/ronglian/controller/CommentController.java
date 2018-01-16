/**
 * 
 */
package com.ronglian.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.NewsComment;
import com.ronglian.service.CommentService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��22��
 */
@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	/**
	 * �û��������۲�ѯ�ӿ�
	 * */
	@RequestMapping(value="/1.0/usercomments",method=RequestMethod.GET)
	public RongLianResult getComments(String deviceId,String userId){
		return  this.commentService.getComments(deviceId, userId);
	}
	/**
	 * ������۽ӿ�
	 * */
	@RequestMapping(value="/1.0/contentcomment",method=RequestMethod.POST)
	public RongLianResult addComment(@RequestBody NewsComment comment){
		return this.commentService.addComment(comment);
	}
	/**
	 * ���ţ����У������б��ѯ
	 * */
	@RequestMapping(value="/1.0/comments",method=RequestMethod.GET)
	public RongLianResult getCommentList(String userId,String newsId,String deviceId){
		return this.commentService.getCommentList(userId,newsId,deviceId);
	}
	
	//ɾ���û����۽ӿ�
	@RequestMapping(value="/1.0/deletecomment",method=RequestMethod.POST)
	public RongLianResult delComment(@RequestBody Map requestMap){
		String commentId = (String) requestMap.get("commentId");
		try {
			return this.commentService.delCommentById(commentId);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "ɾ��ʧ�ܣ������Ǹ������ѱ����");
		}
	}
}
