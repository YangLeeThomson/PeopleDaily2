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
 * @createTime 2017年12月22日
 */
@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	@RequestMapping(value="/1.0/usercomments",method=RequestMethod.GET)
	public RongLianResult getComments(String deviceId,String userId){
		return  this.commentService.getComments(deviceId, userId);
	}
	
	@RequestMapping(value="/1.0/contentcomment",method=RequestMethod.POST)
	public RongLianResult addComment(@RequestBody NewsComment comment){
		return this.commentService.addComment(comment);
	}
	//讨论一下用户评论接口是否要做？？？
	@RequestMapping(value="/1.0/comments",method=RequestMethod.GET)
	public RongLianResult getCommentList(String userId,String newsId){
//		return this.commentService.getCommentList(userId,newsId);

		Map map2 = new HashMap<>();
		map2.put("nickName", "tom");
		map2.put("userImage", "头像链接1");
		map2.put("commentContent", "真好玩北京");
		map2.put("userid", "23245234");
		map2.put("commentid", "894486156");
		Map map = new HashMap<>();
		map.put("nickName", "katte");
		map.put("userImage", "头像链接2");
		map.put("commentContent", "真好玩纽约");
		map.put("userid", "8448415151561");
		map.put("commentid", "89489444");
		
		List list = new ArrayList<>();
		list.add(map);
		list.add(map2);
		return RongLianResult.ok(list);
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
}
