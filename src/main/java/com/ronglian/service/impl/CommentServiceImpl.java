/**
 * 
 */
package com.ronglian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.ronglian.dao.CommentDao;
import com.ronglian.dao.NewsInfoDao;
import com.ronglian.entity.NewsComment;
import com.ronglian.entity.NewsInfo;
import com.ronglian.jedis.JedisDao;
import com.ronglian.service.CommentService;
import com.ronglian.utils.JsonUtils;
import com.ronglian.utils.PageCountResult;
import com.ronglian.utils.RongLianConstant;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.NewsCommentBody;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
@Service
public class CommentServiceImpl implements CommentService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ronglian.service.CommentService#getComments(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private NewsInfoDao newsInfoDao;
	@Autowired
	private JedisDao jedisDao;
	@Override
	public RongLianResult getComments(String deviceId, String userId) {
		// TODO Auto-generated method stub
		List<NewsComment> list = null;
		List<String> newsIdList = null;
		List<NewsInfo> newsInfoList = null;
		Map<String,NewsInfo> map = new HashMap<String,NewsInfo>();
		List<NewsCommentBody> commentBodyList = new ArrayList<NewsCommentBody>();
		if (StringUtils.isBlank(deviceId)) {
			return RongLianResult.build(200, "request param is incorrect");
		}
		if (StringUtils.isNotBlank(userId)) {
			list = this.commentDao.getCommentsByUserId(userId);
			newsIdList = this.commentDao.getNewsIdListByUserId(userId);
			if(newsIdList != null && newsIdList.size() > 0){
				newsInfoList = this.newsInfoDao.selectByNewsID(newsIdList);
			}
		} else {
			list = this.commentDao.getCommentsByDeviceId(deviceId);
			newsIdList = this.commentDao.getNewsIdListByDeviceId(deviceId);
			if(newsIdList != null && newsIdList.size() > 0){
				newsInfoList = this.newsInfoDao.selectByNewsID(newsIdList);
			}
		}
		if (list == null || list.size() <= 0) {
			return RongLianResult.ok();
		}
		if( newsInfoList != null && newsInfoList.size() >0){
			for(NewsInfo newsInfo:newsInfoList){
				map.put(newsInfo.getNewsId(), newsInfo);
			}
		}
		for(int i = 0;i < list.size();i++){
			NewsCommentBody commBody = new NewsCommentBody();
			NewsInfo newsInfo = new NewsInfo();
			String newsId = list.get(i).getNewsId();
			if(map.get(newsId) != null){
				newsInfo = map.get(newsId);
			}
			commBody.setAppriseNum(list.get(i).getAppriseNum());
			commBody.setChanelUniqueId(newsInfo.getChannelUniqueId());
			commBody.setCommentContent(list.get(i).getCommentContent());
			commBody.setCommentId(list.get(i).getCommentId());
			commBody.setCreateTime(list.get(i).getCreateTime());
			commBody.setDataMode(newsInfo.getDataMode());
			commBody.setDeviceId(list.get(i).getDeviceId());
			commBody.setHasVideo(newsInfo.getHasVideo());
			commBody.setModifyTime(list.get(i).getModifyTime());
			commBody.setNewsId(list.get(i).getNewsId());
			commBody.setNewsTitle(newsInfo.getNewsTitle());
			commBody.setNickname(list.get(i).getNickname());
			commBody.setStatus(list.get(i).getStatus());
			commBody.setUserId(list.get(i).getUserId());
			commBody.setUserImage(list.get(i).getUserImage());
			commentBodyList.add(commBody);
		}
		return RongLianResult.ok(commentBodyList);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ronglian.service.CommentService#addComment(com.ronglian.entity.
	 * NewsComment)
	 */
	@Override
	public RongLianResult addComment(NewsComment comment) {
		// TODO Auto-generated method stub
		if (comment != null &&
				 StringUtils.isNotBlank(comment.getCommentContent()) &&
				 StringUtils.isNotBlank(comment.getDeviceId()) &&
				 StringUtils.isNotBlank(comment.getNewsId())) {
			String commentId = UUID.randomUUID().toString();
			Date date = new Date();
			comment.setCommentId(commentId);
			comment.setAppriseNum(0);
			comment.setCreateTime(date);
			comment.setModifyTime(date);
			comment.setStatus(0);
			NewsComment result = this.commentDao.save(comment);
			this.newsInfoDao.updateCommentNum(comment.getNewsId());
			//移除统配的key
			this.jedisDao.remove("comment"+comment.getNewsId()+"*");
			this.jedisDao.del("newsContent"+comment.getNewsId());
			return RongLianResult.ok(result);
		} else {
			return RongLianResult.build(200, "request param is error");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ronglian.service.CommentService#getCommentList(java.lang.String,
	 * java.lang.String)
	 */
	// @Override
	// public RongLianResult getCommentList(String userId, String newsId,String
	// deviceId) {
	// // TODO Auto-generated method stub
	// List<NewsComment> list = null;
	// if(StringUtils.isNotBlank(newsId) &&
	// StringUtils.isNotBlank(deviceId) ){
	// if(StringUtils.isNotBlank(userId)){//�û���¼ʱ
	// list = this.commentDao.getUserCommentListByUserId(newsId, userId);
	// }else{//�û�û��¼ʱ
	// list = this.commentDao.getUserCommentListByDeviceId(newsId, deviceId);
	// }
	// }else{
	// return RongLianResult.build(500, "�벹ȫ�����������");
	// }
	// return RongLianResult.ok(list);
	// }
	@Override
	public RongLianResult getCommentList(String userId, String newsId,
			String deviceId, int start, int pageSize, Boolean isHotComments,
			String commentId) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(newsId) || StringUtils.isBlank(deviceId)) {
			return RongLianResult.build(200,"The param of newsId and deviceId are not allowed null ");
		}
		
		List<NewsComment> list = null;
		String resultStr = null;
		if(StringUtils.isBlank(userId)){
			resultStr = jedisDao.get("comment"+newsId+pageSize+start+isHotComments);
			if(StringUtils.isNotBlank(resultStr)){
				jedisDao.expire("comment"+newsId+pageSize+start+isHotComments+commentId,RongLianConstant.REDIS_NEWS_EXPIRE);
				list = JsonUtils.jsonToList(resultStr, NewsComment.class);
				return RongLianResult.ok(list);
			}
		}
		if(StringUtils.isNotBlank(userId)){
			resultStr = jedisDao.get("comment"+newsId+userId+pageSize+start+isHotComments);
			if(StringUtils.isNotBlank(resultStr)){
				jedisDao.expire("comment"+newsId+userId+pageSize+start+isHotComments+commentId,RongLianConstant.REDIS_NEWS_EXPIRE);
				list = JsonUtils.jsonToList(resultStr, NewsComment.class);
				return RongLianResult.ok(list);
			}
		}
		
		NewsComment comment = null;
		Date createTime = null;
		if(commentId != null){
			comment = this.commentDao.findOne(commentId);
			if(comment == null){
				return RongLianResult.build(200,"this commentId or Comment is not exit ");
			}
			createTime = comment.getCreateTime();
		}
		
		if (!isHotComments && StringUtils.isNotBlank(userId)) {
			if(commentId == null){
				list = this.commentDao.getUserCommentListByUserIdLimt(newsId,userId, start, pageSize);
			}else{
				list = this.commentDao.getUserCommentListByUserIdLimtSecond(newsId,userId, start, pageSize,createTime);
			}
			jedisDao.set("comment"+newsId+userId+pageSize+start+isHotComments+commentId, JsonUtils.objectToJson(list));
			jedisDao.expire("comment"+newsId+userId+pageSize+start+isHotComments+commentId, RongLianConstant.REDIS_NEWS_EXPIRE);
		}
		if (!isHotComments && StringUtils.isBlank(userId) ) {
			if(commentId == null){
				list = this.commentDao.getUserCommentListByDeviceIdLimt(newsId,deviceId, start, pageSize);
			}else{
				list = this.commentDao.getUserCommentListByDeviceIdLimtSecond(newsId,userId, start, pageSize,createTime);
			}
			jedisDao.set("comment"+newsId+pageSize+start+isHotComments+commentId, JsonUtils.objectToJson(list));
			
			jedisDao.expire("comment"+newsId+pageSize+start+isHotComments+commentId, RongLianConstant.REDIS_NEWS_EXPIRE);
		}
		if (isHotComments && StringUtils.isNotBlank(userId)) {
			if(commentId == null){
				list = this.commentDao.getCommentListByUserIdAndAppriseNum(newsId,userId, start, pageSize);
			}else{
				list = this.commentDao.getCommentListByUserIdAndAppriseNumSecond(newsId,userId, start, pageSize,createTime);
			}
			jedisDao.set("comment"+newsId+userId+pageSize+start+isHotComments+commentId,JsonUtils.objectToJson(list));
			jedisDao.expire("comment"+newsId+userId+pageSize+start+isHotComments+commentId, RongLianConstant.REDIS_NEWS_EXPIRE);
		}
		if (isHotComments && StringUtils.isBlank(userId)) {
			if(commentId == null){
				list = this.commentDao.getCommentListByDeviceIdAndAppriseNum(newsId, deviceId, start, pageSize);
			}else{
				list = this.commentDao.getCommentListByDeviceIdAndAppriseNumSecond(newsId, deviceId, start, pageSize,createTime);
			}
			jedisDao.set("comment"+newsId+pageSize+start+isHotComments+commentId,JsonUtils.objectToJson(list));
			jedisDao.expire("comment"+newsId+pageSize+start+isHotComments+commentId, RongLianConstant.REDIS_NEWS_EXPIRE);
		}
       		return RongLianResult.ok(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ronglian.service.CommentService#delCommentById(java.lang.String)
	 */
	@Override
	public RongLianResult delCommentById(String commentId) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(commentId)) {
			return RongLianResult.build(200,
					"The param commentId must not be null");
		}
		NewsComment comm = this.commentDao.findOne(commentId);
		if(comm == null){
			return RongLianResult.build(200,
					"The param commentId may not be exist");
		}
		this.commentDao.delete(commentId);
		this.jedisDao.remove("comment"+comm.getNewsId()+"*");
		return RongLianResult.ok();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ronglian.service.CommentService#checkComment(java.lang.Integer,
	 * java.lang.String)
	 */
	@Override
	@Transactional
	public RongLianResult checkComment(Integer status, String commentId) {
		// TODO Auto-generated method stub
		if (status == null || StringUtils.isBlank(commentId)) {
			return RongLianResult.build(200,
					"The param of status and commentId are not allowed null");
		}
		this.commentDao.updateStatusById(status, commentId);
		return RongLianResult.ok();

	}

	@Override
	public PageCountResult fingCommentList(Integer status, String newsTitle,
			int pageNo, int pageSize) {
		int start = (pageNo - 1) * pageSize;
		List<NewsComment> list = null;
		int totalCount = 0;
		if (status != null && StringUtils.isNotBlank(newsTitle)) {
			list = this.commentDao.selectCommentList(status, newsTitle, start,
					pageSize);
			if (list != null && list.size() > 0) {
				totalCount = this.commentDao.countCommentByOthers(status,
						newsTitle);
			}
		}

		if (status == null && StringUtils.isBlank(newsTitle)) {
			list = this.commentDao.selectCommentListAll(start, pageSize);
			if (list != null && list.size() > 0) {
				totalCount = this.commentDao.countCommentAll();
			}
		}

		if (status == null && StringUtils.isNotBlank(newsTitle)) {
			list = this.commentDao.selectCommentListByNewsTitle(newsTitle,
					start, pageSize);
			if (list != null && list.size() > 0) {
				totalCount = this.commentDao.countComment(newsTitle);
			}
		}
		if (status != null && StringUtils.isBlank(newsTitle)) {
			list = this.commentDao.selectCommentListByStatus(status, start,
					pageSize);
			if (list != null && list.size() > 0) {
				totalCount = this.commentDao.countCommentByStatus(status);
			}
		}
		if (list != null && list.size() > 0) {
			return PageCountResult.build(0, "ok", totalCount, pageNo, pageSize,
					list);
		} else {
			return PageCountResult.error(200, "result is null", pageNo,
					pageSize);
		}
	}

}
