/**
 * 
 */
package com.ronglian.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ronglian.dao.NewsInfoDao;
import com.ronglian.entity.NewsInfo;
import com.ronglian.entity.NewsPicture;
import com.ronglian.entity.NewsTopic;
import com.ronglian.service.NewsInfoService;
import com.ronglian.utils.PageCountResult;
import com.ronglian.utils.PageResult;
import com.ronglian.utils.RongLianConstant;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.RongLianUtils;
import com.ronglian.utils.model.request.ImageInfo;
import com.ronglian.dao.NewsPictureDao;
import com.ronglian.dao.TopicDao;
import com.ronglian.dao.TopicNewsDao;

/**
 * @author liyang
 * @createTime 2017/12/27
 */
@Service
public class NewsInfoServiceImpl implements NewsInfoService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ronglian.service.NewsInfoService#inserNewsInfo(com.ronglian.entity.
	 * NewsInfo)
	 */
	@Autowired
	private NewsInfoDao newsInfoDao;
	@Autowired
	private NewsPictureDao newsPictureDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private TopicNewsDao topicAndNewsDao;

	@Override
	public RongLianResult inserNewsInfo(NewsInfo newsInfo) {
		NewsInfo result = this.newsInfoDao.save(newsInfo);
		if (result != null) {
			return RongLianResult.ok();
		} else {
			return RongLianResult.build(200, "save failed");
		}
	}

	/**
	 * @author liyang
	 * @createTime 2017骞�12鏈�27鏃�
	 * @updateYime 2018/3/9
	 */
	@Override
	public PageCountResult findNewsList(int pageSize, int pageNo, String channelUniqueId) {
		int start = 0;
		int counter = 0;
		List<Map> resultList = new ArrayList<Map>();
		if (StringUtils.isBlank(channelUniqueId)) {
			return PageCountResult.error(200, "channelUniqueId can not be null", pageNo, pageSize);
		}
		start = (pageNo - 1) * pageSize;
		List<NewsInfo> list = this.newsInfoDao.selectNewsInfoByChannel(channelUniqueId, start, pageSize);
		if (list == null || list.size() == 0) {
			return PageCountResult.error(200, "result is null", pageNo, pageSize);
		}
		// counter = list.size();
		counter = this.newsInfoDao.countNewsInfoByChannel(channelUniqueId);
		resultList = this.changeDataContent(list);
		return PageCountResult.build(0, "ok", counter, pageNo, pageSize, resultList);
	}

	/**
	 * @author liyang
	 * @createTime 2017骞�12鏈�27鏃�
	 */
	@Override
	public RongLianResult findTopnewsList(String channelUniqueId) {
		if (StringUtils.isBlank(channelUniqueId)) {
			return RongLianResult.build(200, "channelUniqueId can not be null");
		}
		List<Map> resultList = new ArrayList<Map>();
		List<NewsInfo> list = this.newsInfoDao.selectTopnewsByChannel(channelUniqueId);
		if (list == null || list.size() == 0) {
			return RongLianResult.build(200, "result is null");
		}
		resultList = this.changeDataContent(list);
		return RongLianResult.ok(resultList);
	}

	/**
	 * @author liyang
	 * @createTime 2017骞�12鏈�27鏃�
	 */
	@Override
	public RongLianResult findEditorNewsList(String channelUniqueId) {
		if (StringUtils.isBlank(channelUniqueId)) {
			return RongLianResult.build(200, "channelUniqueId can not be null");
		}
		List<Map> resultList = new ArrayList<Map>();
		List<NewsInfo> list = this.newsInfoDao.selectEditorNewsByChannel(channelUniqueId);
		if (list == null || list.size() == 0) {
			return RongLianResult.build(200, "result is null");
		}
		resultList = this.changeDataContent(list);
		return RongLianResult.ok(resultList);
	}

	/**
	 * @author liyang
	 * @createTime 2017骞�12鏈�27鏃�
	 */
	@Override
	public PageCountResult findTopicNewsList(String topicId, int pageNo, int pageSize) {
		if (topicId == null) {
			return PageCountResult.error(200, "topicId can not be null ", pageNo, pageSize);
		}
		pageNo = (pageNo - 1) * pageSize;
		int count = 0;
		List<NewsInfo> newsInfoList = this.newsInfoDao.selectTopicNewsByNewsInfoId(topicId, pageNo, pageSize);
		int counter = this.newsInfoDao.selectTopicNewsByNewsInfoId(topicId,0,200).size();
		List<Map> resultList = new ArrayList<Map>();
		count = newsInfoList.size();
		if (newsInfoList == null || count == 0) {
			return PageCountResult.error(200, "result is null", pageNo, pageSize);
		}
		resultList = this.changeDataContent(newsInfoList);
		return PageCountResult.build(0, "ok", counter, pageNo, pageSize, resultList);
	}

	/**
	 * @author liyang
	 * @createTime 2017骞�12鏈�27鏃�
	 */
	@Override
	public RongLianResult getNewsInfoContent(String newsId) {
		if (newsId == null) {
			return RongLianResult.build(200, "newsId can not be null");
		}
		NewsInfo newsInfo = this.newsInfoDao.findOne(newsId);
		if (newsInfo != null) {
			Map data = new HashMap();
			data.put("incNo", newsInfo.getContentId());
			data.put("newsContent", newsInfo.getNewsContent());
			data.put("newsOrganization", newsInfo.getNewsOrganization());
			data.put("newsAuthors", newsInfo.getNewsAuthors());
			data.put("publishTime", RongLianUtils.changeDateTime(newsInfo.getPublishTime()));
			data.put("newsTitle", newsInfo.getNewsTitle());
			data.put("appriseUPCount", newsInfo.getAppriseUpNum());
			data.put("appriseDownCount", newsInfo.getAppriseDownNum());
			data.put("commentNum", newsInfo.getCommentNum());
			data.put("newsTags", newsInfo.getNewsTags());
			data.put("videoDuration", newsInfo.getVideoDuration());
			data.put("shareNum",newsInfo.getShareNum());
			data.put("hasVideo",newsInfo.getHasVideo());
			data.put("isLive",newsInfo.getIsLive());
			data.put("isLiveReplay",newsInfo.getIsLiveReplay());
			data.put("appointCoverImage",newsInfo.getAppointCoverImage());
			data.put("liveUrl",newsInfo.getLiveUrl());
			data.put("liveReplayUrl",newsInfo.getLiveReplayUrl());
			data.put("liveHostChatid",newsInfo.getLiveHostChatid());
			data.put("liveUsChatid",newsInfo.getLiveUsChatid());
			
			Integer imageCount = newsInfo.getImageList();
			if (imageCount == null) {
				imageCount = 0;
			}
			data.put("imageCount", imageCount);

			if (imageCount > 0) {
				List<NewsPicture> pictures = this.newsPictureDao.selectNewsPictureByNewsId(newsInfo.getNewsId());
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
					data.put("imageList", photoList);
				} else {
					data.put("imageList", null);
				}
			} else {
				data.put("imageList", null);
			}
			return RongLianResult.ok(data);
		} else {
			return RongLianResult.build(200, "The content of newsInfo is null ");
		}
	}

	@Override
	/**
	 * 鍒樼�氬崥 imedia鍚庡彴鍚屾鏂伴椈鍐呭
	 */
	public RongLianResult addNewsInfo(String newsStr)
			throws JsonParseException, JsonMappingException, IOException, NumberFormatException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/* try{ */
		Map mapRes = mapper.readValue(newsStr, Map.class);
		Map map = (Map) mapRes.get("data");
		if (map != null) {
			if (map.get("newsId") == null) {
				return RongLianResult.build(200, "newsId can not be null");
			} else {
				if (map.get("channelUniqueId") == null || map.get("channelName") == null) {
					return RongLianResult.build(200, "channelUniqueId or channelName can not be null");
				}
				/*
				 * @author liyang
				 * 
				 * @createTime 2018骞�2鏈�5鏃� 杩藉姞杩斿洖瀛楁 鑾峰彇涓撻淇℃伅
				 */
				String topicUniqueId = null;
				Object obj = map.get("topicUniqueId");
				if (obj != null) {
					topicUniqueId = obj.toString();
				}
				/*
				 * @author liyang
				 * 
				 * @createTime 2018骞�2鏈�5鏃� 杩藉姞杩斿洖瀛楁
				 * 鑾峰彇鐩存挱锛歭iveUrl銆乴iveReplayUrl銆乴iveHostChatid銆乴iveUsChatid銆�
				 * appointCoverImage
				 */
				NewsInfo newsInfo = new NewsInfo(map.get("newsId").toString(),
						(map.get("canComment") != null) ? map.get("canComment").toString() : null,
						(map.get("channelUniqueId") != null) ? map.get("channelUniqueId").toString() : null,
						(map.get("channelName") != null) ? map.get("channelName").toString() : null, null,
						(map.get("contentId") != null) ? (int) map.get("contentId") : null, null,
						(map.get("createTime") != null) ? sdf.parse(map.get("createTime").toString()) : null,
						(map.get("editExpire") != null) ? sdf.parse(map.get("editExpire").toString()) : null, null,
						(map.get("isEditRecom") != null)
								? (map.get("isEditRecom").toString().equals("true") ? (byte) 1 : (byte) 0) : null,
						(map.get("isToTop") != null)
								? (map.get("isToTop").toString().equals("true") ? (byte) 1 : (byte) 0) : null,
						// (map.get("isTopic")!=null)?(int)map.get("isTopic"):null,
						null, null, null, null,
						(map.get("modifyTime") != null) ? sdf.parse(map.get("modifyTime").toString()) : null,
						(map.get("newsAuthors") != null) ? map.get("newsAuthors").toString() : null,
						(map.get("newsContent") != null) ? map.get("newsContent").toString() : null,
						(map.get("newsOrganization") != null) ? map.get("newsOrganization").toString() : null,
						(map.get("newsOriginal") != null) ? (int) map.get("newsOriginal") : null,
						(map.get("newsSort") != null) ? (int) map.get("newsSort") : null,
						(map.get("newsSource") != null) ? map.get("newsSource").toString() : null,
						(map.get("newsSourceUrl") != null) ? map.get("newsSourceUrl").toString() : null, null,
						(map.get("newsSummary") != null) ? map.get("newsSummary").toString() : null,
						(map.get("newsTags") != null) ? map.get("newsTags").toString() : null,
						(map.get("newsTitle") != null) ? map.get("newsTitle").toString() : null,
						(map.get("publishTime") != null) ? sdf.parse(map.get("publishTime").toString()) : null,
						(map.get("topExpire") != null) ? sdf.parse(map.get("topExpire").toString()) : null, null, null,
						(map.get("dataStatus") != null) ? (int) map.get("dataStatus") : null,
						(map.get("showType") != null) ? (int) map.get("showType") : null,
						(map.get("fullColumnImgUrl") != null) ? map.get("fullColumnImgUrl").toString() : null,
						(map.get("hasVideo") != null)
								? (map.get("hasVideo").toString().equals("true") ? (byte) 1 : (byte) 0) : null,
						(map.get("isLive") != null)
								? (map.get("isLive").toString().equals("true") ? (byte) 1 : (byte) 0) : null,
						(map.get("isLiveReplay") != null)
								? (map.get("isLiveReplay").toString().equals("true") ? (byte) 1 : (byte) 0) : null,
						topicUniqueId);
				/*
				 * @author liyang
				 * 
				 * @createTime 2018骞�2鏈�5鏃� 杩藉姞 鐩存挱鐨勫瓧娈碉細String liveUrl String
				 * liveReplayUrl String liveHostChatid String liveUsChatid
				 * String appointCoverImage
				 */
				String liveUrl = null;
				String liveReplayUrl = null;
				String liveHostChatid = null;
				String liveUsChatid = null;
				String appointCoverImage = null;
				Integer topnewsSort = null;

				Byte isTopnews = null;
				Byte isEditRecom = null;
				Byte isTopnewsTotop = null;
				Byte isLive = null;
				Byte isLiveReplay = null;
				Byte isToTop = null;
				Integer isTopic = null;

				obj = map.get("isTopic");
				if (obj != null) {
					isTopic = Integer.parseInt(obj.toString());
				}
				obj = map.get("topnewsSort");
				if (obj != null) {
					topnewsSort = Integer.parseInt(obj.toString());
				}
				obj = map.get("isToTop");
				if (obj != null) {
					isToTop = Byte.parseByte(obj.toString());
				}
				obj = map.get("isLive");
				if (obj != null) {
					isLive = Byte.parseByte(obj.toString());
				}
				obj = map.get("isLiveReplay");
				if (obj != null) {
					isLiveReplay = Byte.parseByte(obj.toString());
				}
				obj = map.get("isTopnews");
				if (obj != null) {
					isTopnews = Byte.parseByte(obj.toString());
				}
				obj = map.get("isEditRecom");
				if (obj != null) {
					isEditRecom = Byte.parseByte(obj.toString());
				}
				obj = map.get("isTopnewsTotop");
				if (obj != null) {
					isTopnewsTotop = Byte.parseByte(obj.toString());
				}
				obj = map.get("liveUrl");
				if (obj != null) {
					liveUrl = obj.toString();
				}
				obj = map.get("liveReplayUrl");
				if (obj != null) {
					liveReplayUrl = obj.toString();
				}
				obj = map.get("liveHostChatid");
				if (obj != null) {
					liveHostChatid = obj.toString();
				}
				obj = map.get("liveUsChatid");
				if (obj != null) {
					liveUsChatid = obj.toString();
				}
				obj = map.get("appointCoverImage");
				if (obj != null) {
					appointCoverImage = obj.toString();
				}
				newsInfo.setIsTopic(isTopic);
				newsInfo.setLiveUrl(liveUrl);
				newsInfo.setLiveReplayUrl(liveReplayUrl);
				newsInfo.setLiveHostChatid(liveHostChatid);
				newsInfo.setLiveUsChatid(liveUsChatid);
				newsInfo.setAppointCoverImage(appointCoverImage);
				newsInfo.setIsEditRecom(isEditRecom);
				newsInfo.setIsTopnews(isTopnews);
				newsInfo.setIsTopnewsTotop(isTopnewsTotop);
				newsInfo.setIsLive(isLive);
				newsInfo.setIsLiveReplay(isLiveReplay);
				newsInfo.setIsToTop(isToTop);
				newsInfo.setTopnewsSort(topnewsSort);
				/*
				 * @author liyang
				 * 
				 * @createTime 2018骞�3鏈�5鏃� 杩藉姞 鐨勫瓧娈碉細String link Byte dataMode
				 */
				String link = null;
				Byte dataMode = null;
				obj = map.get("link");
				if (obj != null) {
					link = obj.toString();
				}
				obj = map.get("dataMode");
				if (obj != null) {
					dataMode = Byte.parseByte(obj.toString());
				}
				newsInfo.setDataMode(dataMode);
				newsInfo.setLink(link);
				
				/*
				 * @author liyang
				 * 
				 * @createTime 20180315 添加视频总长（单位秒）
				 * */
				Integer videoDuration  = null;
				obj = map.get("videoDuration");
				if (obj != null) {
					videoDuration = Integer.parseInt(obj.toString());
				}
				newsInfo.setVideoDuration(videoDuration);
				// 褰曞叆APP鍚庡彴鏁版嵁搴�
				newsPictureDao.deleteByNewsID(newsInfo.getNewsId());
				int i = 0;
				boolean less = false;
				if (newsInfo.getNewsContent() != null) {
					String[] imgs = getImgs(newsInfo.getNewsContent());
					if (imgs != null) {
						for (; i < imgs.length; i++) {
							NewsPicture newsPicture = new NewsPicture(newsInfo.getNewsId(),
									newsInfo.getNewsId() + "_" + i, imgs[i], i);
							newsPictureDao.save(newsPicture);
						}
						less = true;
					}
				}
				if (map.get("imageList") != null) {
					List<Map> imageList = (List<Map>) map.get("imageList");
					for (Map imageInfoMap : imageList) {
						NewsPicture newsPicture = new NewsPicture(newsInfo.getNewsId(), newsInfo.getNewsId() + "_" + i,
								imageInfoMap.get("picPath") != null ? imageInfoMap.get("picPath").toString() : null,
								imageInfoMap.get("picDesc") != null ? imageInfoMap.get("picDesc").toString() : null,
								imageInfoMap.get("picTitle") != null ? imageInfoMap.get("picTitle").toString() : null,
								i);
						i++;
						newsPictureDao.save(newsPicture);
					}
				}
				newsInfo.setImageList(i);
				this.newsInfoDao.save(newsInfo);
				return RongLianResult.ok();
			}
		} else {
			return RongLianResult.build(200, "maybe requestBody is null");
		}
	}

	private String[] getImgs(String content) {
		String img = "";
		Pattern p_image;
		Matcher m_image;
		String str = "";
		String[] images = null;
		String regEx_img = "<(img|IMG)(.*?)(/>|></img>|>)";
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(content);
		while (m_image.find()) {
			img = m_image.group();
			Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
			while (m.find()) {
				String tempSelected = m.group(1);

				if ("".equals(str)) {
					str = tempSelected;
				} else {
					String temp = tempSelected;
					str = str + "," + temp;
				}
			}
		}

		if (!"".equals(str)) {
			String cutStr = str.replace(",http:", " ,http:");
			images = cutStr.split(" ,");
		}
		return images;
	}

	/**
	 * @author liyang
	 * @createTime 2017骞�12鏈�27鏃�
	 */
	@Override
	public RongLianResult getPhotoNewsByNewsId(String newsID, Integer incNo) {
		if (incNo == null) {
			return RongLianResult.build(200, "the param incNo can not be null");
		}
		List<NewsInfo> list = this.newsInfoDao.selectNewsInfoNearUpByIncNo(incNo);
		List<NewsInfo> list2 = this.newsInfoDao.selectNewsInfoNearDownByIncNo(incNo);
		if (list != null) {
			list.addAll(list2);
		} else {
			list = list2;
		}
		if (list != null && list.size() > 0) {
			List<Map> resultList = new ArrayList<Map>();

			for (NewsInfo news : list) {
				Map result = new HashMap();
				result.put("newsTitle", news.getNewsTitle());
				result.put("newsId", news.getNewsId());
				result.put("newsTags", news.getNewsTags());
				result.put("publishTime", RongLianUtils.changeDateTime(news.getPublishTime()));
				result.put("newsSort", news.getNewsSort());

				List<NewsPicture> photos = this.newsPictureDao.selectNewsPictureByNewsId(news.getNewsId());
				List pictureList = new ArrayList();
				for (NewsPicture picture : photos) {
					Map photo = new HashMap();
					photo.put("pictureId", picture.getPictureId());
					photo.put("picTitle", picture.getPictureTitle());
					photo.put("picPath", picture.getImagePath());
					photo.put("picDesc", picture.getPictureDesc());
					pictureList.add(photo);
				}
				result.put("pictureList", pictureList);
				resultList.add(result);
			}
			//
			if (resultList.size() == 4 || resultList.size() == 2 || resultList.size() == 1) {
				return RongLianResult.ok(resultList);
			} else {
				//
				resultList.remove(0);
				return RongLianResult.ok(resultList);
			}
		} else {
			return RongLianResult.build(200, "the result is null");
		}
	}

	@Override
	public RongLianResult getTopnewsAhead() {
		// TODO Auto-generated method stub
		List<NewsInfo> list = this.newsInfoDao.selectTopnewsAhead();
		if (list == null || !(list.size() > 0)) {
			return RongLianResult.build(200, "result is null", null);
		}
		List resultList = this.changeDataContent(list);
		return RongLianResult.ok(resultList);
	}

	/*
	 * @param List<NewsInfo> list ----鏂伴椈鍒楄〃 鎶奻ind鐨勬暟鎹紝缁勫悎鍙樻崲鎴愬墠绔渶瑕佺殑鏍煎紡锛�
	 */
	public List<Map> changeDataContent(List<NewsInfo> list) {
		List<Map> resultList = new ArrayList<Map>();
		for (NewsInfo news : list) {
			Map resultMap = new HashMap();
			resultMap.put("newsTitle", news.getNewsTitle());
			resultMap.put("newsId", news.getNewsId());
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
			// 鍒ゆ柇鏄惁鏄笓棰�
			Integer isTopic = news.getIsTopic();
			resultMap.put("isTopic", news.getIsTopic());
			String topicUniqueId = null;
			if (isTopic > 0) {
				topicUniqueId = news.getTopicUniqueId();
				resultMap.put("topicUniqueId", topicUniqueId);
			}
			//
			if (StringUtils.isNotBlank(topicUniqueId)) {
				// 鏍规嵁涓撻鐨剈niqueId鏌ヨ涓撻
				NewsTopic topic = this.topicDao.getNewsTopicByTopicId(topicUniqueId);
				if (topic != null) {
					Map topicDetail = new HashMap();
					topicDetail.put("topicDesc", topic.getTopicDesc());
					topicDetail.put("bannerPhoto", topic.getBannerImage());
					topicDetail.put("coverPhoto", topic.getListImage());
					resultMap.put("topicDetail", topicDetail);
				}
			}
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
			if (StringUtils.isNotBlank(topicUniqueId)) {
				resultList = this.findTopicNews(resultList, topicUniqueId, RongLianConstant.TOPIC_NEWS_NUMBER);
			}
		}
		return resultList;
	}

	/*
	 * 鏍规嵁涓撻鐨剈niqueId 鏌ヨ涓撻鏄犲皠鐨勬柊闂诲垪琛ㄣ�傘�傘�傘�傘�傘�傘�傘��
	 * 
	 * @param int n ---- 涓嶈秴杩噉鏉℃柊闂绘墠浼氭樉绀�
	 * 
	 * @param String topicUniqueId ----涓撻id
	 * 
	 * @param resultList ----杩斿洖鐨勭粨鏋滈泦
	 */
	public List<Map> findTopicNews(List<Map> resultList, String topicUniqueId,int n) {
		List<String> newsIdList = this.topicAndNewsDao.selectNewsInfoIdByTopic(topicUniqueId);
		if (newsIdList != null && newsIdList.size() <= n) {
			List<NewsInfo> newsInfoList = this.newsInfoDao.selectByNewsID(newsIdList);
			List<Map> topicNewsList = new ArrayList<Map>();
			for (NewsInfo newsInfo : newsInfoList) {
				Map topicNews = new HashMap();
				topicNews.put("newsTitle", newsInfo.getNewsTitle());
				topicNews.put("newsId", newsInfo.getNewsId());
				topicNews.put("newsTags", newsInfo.getNewsTags());
				topicNews.put("channelName", newsInfo.getChannelName());
				topicNews.put("channelUniqueId", newsInfo.getChannelUniqueId());
				topicNews.put("publishTime", RongLianUtils.changeDateTime(newsInfo.getPublishTime()));
				topicNews.put("newsSort", newsInfo.getNewsSort());
				topicNews.put("showType", newsInfo.getShowType());
				topicNews.put("fullColumnImgUrl", newsInfo.getFullColumnImgUrl());
				topicNews.put("hasVideo", newsInfo.getHasVideo());
				topicNews.put("isLive", newsInfo.getIsLive());
				topicNews.put("isLiveReplay", newsInfo.getIsLiveReplay());
				// 杩藉姞鐩存挱5涓瓧娈�
				topicNews.put("appointCoverImage ", newsInfo.getAppointCoverImage());
				topicNews.put("liveUrl", newsInfo.getLiveUrl());
				topicNews.put("liveReplayUrl", newsInfo.getLiveReplayUrl());
				topicNews.put("liveHostChatid", newsInfo.getLiveHostChatid());
				topicNews.put("liveUsChatid", newsInfo.getLiveUsChatid());
				// 杩藉姞dataMode銆乴ink涓や釜瀛楁
				topicNews.put("link", newsInfo.getLink());
				topicNews.put("dataMode", newsInfo.getDataMode());
				//视频总长
				topicNews.put("videoDuration", newsInfo.getVideoDuration());
				// 鍒ゆ柇鏄惁鏄笓棰�
				topicNews.put("isTopic", newsInfo.getIsTopic());
				topicNewsList.add(topicNews);
			}
			int position = resultList.size() - 1;
			Map topicListBody = resultList.get(position);
			topicListBody.put("topicNewsList", topicNewsList);
			resultList.set(position, topicListBody);
		}
		return resultList;
	}

}