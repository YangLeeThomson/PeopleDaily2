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
 * @createTime 2018��1��2��
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
	 * @createTime 2018��1��2��
	 */
	@Override
	public RongLianResult insertUserCollection(Collection collection) {
		// TODO Auto-generated method stub
		String userId = collection.getUserId();
		String newsId = collection.getNewsId();
		String deviceId = collection.getDeviceId();
		Collection result = null;
		if (StringUtils.isNotBlank(newsId) && StringUtils.isNotBlank(deviceId)) {
			// �û���¼״̬ʱ
			if (StringUtils.isNotBlank(userId)) {
				result = this.collectionDao.selectCollectionByUserId(userId,
						newsId);
			} else {// û�е�¼ʱ
				result = this.collectionDao.selectCollectionByDeviceId(
						deviceId, newsId);
			}
			// �ж�Result�����ݿ����Ƿ����
			if (result == null) {
				// ���ݿⲻ���ڣ����ղظ�����
				collection.setCreateTime(new Date());
				collection.setCollectionId(UUID.randomUUID().toString());
				result = this.collectionDao.save(collection);
				return RongLianResult.ok(result);
			} else {
				// ���ݿ���ڸ�����
				return RongLianResult.build(200,
						"save failed��as you have saved this news");
			}
		} else {
			return RongLianResult.build(200, "request params must not be null");
		}
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
		Integer start = (pageNo-1) * pageSize;
		if (StringUtils.isBlank(deviceId)) {
			return RongLianResult.build(200, "deviceId can not be null");
		}
		if (StringUtils.isBlank(userId)) {
//			list = this.collectionDao.selectCollectionListByDeviceId(deviceId);
			list = this.collectionDao.selectCollectionListByDeviceIdSort(deviceId);
		}else{
			list = this.collectionDao.selectCollectionListByUserIdSort(userId);
		}
		if(list == null || list.size() < 1){
			return RongLianResult.build(200, "you have never collected News");
		}
		List<String> newsIdList = new ArrayList<String>();
		Map<String,Collection> map = new HashMap<String,Collection>();
		String newsId = null;
		for(Collection collect:list){
			newsId = collect.getNewsId();
			newsIdList.add(newsId);
			map.put(newsId, collect);
		}
		List<NewsInfo> newsList = this.newsInfoDao.selectPageInfo(newsIdList,start,pageSize);
		List<Map> result  = this.changeDataContent(newsList, map);
		return RongLianResult.ok(result);
	}

	@Override
	// 3��5�Ձ�����һ����д
	public RongLianResult delCollectionById(String collectionId) {
		this.collectionDao.delete(collectionId);
		return RongLianResult.ok();
	}

	/*
	 * @param List<NewsInfo> list ----鏂伴椈鍒楄〃 鎶奻ind鐨勬暟鎹紝缁勫悎鍙樻崲鎴愬墠绔渶瑕佺殑鏍煎紡锛�
	 */
	public List<Map> changeDataContent(List<NewsInfo> list,Map<String,Collection> map) {
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
			resultMap.put("publishTime", RongLianUtils.changeDateTime(news.getPublishTime()));
			resultMap.put("newsSort", news.getNewsSort());
			resultMap.put("showType", news.getShowType());
			resultMap.put("fullColumnImgUrl", news.getFullColumnImgUrl());
			resultMap.put("hasVideo", news.getHasVideo());
			resultMap.put("isLive", news.getIsLive());
			resultMap.put("isLiveReplay", news.getIsLiveReplay());
			// 杩藉姞鐩存挱5涓瓧娈�
			resultMap.put("appointCoverImage ", news.getAppointCoverImage());
			resultMap.put("liveUrl", news.getLiveUrl());
			resultMap.put("liveReplayUrl", news.getLiveReplayUrl());
			resultMap.put("liveHostChatid", news.getLiveHostChatid());
			resultMap.put("liveUsChatid", news.getLiveUsChatid());
			// 杩藉姞dataMode銆乴ink涓や釜瀛楁
			resultMap.put("link", news.getLink());
			resultMap.put("dataMode", news.getDataMode());
			//视频总长
			resultMap.put("videoDuration", news.getVideoDuration());
			//视频相关字段
			Integer videoDuration = null;
			videoDuration = news.getVideoDuration();
			resultMap.put("videoDuration", videoDuration);
			// 鏌ョ湅鍥剧墖
			Integer imageCount = news.getImageList();
			if (imageCount == null) {
				imageCount = 0;
			}
			resultMap.put("imageCount", imageCount);
			resultMap.put("photoList", null);
			if (imageCount > 0) {
				List<NewsPicture> pictures = this.newsPictureDao.selectNewsPictureByNewsId(news.getNewsId());
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
