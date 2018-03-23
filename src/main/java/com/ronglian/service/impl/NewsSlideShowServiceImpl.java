/**
 * 
 */
package com.ronglian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.ChannelDao;
import com.ronglian.dao.NewsInfoDao;
import com.ronglian.dao.SlideShowDao;
import com.ronglian.entity.Channel;
import com.ronglian.entity.NewsInfo;
import com.ronglian.entity.NewsSlideshow;
import com.ronglian.service.NewsSlideShowService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.RongLianUtils;
import com.ronglian.utils.model.request.NewsInfoBody;
import com.ronglian.utils.model.request.SlideShowBody;

/**
 * @author liyang
 * @createTime 2017��12��28��
 */
@Service
public class NewsSlideShowServiceImpl implements NewsSlideShowService {
	@Autowired
	private SlideShowDao slideShowDao;

	@Autowired
	private ChannelDao channelDao;

	@Autowired
	private NewsInfoDao newsInfoDao;

	@Override
	public RongLianResult addSlideShow(NewsSlideshow slideShow) {
		if (slideShow.getSlideShowId() == null
				|| slideShow.getImageUrl() == null
				|| slideShow.getChannelUniqueId() == null
				|| slideShow.getTitle() == null || slideShow.getDesc() == null) {
			return RongLianResult.build(200, "The needed params can not be null");
		}
		slideShow.setCreateTime((slideShowDao.findOne(slideShow
				.getSlideShowId()) != null) ? slideShowDao.findOne(
				slideShow.getSlideShowId()).getCreateTime() : (new Date()));
		NewsSlideshow result = this.slideShowDao.save(slideShow);
		if (result != null) {
			return RongLianResult.ok();
		} else {
			return RongLianResult.build(200, "save error");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ronglian.service.NewsSlideShowService#getSlidePictureByChannel(java
	 * .lang.String)
	 */
	@Override
	public RongLianResult getSlideShowByChannel(String channelUniqueId) {
		// TODO Auto-generated method stub
		List<NewsSlideshow> list = null;
		List<SlideShowBody> resultList = new ArrayList<SlideShowBody>();
		if (StringUtils.isBlank(channelUniqueId)) {
			return RongLianResult.build(200, "channelUniqueId can not be null");
		}

		list = this.slideShowDao.selectSlideShowByChannel(channelUniqueId);
		// String channelName =
		// this.channelDao.selectChannelByUniqueId(channelUniqueId);
		List<String> newsIdList = new ArrayList<String>();
		for (NewsSlideshow slideShow : list) {
			newsIdList.add(slideShow.getNewsId());
		}
		List<NewsInfo> newsInfoList = this.newsInfoDao.selectByNewsID(newsIdList);
		Map<String, NewsInfoBody> temp = new HashMap<String, NewsInfoBody>();
		for (NewsInfo newsInfo : newsInfoList) {
			NewsInfoBody newsInfoBody = new NewsInfoBody();
			newsInfoBody.setAppointCoverImage(newsInfo.getAppointCoverImage());
			newsInfoBody.setNewsId(newsInfo.getNewsId());
			newsInfoBody.setNewsSort(newsInfo.getNewsSort());
			newsInfoBody.setNewsTags(newsInfo.getNewsTags());
			newsInfoBody.setNewsTitle(newsInfo.getNewsTitle());
			newsInfoBody.setChannelUniqueId(newsInfo.getChannelUniqueId());
			newsInfoBody.setChannelName(newsInfo.getChannelName());
			newsInfoBody.setShowType(newsInfo.getShowType());
			newsInfoBody.setFullColumnImgUrl(newsInfo.getFullColumnImgUrl());
			newsInfoBody.setHasVideo(newsInfo.getHasVideo());
			newsInfoBody.setIsLive(newsInfo.getIsLive());
			newsInfoBody.setIsLiveReplay(newsInfo.getIsLiveReplay());
			newsInfoBody.setLiveUrl(newsInfo.getLiveUrl());
			newsInfoBody.setLiveReplayUrl(newsInfo.getLiveReplayUrl());
			newsInfoBody.setLiveHostChatid(newsInfo.getLiveHostChatid());
			newsInfoBody.setLiveUsChatid(newsInfo.getLiveUsChatid());
			newsInfoBody.setLink(newsInfo.getLink());
			newsInfoBody.setDataMode(newsInfo.getDataMode());
			newsInfoBody.setVideoDuration(newsInfo.getVideoDuration());
			newsInfoBody.setTopicUniqueId(newsInfo.getTopicUniqueId());
			newsInfoBody.setIsTopic(newsInfo.getIsTopic());
			newsInfoBody.setPublishTime(RongLianUtils.changeDateTime(newsInfo
					.getPublishTime()));
			temp.put(newsInfo.getNewsId(), newsInfoBody);
		}
		for (NewsSlideshow slideShow : list) {
			SlideShowBody slideBody = new SlideShowBody();
			slideBody.setChannelUniqueId(slideShow.getChannelUniqueId());
			String createTime = RongLianUtils.changeDateTime(slideShow
					.getCreateTime());
			slideBody.setCreateTime(createTime);
			slideBody.setDataStatus(slideShow.getDataStatus());
			slideBody.setDesc(slideShow.getDesc());
			slideBody.setImageUrl(slideShow.getImageUrl());

			String newsId = slideShow.getNewsId();
			NewsInfoBody newsInfoBody = temp.get(newsId);
			slideBody.setNewsId(newsId);
			slideBody.setSlideShowId(slideShow.getSlideShowId());
			slideBody.setSort(slideShow.getSort());
			slideBody.setTitle(slideShow.getTitle());
			slideBody.setNewsInfoBody(newsInfoBody);
			resultList.add(slideBody);
		}
		return RongLianResult.ok(resultList);
	}

}
