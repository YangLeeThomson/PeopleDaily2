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
import com.ronglian.service.AppriseService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018��1��2��
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
				
				//�Ȳ�һ�£��Ƿ��Ѿ�����
				result = this.appriseDao.getNewsInfoApprise(deviceId,userId,newsId);
				if(result == null){
					apprise.setAppriseId(UUID.randomUUID().toString());
					apprise.setCreateTime(new Date());

					result = this.appriseDao.save(apprise);
					if(good == 1){
						this.newsInfoDao.updateAppriseUpNum(newsId);
						return RongLianResult.ok(result);
					}else if(good == -1){
						this.newsInfoDao.updateAppriseDownNum(newsId);
						return RongLianResult.ok(result);
					}else{
						return RongLianResult.build(500, "����good�����⣬good������1��-1");
					}
				}else{
					return RongLianResult.build(500, "���Ѿ��������");
				}
			}else{
				return RongLianResult.build(500, "�����������ȷ");
			}
		}else{
			return RongLianResult.build(500, "��β�����Ϊnull");
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
				//�Ȳ�һ�£��Ƿ��Ѿ�����
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
						return RongLianResult.build(500, "����good�����⣬good������1��-1");
					}
				}else{
					return RongLianResult.build(500, "��Ҫɾ���ĵ��������Ѿ�ɾ���򲻴���");
				}
			}else{
				return RongLianResult.build(500, "�����������ȷ");
			}
		}else{
			return RongLianResult.build(500, "�������������Ϊ��");
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
			return RongLianResult.build(500, "deviceId����Ϊnull��մ���");
		}
	}

}
