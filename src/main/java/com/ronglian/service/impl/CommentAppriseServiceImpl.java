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
				return RongLianResult.build(500, "commentId是虚构的数据");
			}
			//检验是否已经点赞？如果不可以连续点赞，将注释放开！！！
			/*
				是否可以连续代码
			*/
			//保存点赞
			String appriseId = UUID.randomUUID().toString();
			obj.setAppriseId(appriseId);
			obj.setCreateTime(new Date());
			NewsCommentApprise result = commentAppriseDao.save(obj);
			if(result == null){
				return RongLianResult.build(500, "save点赞失败");
			}
			//点赞数统计到评论表中
			this.commentDao.updateNewsCommentById(obj.getCommentId());
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(500, "请求参数有问题");
		}
	}
	
}
