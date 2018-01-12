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
 * @createTime 2018年1月12日
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
			//分享的平台(-1:本地软件分享，0:微信，1：twinnter，2：facebook)',
			Integer sharePlatform = newsShare.getSharePlatform();
			
			if(StringUtils.isNotBlank(deviceId) 
					&& StringUtils.isNotBlank(newsId)
					&& sharePlatform != null){
				//1、查看有无分享此数据
				result = this.shareDao.getNewsShare(userId, deviceId, newsId, sharePlatform);
				if(result == null){
					newsShare.setCreateTime(new Date());
					newsShare.setShareId(UUID.randomUUID().toString());
					result = this.shareDao.save(newsShare);
					//同步到newsInfo表里的分享数据
					if(result != null){
						this.newsInfoDao.updateShareNum(newsId);
						NewsInfo newsInfo = this.newsInfoDao.findOne(newsId);
						Map resultMap = new HashMap();
						resultMap.put("count", newsInfo.getShareNum());
						return RongLianResult.ok(resultMap);
					}
					return RongLianResult.build(500,"分享成功，但同步newsInfo失败！");
				}else{
					return RongLianResult.build(500, "该文章已经分享！"); 
				}
			}else{
				return RongLianResult.build(500,"缺少必须请求参数");
			}
		}else{
			return RongLianResult.build(500, "请求参数不能为空！");
		}
	}

}
