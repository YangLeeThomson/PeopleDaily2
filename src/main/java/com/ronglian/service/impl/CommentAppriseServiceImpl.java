/**
 * 
 */
package com.ronglian.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronglian.dao.CommentDao;
import com.ronglian.dao.NewsCommentAppriseDao;
import com.ronglian.entity.NewsComment;
import com.ronglian.entity.NewsCommentApprise;
import com.ronglian.service.CommentAppriseService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018年1月10日
 */
@Service
public class CommentAppriseServiceImpl implements CommentAppriseService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.CommentAppriseService#addCommentApprise(com.ronglian.entity.NewsCommentApprise)
	 */
	@Autowired
	private NewsCommentAppriseDao commentAppriseDao;
	@Autowired
	private CommentDao commentDao; 
	@Transactional
	@Override
	public RongLianResult addCommentApprise(NewsCommentApprise obj) {
		// TODO Auto-generated method stub
		if(obj !=null && StringUtils.isNotBlank(obj.getCommentId())
			&& StringUtils.isNotBlank(obj.getDeviceId())
			){
			
			//校验commentId合法性
			NewsComment comment = commentDao.findOne(obj.getCommentId());
			if(comment == null){
				return RongLianResult.build(200, "commentId is a fake data ");
			}
			//检验是否已经点赞？如果不可以连续点赞，将注释放开！！！
			/*
				人民日报和微软，沟通过，评论的点赞，做成不可以取消的，连续点赞！
			*/
			//保存点赞
			String appriseId = UUID.randomUUID().toString();
			obj.setAppriseId(appriseId);
			obj.setCreateTime(new Date());
			NewsCommentApprise result = commentAppriseDao.save(obj);
			if(result == null){
				return RongLianResult.build(200, "save Apprise failed");
			}
			//点赞数统计到评论表中
			this.commentDao.updateNewsCommentById(obj.getCommentId());
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(200, "request param has problem");
		}
	}
	
}
