/**
 * 
 */
package com.ronglian.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronglian.dao.AppriseDao;
import com.ronglian.dao.NewsInfoDao;
import com.ronglian.entity.NewsInfoApprise;
import com.ronglian.jedis.JedisDao;
import com.ronglian.service.AppriseService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018年1月2日
 */
@Service
public class AppriseServiceImpl implements AppriseService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.AppriseService#addNewsInfoApprise(com.ronglian.entity.NewsInfoApprise)
	 */
	@Autowired
	private AppriseDao appriseDao;
	@Autowired
	private NewsInfoDao newsInfoDao;
	@Autowired
	private JedisDao jedisDao;
	@Override
	@Transactional
	public RongLianResult addNewsInfoApprise(NewsInfoApprise apprise) {
		// TODO Auto-generated method stub
		String newsId = null;
		String deviceId = null;
		NewsInfoApprise result = null;
		if(apprise != null){
			newsId = apprise.getNewsId();
			deviceId = apprise.getDeviceId();
			String userId = apprise.getUserId();
			Integer good = apprise.getGood();
			if(StringUtils.isNotBlank(deviceId)
					&& StringUtils.isNotBlank(newsId)
					&& good != null
					){
				
				//先查一下，是否已经点赞
				result = this.appriseDao.getNewsInfoApprise(deviceId,userId,newsId);
				if(result == null){
					apprise.setAppriseId(UUID.randomUUID().toString());
					apprise.setCreateTime(new Date());

					result = this.appriseDao.save(apprise);
					if(good == 1){
						this.newsInfoDao.updateAppriseUpNum(newsId);
						this.jedisDao.del("newsContent"+newsId);
						return RongLianResult.ok(result);
					}else if(good == -1){
						this.newsInfoDao.updateAppriseDownNum(newsId);
						this.jedisDao.del("newsContent"+newsId);
						return RongLianResult.ok(result);
					}else{
						return RongLianResult.build(200, "param good has problem��the value of good should be 1 or -1");
					}
				}else{
					return RongLianResult.build(200, "you have apprised");
				}
			}else{
				return RongLianResult.build(200, "request param is incorrect");
			}
		}else{
			return RongLianResult.build(200, "request param can not be null");
		}
	}

	/* (non-Javadoc)
	 * @see com.ronglian.service.AppriseService#removeApprise(com.ronglian.entity.NewsInfoApprise)
	 */
	@Override
	@Transactional
	public RongLianResult removeApprise(NewsInfoApprise apprise) {
		// TODO Auto-generated method stub
		NewsInfoApprise result = null;
		if(apprise != null){
			String newsId = apprise.getNewsId();
			String deviceId = apprise.getDeviceId();
			String userId = apprise.getUserId();
			Integer good = apprise.getGood();
			if(StringUtils.isNotBlank(deviceId)
					&& StringUtils.isNotBlank(newsId)
					&& good != null
					){
				//先查一下，是否已经点赞
				result = this.appriseDao.getNewsInfoApprise(deviceId,userId,newsId);
				if(result != null){
					this.appriseDao.delete(result);
					if(good == 1){
						this.newsInfoDao.deleteAppriseUpNum(newsId);
						return RongLianResult.ok();
					}else if(good == -1){
						this.newsInfoDao.deleteAppriseDownNum(newsId);
						return RongLianResult.ok();
					}else{
						return RongLianResult.build(200, "参数good有问题，good必须是1或-1");
					}
				}else{
					return RongLianResult.build(200, "你要删除的点赞数据已经删除或不存在");
				}
			}else{
				return RongLianResult.build(200, "请求参数不正确");
			}
		}else{
			return RongLianResult.build(200, "请求参数不可以为空");
		}
	}

	/* (non-Javadoc)
	 * @see com.ronglian.service.AppriseService#getAppriseList(java.util.Map)
	 */
	@Override
	public RongLianResult getAppriseList(String deviceId, String userId) {
		// TODO Auto-generated method stub
		List<NewsInfoApprise> list = null;
		if(StringUtils.isNotBlank(deviceId)){
 			list= this.appriseDao.selectAppriseList(deviceId,userId);
			return RongLianResult.ok(list);
		}else{
			return RongLianResult.build(200, "deviceId can not be null");
		}
	}

	/* (non-Javadoc)
	 * @see com.ronglian.service.AppriseService#isApprised(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public boolean isApprised(String deviceId, String newsId, Integer good) {
		// TODO Auto-generated method stub
		List<NewsInfoApprise> list = null;
		list = this.appriseDao.selectIsApprised(deviceId, good, newsId);
		if(list != null && list.size() >= 1){
			return true;
		}
		return false;
	}
}
