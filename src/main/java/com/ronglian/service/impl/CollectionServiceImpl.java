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

import com.ronglian.dao.CollectionDao;
import com.ronglian.dao.NewsInfoDao;
import com.ronglian.dao.NewsPictureDao;
import com.ronglian.dao.TopicDao;
import com.ronglian.dao.TopicNewsDao;
import com.ronglian.entity.Collection;
import com.ronglian.entity.NewsInfo;
import com.ronglian.entity.NewsPicture;
import com.ronglian.entity.NewsTopic;
import com.ronglian.service.CollectionService;
import com.ronglian.utils.RongLianConstant;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.RongLianUtils;

/**
 * @author liyang
 * @createTime 2018年1月2日
 */
@Service
public class CollectionServiceImpl implements CollectionService {

	/**
	 * @parameter
	 */
	@Autowired
	private CollectionDao collectionDao;
	@Autowired
	private NewsInfoDao newsInfoDao;
	@Autowired
	private NewsPictureDao newsPictureDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private TopicNewsDao topicAndNewsDao;

	/**
	 * @author liyang
	 * @createTime 2018年1月2日
	 */
	@Override
	public RongLianResult insertUserCollection(Collection collection) {
		// TODO Auto-generated method stub
		String userId = collection.getUserId();
		String newsId = collection.getNewsId();
		String deviceId = collection.getDeviceId();
		Collection result = null;
		if (StringUtils.isBlank(newsId) || StringUtils.isBlank(deviceId)) {
			return RongLianResult.build(200, "request params must not be null");
		}

		NewsInfo newsInfo = this.newsInfoDao.findOne(newsId);
		if (newsInfo == null) {
			return RongLianResult
					.build(200,
							"NewsInfo,the mapping of newsId is not exit,perhaps the param newsId was wrong");
		}
		if (StringUtils.isNotBlank(userId)) {
			result = this.collectionDao
					.selectCollectionByUserId(userId, newsId);
		} else {
			result = this.collectionDao.selectCollectionByDeviceId(deviceId,
					newsId);
		}
		if (result != null) {
			return RongLianResult.build(200,
					"save failed as you have saved this news");
		}
		collection.setIsTopic(newsInfo.getIsTopic());
		collection.setTopicUniqueId(newsInfo.getTopicUniqueId());
		collection.setCreateTime(new Date());
		collection.setCollectionId(UUID.randomUUID().toString());
		result = this.collectionDao.save(collection);
		return RongLianResult.ok(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ronglian.service.CollectionService#getUserCollection(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public RongLianResult getUserCollection(String userId, String deviceId,
			Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		List<Collection> list = null;
		Integer start = (pageNo - 1) * pageSize;
		if (StringUtils.isBlank(deviceId)) {
			return RongLianResult.build(200, "deviceId can not be null");
		}
		if (StringUtils.isBlank(userId)) {
			// list =
			// this.collectionDao.selectCollectionListByDeviceId(deviceId);
			list = this.collectionDao
					.selectCollectionListByDeviceIdSort(deviceId);
		} else {
			list = this.collectionDao.selectCollectionListByUserIdSort(userId);
		}
		if (list == null || list.size() < 1) {
			return RongLianResult.build(200, "you have never collected News");
		}
		List<String> newsIdList = new ArrayList<String>();
		Map<String, Collection> map = new HashMap<String, Collection>();
		String newsId = null;
		for (Collection collect : list) {
			newsId = collect.getNewsId();
			newsIdList.add(newsId);
			map.put(newsId, collect);
		}
		List<NewsInfo> newsList = this.newsInfoDao.selectPageInfo(newsIdList,
				start, pageSize);
		List<Map> result = this.changeDataContent(newsList, map);
		return RongLianResult.ok(result);
	}

	@Override
	public RongLianResult delCollectionById(String collectionId) {
		this.collectionDao.delete(collectionId);
		return RongLianResult.ok();
	}

	/*
	 * 输出新闻列表的数据转换
	 */
	public List<Map> changeDataContent(List<NewsInfo> list,
			Map<String, Collection> map) {
		List<Map> resultList = new ArrayList<Map>();
		for (NewsInfo news : list) {
			Map resultMap = new HashMap();
			resultMap.put("newsTitle", news.getNewsTitle());
			String newsId = news.getNewsId();
			Collection collect = map.get(newsId);
			resultMap.put("collectionId", collect.getCollectionId());
			resultMap.put("userId", collect.getUserId());
			resultMap.put("deviceId", collect.getDeviceId());
			resultMap.put("newsId", newsId);

			resultMap.put("newsTags", news.getNewsTags());
			resultMap.put("channelName", news.getChannelName());
			resultMap.put("channelUniqueId", news.getChannelUniqueId());
			resultMap.put("publishTime",
					RongLianUtils.getUTCtime(news.getPublishTime()));

			resultMap.put("newsSort", news.getNewsSort());
			resultMap.put("showType", news.getShowType());
			resultMap.put("fullColumnImgUrl", news.getFullColumnImgUrl());
			resultMap.put("hasVideo", news.getHasVideo());
			resultMap.put("isLive", news.getIsLive());
			resultMap.put("isLiveReplay", news.getIsLiveReplay());
			// 追加5个字段
			resultMap.put("appointCoverImage ", news.getAppointCoverImage());
			resultMap.put("liveUrl", news.getLiveUrl());
			resultMap.put("liveReplayUrl", news.getLiveReplayUrl());
			resultMap.put("liveHostChatid", news.getLiveHostChatid());
			resultMap.put("liveUsChatid", news.getLiveUsChatid());
			
			//增加专题部分信息
			Integer isTopic = news.getIsTopic();
			resultMap.put("isTopic",isTopic);
			String topicUniqueId = null;
			if(isTopic == 1){
				topicUniqueId = news.getTopicUniqueId();
				resultMap.put("topicUniqueId", topicUniqueId);
			}
			if(topicUniqueId != null){
				NewsTopic topic = this.topicDao.getNewsTopicByTopicId(topicUniqueId);
				if (topic != null) {
					Map topicDetail = new HashMap();
					topicDetail.put("topicDesc", topic.getTopicDesc());
					topicDetail.put("bannerPhoto", topic.getBannerImage());
					topicDetail.put("coverPhoto", topic.getListImage());
					resultMap.put("topicDetail", topicDetail);
				}
			}
			
			// 追加2个字段
			resultMap.put("link", news.getLink());
			resultMap.put("dataMode", news.getDataMode());
			// 视频相关字段
			Integer videoDuration = null;
			videoDuration = news.getVideoDuration();
			resultMap.put("videoDuration", videoDuration);
			// 照片总数
			Integer imageCount = news.getImageList();
			if (imageCount == null) {
				imageCount = 0;
			}
			resultMap.put("imageCount", imageCount);
			resultMap.put("photoList", null);
			if (imageCount > 0) {
				List<NewsPicture> pictures = this.newsPictureDao
						.selectNewsPictureByNewsId(news.getNewsId());
				if (pictures != null && pictures.size() > 0) {
					List<Map> photoList = new ArrayList<Map>();
					for (NewsPicture picture : pictures) {
						Map photo = new HashMap();
						photo.put("pictureId", picture.getPictureId());
						photo.put("picPath", picture.getImagePath());
						photo.put("picTitle", picture.getPictureTitle());
						photo.put("picDesc", picture.getPictureDesc());
						photoList.add(photo);
					}
					resultMap.put("photoList", photoList);
				}
			}
			resultList.add(resultMap);
		}
		return resultList;
	}
}
