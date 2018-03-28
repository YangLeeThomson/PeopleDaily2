package com.ronglian.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.NewsInfoDao;
import com.ronglian.dao.NewsPushDao;
import com.ronglian.entity.NewsInfo;
import com.ronglian.entity.NewsPush;
import com.ronglian.service.NewsPushService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.NewsPushRequestBody;

@Service
public class NewsPushServiceImpl implements NewsPushService {

	@Autowired
	private NewsPushDao newsPushDao;
	@Autowired
	private NewsInfoDao newsDao;
	@Override
	public RongLianResult addNewsPush(NewsPushRequestBody requestBody) {
		// TODO Auto-generated method stub
		if(requestBody == null || requestBody.getNewsId() == null){
			RongLianResult.build(200, "requestBody or newsId can not be null");
		}
		String newsId = requestBody.getNewsId();
		String pushTitle = requestBody.getPushTitle();
		Date createTime = new Date();
//		Byte isRead = 
		NewsInfo newsInfo = this.newsDao.selectByNewsId(newsId);
		if(newsInfo == null){
			return RongLianResult.build(200, "newsInfo is null");
		}
		Byte hasVideo = newsInfo.getHasVideo();
		Byte dataMode = newsInfo.getDataMode();
		Byte isLive = newsInfo.getIsLive();
		Byte isLiveReplay = newsInfo.getIsLiveReplay();
		Integer isTopic = newsInfo.getIsTopic();
		String newsTitle = newsInfo.getNewsTitle();
		String topicUniqueId = newsInfo.getTopicUniqueId();
		Date publishTime = newsInfo.getPublishTime();
		//补全数据
		NewsPush newsPush = new NewsPush();
		newsPush.setCreateTime(createTime);
		newsPush.setDataMode(dataMode);
		newsPush.setHasVideo(hasVideo);
		newsPush.setIsLive(isLive);
		newsPush.setIsLiveReplay(isLiveReplay);
		newsPush.setIsTopic(isTopic);
		newsPush.setNewsId(newsId);
		newsPush.setNewsTitle(newsTitle);
		newsPush.setPublishTime(publishTime);
		newsPush.setPushTitle(pushTitle);
		newsPush.setTopicUniqueId(topicUniqueId);
		String pushId = UUID.randomUUID().toString();
		newsPush.setPushId(pushId);
		NewsPush object = this.newsPushDao.save(newsPush);
		return RongLianResult.ok(object);
	}

	@Override
	public RongLianResult getNewsPush(String deviceId, String userId,
			Integer pagesize, Integer pageNo) {
		// TODO Auto-generated method stub
		Integer start = (pageNo - 1)*pagesize;
		List<NewsPush> list = this.newsPushDao.selectPushList(start,pagesize);
		return RongLianResult.ok(list);
	}

}
