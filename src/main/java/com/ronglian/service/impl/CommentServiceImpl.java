/**
 * 
 */
package com.ronglian.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.CommentDao;
import com.ronglian.entity.NewsComment;
import com.ronglian.service.CommentService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
@Service
public class CommentServiceImpl implements CommentService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.CommentService#getComments(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Autowired
	private CommentDao commentDao;
	@Override
	public RongLianResult getComments(String deviceId, String userId) {
		// TODO Auto-generated method stub
		List<NewsComment> list = null;
		if(StringUtils.isNotBlank(deviceId)){
			if(StringUtils.isNotBlank(userId)){
				list = this.commentDao.getCommentsByUserId(userId);
			}else{
				list = this.commentDao.getCommentsByDeviceId(deviceId);
			}
			
			if(list != null && list.size() > 0){
						return RongLianResult.ok(list);
					}else{
						return RongLianResult.ok();
			}
		}else{
			return RongLianResult.build(500, "请求参数不正确");
		}
		
		
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.CommentService#addComment(com.ronglian.entity.NewsComment)
	 */
	@Override
	public RongLianResult addComment(NewsComment comment) {
		// TODO Auto-generated method stub
		if(comment != null
				&& StringUtils.isNotBlank(comment.getCommentContent())
				&& StringUtils.isNotBlank(comment.getDeviceId())
				&& StringUtils.isNotBlank(comment.getNewsId())
				){
			String commentId = UUID.randomUUID().toString();
			Date date = new Date();
			comment.setCommentId(commentId);
			comment.setAppriseNum(0);
			comment.setCreateTime(date);
			comment.setModifyTime(date);
			NewsComment result = this.commentDao.save(comment);
			return RongLianResult.ok(result);
		}else{
			return RongLianResult.build(500, "请求参数或格式不对");
		}
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
		if(StringUtils.isNotBlank(commentId)){
			this.commentDao.delete(commentId);
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(500, "请求参数有问题");
		}
	}

}
