/**
 * 
 */
package com.ronglian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.CommentDao;
import com.ronglian.entity.NewsComment;
import com.ronglian.service.CommentService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ29ÈÕ
 */
@Service
public class CommentServiceImpl implements CommentService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.CommentService#getComments(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Autowired
	private CommentDao commentDao;
	@Override
	public RongLianResult getComments(String deviceId, String newsId) {
		// TODO Auto-generated method stub
		List<NewsComment> list = null;
		if(deviceId != null){
			list = this.commentDao.getComments(deviceId, newsId);
		}else{
			list = this.commentDao.getAllComments(newsId);
		}
		if(list != null && list.size() > 0){
			return RongLianResult.ok(list);
		}else{
			return RongLianResult.ok(null);
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.CommentService#addComment(com.ronglian.entity.NewsComment)
	 */
	@Override
	public RongLianResult addComment(NewsComment comment) {
		// TODO Auto-generated method stub
		NewsComment result = this.commentDao.save(comment);
		return RongLianResult.ok(result);
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.CommentService#getCommentList(java.lang.String, java.lang.String)
	 */
	@Override
	public RongLianResult getCommentList(String userId, String newsId) {
		// TODO Auto-generated method stub
		List<NewsComment> list = this.commentDao.getUserCommentList(newsId, userId);
		return RongLianResult.ok(list);
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.CommentService#delCommentById(java.lang.String)
	 */
	@Override
	public RongLianResult delCommentById(String commentId) {
		// TODO Auto-generated method stub
		this.commentDao.delete(commentId);
		return RongLianResult.ok();
	}

}
