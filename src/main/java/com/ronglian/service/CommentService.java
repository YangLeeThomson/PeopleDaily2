/**
 * 
 */
package com.ronglian.service;

import com.ronglian.entity.NewsComment;
import com.ronglian.utils.PageCountResult;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ29ÈÕ
 */
public interface CommentService {

	public RongLianResult getComments(String deviceId,String newsId);
	
	public RongLianResult addComment(NewsComment comment);
	
//	public RongLianResult getCommentList(String userId,String newsId,String deviceId);
	
	public RongLianResult delCommentById(String commentId);
	
	public RongLianResult checkComment(Integer status,String commentId);
	
	public PageCountResult fingCommentList(Integer status,String newsTitle,int pageNo,int pageSize);
	
	public RongLianResult getCommentList(String userId, String newsId,String deviceId,int start,int pageSize); 

}
