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

	@Autowired
	private NewsCommentAppriseDao commentAppriseDao;
	@Autowired
	private CommentDao commentDao; 
	@Transactional
	@Override
	public RongLianResult addCommentApprise(NewsCommentApprise obj) {
		if(obj !=null && StringUtils.isNotBlank(obj.getCommentId())
			&& StringUtils.isNotBlank(obj.getDeviceId())
			){
			
			NewsComment comment = commentDao.findOne(obj.getCommentId());
			if(comment == null){
				return RongLianResult.build(200, "commentId is a fake data ");
			}
			String appriseId = UUID.randomUUID().toString();
			obj.setAppriseId(appriseId);
			obj.setCreateTime(new Date());
			NewsCommentApprise result = commentAppriseDao.save(obj);
			if(result == null){
				return RongLianResult.build(200, "save Apprise failed");
			}
			this.commentDao.updateNewsCommentById(obj.getCommentId());
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(200, "request param has problem");
		}
	}
	
}
