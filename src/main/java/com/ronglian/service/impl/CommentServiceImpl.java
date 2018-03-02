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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.ronglian.dao.CommentDao;
import com.ronglian.dao.NewsInfoDao;
import com.ronglian.entity.NewsComment;
import com.ronglian.service.CommentService;
import com.ronglian.utils.PageCountResult;
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
	@Autowired
	private NewsInfoDao newsInfoDao;
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
			return RongLianResult.build(200, "request param is incorrect");
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
			
			//同步newsInfo表的comment_num 数据
			this.newsInfoDao.updateCommentNum(comment.getNewsId());
			return RongLianResult.ok(result);
		}else{
			return RongLianResult.build(200, "request param is error");
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.CommentService#getCommentList(java.lang.String, java.lang.String)
	 */
	@Override
	public RongLianResult getCommentList(String userId, String newsId,String deviceId) {
		// TODO Auto-generated method stub
		List<NewsComment> list = null;
		if(StringUtils.isNotBlank(newsId) && 
				StringUtils.isNotBlank(deviceId) ){
			if(StringUtils.isNotBlank(userId)){//用户登录时
				list = this.commentDao.getUserCommentListByUserId(newsId, userId);
			}else{//用户没登录时
				list = this.commentDao.getUserCommentListByDeviceId(newsId, deviceId);
			}
		}else{
			return RongLianResult.build(500, "请补全必须请求参数");
		}
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
	/* (non-Javadoc)
	 * @see com.ronglian.service.CommentService#checkComment(java.lang.Integer, java.lang.String)
	 */
	@Override
	@Transactional
	public RongLianResult checkComment(Integer status, String commentId) {
		// TODO Auto-generated method stub
		if(status != null && StringUtils.isNotBlank(commentId)){
			this.commentDao.updateStatusById(status,commentId);
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(500, "请求参数不能为空");
		}
	}
	@Override
	public PageCountResult fingCommentList(Integer status,String newsTitle,int pageNo,int pageSize){
		int start = (pageNo - 1)*pageSize;
		List<NewsComment> list = null;
		int totalCount = 0;
		if(status != null && StringUtils.isNotBlank(newsTitle)){
			list = this.commentDao.selectCommentList(status, newsTitle, start, pageSize);
			if(list != null && list.size() > 0){
				totalCount = this.commentDao.countCommentByOthers(status,newsTitle);
			}
		}else if(status == null && StringUtils.isBlank(newsTitle)){
			list = this.commentDao.selectCommentListAll(start, pageSize);
			if(list != null && list.size() > 0){
				totalCount = this.commentDao.countCommentAll();
			}
		}else if(status == null && StringUtils.isNotBlank(newsTitle)){
			list = this.commentDao.selectCommentListByNewsTitle(newsTitle,start, pageSize);
			if(list != null && list.size() > 0){
				totalCount = this.commentDao.countComment(newsTitle);
			}
		}else if(status != null && StringUtils.isBlank(newsTitle)){
			list=this.commentDao.selectCommentListByStatus(status, start, pageSize);
			if(list != null && list.size() > 0){
				totalCount = this.commentDao.countCommentByStatus(status);
			}
		}
		if(list != null && list.size() > 0){
			return PageCountResult.build(0, "ok", totalCount, pageNo, pageSize, list);
		}else{
			return PageCountResult.error(500, "评论列表为空", pageNo, pageSize);
		}
	}

}
