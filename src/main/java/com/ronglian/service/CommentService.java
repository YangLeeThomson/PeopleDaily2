/**
 * 
 */
package com.ronglian.service;

import com.ronglian.entity.NewsComment;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��29��
 */
public interface CommentService {

	public RongLianResult getComments(String deviceId,String newsId);
	
	public RongLianResult addComment(NewsComment comment);
	
	public RongLianResult getCommentList(String userId,String newsId);
	
	public RongLianResult delCommentById(String commentId);
}
