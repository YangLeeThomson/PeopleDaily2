/**
 * 
 */
package com.ronglian.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronglian.dao.NewsInfoDao;
import com.ronglian.dao.NewsShareDao;
import com.ronglian.entity.NewsInfo;
import com.ronglian.entity.NewsShare;
import com.ronglian.service.NewsShareService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018��1��12��
 */
@Service
public class NewsShareServiceImpl implements NewsShareService {


	@Autowired
	private NewsShareDao shareDao;
	@Autowired 
	private NewsInfoDao newsInfoDao;
	
	@Override
	@Transactional
	public RongLianResult countNewsShare(NewsShare newsShare) {
		// TODO Auto-generated method stub
		if(newsShare != null){
			NewsShare result = null;
			String newsId = newsShare.getNewsId();
			String deviceId = newsShare.getDeviceId();
			String userId = newsShare.getUserId();
			//�����ƽ̨(-1:�����������0:΢�ţ�1��twinnter��2��facebook)',
			Integer sharePlatform = newsShare.getSharePlatform();
			
			if(StringUtils.isNotBlank(deviceId) 
					&& StringUtils.isNotBlank(newsId)
					&& sharePlatform != null){
				//1���鿴���޷��������
				result = this.shareDao.getNewsShare(userId, deviceId, newsId, sharePlatform);
				if(result == null){
					newsShare.setCreateTime(new Date());
					newsShare.setShareId(UUID.randomUUID().toString());
					result = this.shareDao.save(newsShare);
					//ͬ����newsInfo����ķ�������
					if(result != null){
						this.newsInfoDao.updateShareNum(newsId);
						NewsInfo newsInfo = this.newsInfoDao.findOne(newsId);
						Map resultMap = new HashMap();
						resultMap.put("count", newsInfo.getShareNum());
						return RongLianResult.ok(resultMap);
					}
					return RongLianResult.build(500,"����ɹ�����ͬ��newsInfoʧ�ܣ�");
				}else{
					return RongLianResult.build(500, "�������Ѿ�����"); 
				}
			}else{
				return RongLianResult.build(500,"ȱ�ٱ����������");
			}
		}else{
			return RongLianResult.build(500, "�����������Ϊ�գ�");
		}
	}

}
