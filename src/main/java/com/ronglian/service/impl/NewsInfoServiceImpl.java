/**
 * 
 */
package com.ronglian.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ronglian.dao.NewsInfoDao;
import com.ronglian.entity.NewsAuthor;
import com.ronglian.entity.NewsInfo;
import com.ronglian.entity.NewsPicture;
import com.ronglian.entity.NewsTopic;
import com.ronglian.jedis.JedisDao;
import com.ronglian.service.NewsInfoService;
import com.ronglian.utils.GetRequestJsonUtils;
import com.ronglian.utils.HttpClientUtil;
import com.ronglian.utils.JsonUtils;
import com.ronglian.utils.PageCountResult;
import com.ronglian.utils.PageResult;
import com.ronglian.utils.RongLianConstant;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.RongLianUtils;
import com.ronglian.utils.model.request.ImageInfo;
import com.ronglian.dao.NewsAuthorDao;
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
	@Autowired
	private NewsAuthorDao newsAuthorDao;
	@Autowired
	private JedisDao jedisDao;

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
	 * @createTime 2017/12/27
	 * @updateYime 2018/3/9
	 */
	@Override
	public PageCountResult findNewsList(int pageSize, int pageNo,
			String channelUniqueId, String newsId) {
/*		public PageResult findNewsList(int pageSize, int pageNo,
				String channelUniqueId, String newsId) {*/		
		int start = 0;
		Integer counter = null;
		List<Map> resultList = new ArrayList<Map>();
		if (StringUtils.isBlank(channelUniqueId)) {
//			return PageResult.error(200,"channelUniqueId can not be null", pageNo, pageSize);
			return PageCountResult.error(200,"channelUniqueId can not be null", pageNo, pageSize);
		}
		start = (pageNo - 1) * pageSize;
		List<NewsInfo> list = null;
		String resultStr = jedisDao.get("channelNews"+channelUniqueId+pageSize+pageNo+newsId);
		if(StringUtils.isNotBlank(resultStr)){
			String countStr = jedisDao.get("channelNews"+channelUniqueId+"counter");
			if(StringUtils.isNotBlank(countStr)){
				counter = Integer.parseInt(countStr);
			}
			jedisDao.expire("channelNews"+channelUniqueId+pageSize+pageNo+newsId,RongLianConstant.REDIS_NEWS_EXPIRE);
			resultList = JsonUtils.jsonToList(resultStr, Map.class);
			return PageCountResult.build(200, "OK", counter, pageNo, pageSize, resultList);
//			return PageResult.build(0,"ok",pageNo,pageSize,resultList);
			
		}
		if (newsId == null) {
			list = this.newsInfoDao.selectNewsInfoByChannel(channelUniqueId,start, pageSize);
		} else {
			NewsInfo news = this.newsInfoDao.findOne(newsId);
			if (news == null) {
//				return PageResult.error(200,"newsId was not right",pageNo, pageSize);
				return PageCountResult.error(200,"channelUniqueId can not be null", pageNo, pageSize);
			}
			Date dt = news.getPublishTime();
			list = this.newsInfoDao.selectNewsInfoByChannelAndNewsId(channelUniqueId, start, pageSize, dt);
		}

		if (list == null || list.size() == 0) {
//			return PageResult.error(200, "result is null", pageNo,pageSize);
			return PageCountResult.error(200,"channelUniqueId can not be null", pageNo, pageSize);
		}
		//剔除在专题中添加过的新闻
		
		
		counter = this.newsInfoDao.countNewsInfoByChannel(channelUniqueId);
		resultList = this.changeDataContent(list);
		jedisDao.set("channelNews"+channelUniqueId+pageSize+pageNo+newsId, JsonUtils.objectToJson(resultList));
		jedisDao.set("channelNews"+channelUniqueId+"counter",counter.toString());
		jedisDao.expire("channelNews"+channelUniqueId+pageSize+pageNo+newsId, RongLianConstant.REDIS_NEWS_EXPIRE);
		jedisDao.expire("channelNews"+channelUniqueId+"counter", RongLianConstant.REDIS_NEWS_EXPIRE);
//		return PageResult.build(0,"ok",pageNo,pageSize,resultList);
		return PageCountResult.build(200, "OK", counter, pageNo, pageSize, resultList);
	}

	/**
	 * @author liyang
	 * @createTime 2017/12/27
	 * @updateYime 2018/3/9
	 */
	@Override
	public RongLianResult findTopnewsList(String channelUniqueId) {
		if (StringUtils.isBlank(channelUniqueId)) {
			return RongLianResult.build(200, "channelUniqueId can not be null");
		}
		List<Map> resultList = new ArrayList<Map>();
		String resultStr = jedisDao.get("channelTop"+channelUniqueId);
		if(StringUtils.isNotBlank(resultStr)){
			jedisDao.expire("channelTop"+channelUniqueId,RongLianConstant.REDIS_NEWS_EXPIRE);
			resultList = JsonUtils.jsonToList(resultStr, Map.class);
			return RongLianResult.ok(resultList);
		}
		List<NewsInfo> list = this.newsInfoDao.selectTopnewsByChannel(channelUniqueId);
		if (list == null || list.size() == 0) {
			return RongLianResult.build(200, "result is null");
		}
		resultList = this.changeDataContent(list);
		jedisDao.set("channelTop"+channelUniqueId,JsonUtils.objectToJson(resultList));
		jedisDao.expire("channelTop"+channelUniqueId,RongLianConstant.REDIS_NEWS_EXPIRE);
		return RongLianResult.ok(resultList);
	}

	/**
	 * @author liyang
	 * @createTime 2017/12/27
	 * @updateYime 2018/3/9
	 */
	@Override
	public RongLianResult findEditorNewsList(String channelUniqueId) {
		if (StringUtils.isBlank(channelUniqueId)) {
			return RongLianResult.build(200, "channelUniqueId can not be null");
		}
		List<Map> resultList = new ArrayList<Map>();
		String resultStr = jedisDao.get("editrecommen");
		if(StringUtils.isNotBlank(resultStr)){
			jedisDao.expire("editrecommen"+channelUniqueId,RongLianConstant.REDIS_NEWS_EXPIRE);
			resultList = JsonUtils.jsonToList(resultStr, Map.class);
			return RongLianResult.ok(resultList);
		}
		List<NewsInfo> list = this.newsInfoDao
				.selectEditorNewsByChannel();
		if (list == null || list.size() == 0) {
			return RongLianResult.build(200, "result is null");
		}
		resultList = this.changeDataContent(list);
		jedisDao.set("editrecommen",JsonUtils.objectToJson(resultList));
		jedisDao.expire("editrecommen",RongLianConstant.REDIS_NEWS_EXPIRE);
		return RongLianResult.ok(resultList);
	}

	/**
	 * @author liyang
	 * @createTime 2017/12/27
	 * @updateYime 2018/3/9
	 */
	@Override
	public PageCountResult findTopicNewsList(String topicId, int pageNo,
			int pageSize, String newsId) {
		if (topicId == null) {
			return PageCountResult.error(200, "topicId can not be null ",
					pageNo, pageSize);
		}
		int counter;
		List<Map> resultList = new ArrayList<Map>();
		String resultStr = jedisDao.get("topicNews"+topicId+pageNo+pageSize+newsId);
		String counterStr = jedisDao.get("topicNews"+topicId+"counter");
		
		if(StringUtils.isNotBlank(resultStr) && StringUtils.isNotBlank(counterStr)){
			counter = Integer.valueOf(counterStr);
			jedisDao.expire("topicNews"+topicId+pageNo+pageSize+newsId, RongLianConstant.REDIS_NEWS_EXPIRE);
			jedisDao.expire("topicNews"+topicId+"counter", RongLianConstant.REDIS_NEWS_EXPIRE);
			resultList = JsonUtils.jsonToList(resultStr, Map.class);
			return PageCountResult.build(0, "ok", counter, pageNo, pageSize,resultList);
		}
		int start = (pageNo - 1) * pageSize;
		int count = 0;
		List<NewsInfo> newsInfoList = null;
		if (newsId == null) {
			newsInfoList = this.newsInfoDao.selectTopicNewsByNewsInfoId(topicId, start, pageSize);
		} else {
			NewsInfo news = this.newsInfoDao.findOne(newsId);
			if (news == null) {
				return PageCountResult.error(200, "newsId was not right",pageNo, pageSize);
			}
			Date dd = news.getPublishTime();
			newsInfoList = this.newsInfoDao.selectTopicNewsByNewsId(topicId,start, pageSize, dd);
		}
		counter = this.newsInfoDao.selectTopicNewsByNewsInfoId(topicId, 0,200).size();
		jedisDao.set("topicNews"+topicId+"counter", counter+"");
		jedisDao.expire("topicNews"+topicId+"counter", RongLianConstant.REDIS_NEWS_EXPIRE);
		
		count = newsInfoList.size();
		if (newsInfoList == null || count == 0) {
			return PageCountResult.error(200, "result is null", pageNo,pageSize);
		}
		resultList = this.changeDataContent(newsInfoList);
		jedisDao.set("topicNews"+topicId+pageNo+pageSize+newsId, JsonUtils.objectToJson(resultList));
		jedisDao.expire("topicNews"+topicId+pageNo+pageSize+newsId, RongLianConstant.REDIS_NEWS_EXPIRE);
		return PageCountResult.build(0, "ok", counter, pageNo, pageSize,resultList);
	}

	/**
	 * @author liyang
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @createTime 2017/12/27
	 * @updateYime 2018/3/9
	 */
	@Override
	public RongLianResult getNewsInfoContent(String newsId) throws JsonParseException, JsonMappingException, IOException {
		if (newsId == null) {
			return RongLianResult.build(200, "newsId can not be null");
		}
		Map data = new HashMap();
		Map param = new HashMap<String,String>();
		param.put("newsid", newsId);
		String resultStr = jedisDao.get("newsContent"+newsId);
		if(StringUtils.isNotBlank(resultStr)){
			
			ObjectMapper mapper = new ObjectMapper();
			data = mapper.readValue(resultStr, Map.class);
			String accessStr = jedisDao.get("accessNum"+newsId);
			if(StringUtils.isBlank(accessStr)){
				accessStr = "0";
				}
			int accessNum = Integer.parseInt(accessStr);
			data.put("accessNum", accessNum);
			jedisDao.set("accessNum"+newsId, String.valueOf(accessNum+1));
			jedisDao.expire("newsContent"+newsId, RongLianConstant.REDIS_NEWS_CONTENT_EXPIRE);
			jedisDao.expire("accessNum"+newsId, RongLianConstant.REDIS_NEWS_CONTENT_EXPIRE);
			HttpClientUtil.doGet(RongLianConstant.ACCKNOWLEDAGE_IMEDIA_URL, param);
			return RongLianResult.ok(data);
		}
		NewsInfo newsInfo = this.newsInfoDao.selectNewsInfo(newsId);
		if (newsInfo == null) {
			return RongLianResult.build(200, "The content of newsInfo is null ");
		}
		//通知Imedia系统消息
		this.newsInfoDao.updateReadNum(newsId);
		HttpClientUtil.doGet(RongLianConstant.ACCKNOWLEDAGE_IMEDIA_URL, param);
		//查询作者
		List<NewsAuthor> list = this.newsAuthorDao.selectByNewsId(newsId);
		List<Map> appAuthor = null;
		if (list != null && list.size() > 0) {
			appAuthor = new ArrayList<Map>();
			for (NewsAuthor author : list) {
				Map temp = new HashMap();
				temp.put("authorName", author.getAuthorName());
				temp.put("authorUniqueId", author.getAuthorUniqueId());
				temp.put("photoUrl", author.getPhotoUrl());
				temp.put("mediaName", author.getMediaName());
				appAuthor.add(temp);
			}
		}
		
		data.put("appAuthor", appAuthor);
		data.put("incNo", newsInfo.getContentId());
		data.put("newsContent", newsInfo.getNewsContent());
		data.put("newsOrganization", newsInfo.getNewsOrganization());
		data.put("newsAuthors", newsInfo.getNewsAuthors());
		// data.put("publishTime",
		// RongLianUtils.changeDateTime(newsInfo.getPublishTime()));
		data.put("publishTime",
				RongLianUtils.getUTCtime(newsInfo.getPublishTime()));
		data.put("newsTitle", newsInfo.getNewsTitle());
		data.put("appriseUPCount", newsInfo.getAppriseUpNum());
		data.put("appriseDownCount", newsInfo.getAppriseDownNum());
		data.put("commentNum", newsInfo.getCommentNum());
		data.put("newsTags", newsInfo.getNewsTags());
		data.put("videoDuration", newsInfo.getVideoDuration());
		data.put("shareNum", newsInfo.getShareNum());
		data.put("hasVideo", newsInfo.getHasVideo());
		data.put("isLive", newsInfo.getIsLive());
		data.put("isLiveReplay", newsInfo.getIsLiveReplay());
		data.put("appointCoverImage", newsInfo.getAppointCoverImage());
		data.put("liveUrl", newsInfo.getLiveUrl());
		data.put("liveReplayUrl", newsInfo.getLiveReplayUrl());
		data.put("liveHostChatid", newsInfo.getLiveHostChatid());
		data.put("liveUsChatid", newsInfo.getLiveUsChatid());
		data.put("newsSummary", newsInfo.getNewsSummary());
		data.put("newsSource", newsInfo.getNewsSource());
		//加入文章访问Num字段返回
		data.put("accessNum", newsInfo.getAccessNum());
		data.put("fullColumnImgUrl",newsInfo.getFullColumnImgUrl());
		data.put("showType", newsInfo.getShowType());
		Integer imageCount = newsInfo.getImageList();
		if (imageCount == null) {
			imageCount = 0;
		}
		data.put("imageCount", imageCount);

		if (!(imageCount > 0)){
			data.put("imageList", null);
		}
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
					photo.put("height", picture.getHeight());
					photo.put("width", picture.getWidth());
					photoList.add(photo);
				}
				data.put("imageList", photoList);
			} else {
				data.put("imageList", null);
			}
		}
//		JSONObject jsonObject = JSONObject.fromObject(data);
		jedisDao.set("newsContent"+newsId, JsonUtils.objectToJson(data));
		jedisDao.set("accessNum"+newsId, newsInfo.getAccessNum().toString());
		jedisDao.expire("newsContent"+newsId, RongLianConstant.REDIS_NEWS_CONTENT_EXPIRE);
		jedisDao.expire("accessNum"+newsId, RongLianConstant.REDIS_NEWS_CONTENT_EXPIRE);
		return RongLianResult.ok(data);

	}

	@Override
	/**
	 * @author hamburg
	 * @createTime 2017/12/27
	 * @updateYime 2018/3/9
	 */
	public RongLianResult addNewsInfo(String newsStr)
			throws JsonParseException, JsonMappingException, IOException,
			NumberFormatException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/* try{ */
		Map mapRes = mapper.readValue(newsStr, Map.class);
		Map map = (Map) mapRes.get("data");
		if (map != null) {
			if (map.get("newsId") == null) {
				return RongLianResult.build(200, "newsId can not be null");
			} else {
				if (map.get("channelUniqueId") == null
						|| map.get("channelName") == null) {
					return RongLianResult.build(200,
							"channelUniqueId or channelName can not be null");
				}
				/*
				 * @author liyang
				 * 
				 * @createTime 2018年2月5日追加topicUniqueId
				 */
				String topicUniqueId = null;
				Object obj = map.get("topicUniqueId");
				if (obj != null) {
					topicUniqueId = obj.toString();
				}
				/*
				 * @author liyang
				 * 
				 * @createTime 2018年2月5日
				 * liveUrl、iveReplayUrl、liveHostChatid、liveUsChatid
				 * appointCoverImage
				 */
				NewsInfo newsInfo = new NewsInfo(
						map.get("newsId").toString(),
						(map.get("canComment") != null) ? map.get("canComment")
								.toString() : null,
						(map.get("channelUniqueId") != null) ? map.get(
								"channelUniqueId").toString() : null,
						(map.get("channelName") != null) ? map.get(
								"channelName").toString() : null,
						null,
						(map.get("contentId") != null) ? (int) map
								.get("contentId") : null,
						null,
						(map.get("createTime") != null) ? sdf.parse(map.get(
								"createTime").toString()) : null,
						(map.get("editExpire") != null) ? sdf.parse(map.get(
								"editExpire").toString()) : null,
						null,
						(map.get("isEditRecom") != null) ? (map
								.get("isEditRecom").toString().equals("true") ? (byte) 1
								: (byte) 0)
								: null,
						(map.get("isToTop") != null) ? (map.get("isToTop")
								.toString().equals("true") ? (byte) 1
								: (byte) 0) : null,
						// (map.get("isTopic")!=null)?(int)map.get("isTopic"):null,
						null,
						null,
						null,
						null,
						(map.get("modifyTime") != null) ? sdf.parse(map.get(
								"modifyTime").toString()) : null,
						(map.get("newsAuthors") != null) ? map.get(
								"newsAuthors").toString() : null,
						(map.get("newsContent") != null) ? map.get(
								"newsContent").toString() : null,
						(map.get("newsOrganization") != null) ? map.get(
								"newsOrganization").toString() : null,
						(map.get("newsOriginal") != null) ? (int) map
								.get("newsOriginal") : null,
						(map.get("newsSort") != null) ? (int) map
								.get("newsSort") : null,
						(map.get("newsSource") != null) ? map.get("newsSource")
								.toString() : null,
						(map.get("newsSourceUrl") != null) ? map.get(
								"newsSourceUrl").toString() : null,
						null,
						(map.get("newsSummary") != null) ? map.get(
								"newsSummary").toString() : null,
						(map.get("newsTags") != null) ? map.get("newsTags")
								.toString() : null,
						(map.get("newsTitle") != null) ? map.get("newsTitle")
								.toString() : null,
						(map.get("publishTime") != null) ? sdf.parse(map.get(
								"publishTime").toString()) : null,
						(map.get("topExpire") != null) ? sdf.parse(map.get(
								"topExpire").toString()) : null,
						null,
						null,
						(map.get("dataStatus") != null) ? (int) map
								.get("dataStatus") : null,
						(map.get("showType") != null) ? (int) map
								.get("showType") : null,
						(map.get("fullColumnImgUrl") != null) ? map.get(
								"fullColumnImgUrl").toString() : null,
						(map.get("hasVideo") != null) ? (map.get("hasVideo")
								.toString().equals("true") ? (byte) 1
								: (byte) 0) : null,
						(map.get("isLive") != null) ? (map.get("isLive")
								.toString().equals("true") ? (byte) 1
								: (byte) 0) : null,
						(map.get("isLiveReplay") != null) ? (map
								.get("isLiveReplay").toString().equals("true") ? (byte) 1
								: (byte) 0)
								: null, topicUniqueId);
				/*
				 * @author liyang 追加作者
				 */
				List<Map> appAuthorList = (List<Map>) map.get("appAuthor");
				String newsId = map.get("newsId").toString();
				List list = new ArrayList<NewsAuthor>();
				Iterable<NewsAuthor> newsAuthorList = null;
				if (appAuthorList != null && appAuthorList.size() > 0) {
					for (Map temp : appAuthorList) {
						NewsAuthor author = new NewsAuthor();
						author.setAuthorName(temp.get("authorName").toString());
						author.setMediaName(temp.get("mediaName").toString());
						author.setPhotoUrl(temp.get("photoUrl").toString());
						author.setAuthorUniqueId(temp.get("authorUniqueId")
								.toString());
						String id = UUID.randomUUID().toString();
						author.setId(id);
						author.setNewsId(newsId);
						list.add(author);
					}
					newsAuthorList = (Iterable<NewsAuthor>) list;
				}
				if (newsAuthorList != null && list.size() > 0) {
					this.newsAuthorDao.deleteByNewsId(newsId);
					this.newsAuthorDao.save(newsAuthorList);
				}
				// 踩赞和分享，收藏等数据置为0
				NewsInfo news = this.newsInfoDao.findOne(newsId);
				if(news == null){
					newsInfo.setAppriseDownNum(0);
					newsInfo.setAppriseUpNum(0);
					newsInfo.setCommentNum(0);
					newsInfo.setShareNum(0);
					newsInfo.setAccessNum(0);
					newsInfo.setCollectNum(0);
				}else{
					newsInfo.setAppriseDownNum(news.getAppriseDownNum());
					newsInfo.setAppriseUpNum(news.getAppriseUpNum());
					newsInfo.setCommentNum(news.getCommentNum());
					newsInfo.setShareNum(news.getShareNum());
					newsInfo.setCollectNum(news.getCollectNum());
					newsInfo.setAccessNum(news.getAccessNum());
				}
				
				/*
				 * @author liyang
				 * 
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
				Byte hasVideo = null;
				String shortTitle = null;
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
				obj = map.get("shortTitle");
				if (obj != null) {
					shortTitle = obj.toString();
				}
				obj = map.get("hasVideo");
				if (obj != null) {
					hasVideo = Byte.parseByte(obj.toString());
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
				newsInfo.setShortTitle(shortTitle);
				newsInfo.setHasVideo(hasVideo);
				/*
				 * @author liyang
				 * 
				 * @createTime 2018年3月5日 String link Byte dataMode
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
				 */
				Integer videoDuration = null;
				obj = map.get("videoDuration");
				if (obj != null) {
					videoDuration = Integer.parseInt(obj.toString());
				}
				newsInfo.setVideoDuration(videoDuration);
				newsPictureDao.deleteByNewsID(newsInfo.getNewsId());
				int i = 0;
				boolean less = false;
				if (newsInfo.getNewsContent() != null) {
					String[] imgs = getImgs(newsInfo.getNewsContent());
					if (imgs != null) {
						for (; i < imgs.length; i++) {
							NewsPicture newsPicture = new NewsPicture(
									newsInfo.getNewsId(), newsInfo.getNewsId()
											+ "_" + i, imgs[i], i);
							String picPath = newsPicture.getImagePath();
							String url = picPath+RongLianConstant.PICTURE_URL_END;
							String result = HttpClientUtil.doGet(url);
							Map<String, Object> picMap = GetRequestJsonUtils.parseObject(result);
							Object withObj = picMap.get("ImageWidth");
							Object heightObj = picMap.get("ImageHeight");
							
							Map<String, Object> withMap = GetRequestJsonUtils.parseObject(withObj.toString());
							Map<String, Object> heightMap = GetRequestJsonUtils.parseObject(heightObj.toString());
							int width = Integer.parseInt(withMap.get("value").toString());
							int height = Integer.parseInt(heightMap.get("value").toString());
							newsPicture.setHeight(height);
							newsPicture.setWidth(width);
							newsPictureDao.save(newsPicture);
						}
						less = true;
					}
				}
				if (map.get("imageList") != null) {
					List<Map> imageList = (List<Map>) map.get("imageList");
					for (Map imageInfoMap : imageList) {
						NewsPicture newsPicture = new NewsPicture(
								newsInfo.getNewsId(),
								newsInfo.getNewsId() + "_" + i,
								imageInfoMap.get("picPath") != null ? imageInfoMap
										.get("picPath").toString() : null,
								imageInfoMap.get("picDesc") != null ? imageInfoMap
										.get("picDesc").toString() : null,
								imageInfoMap.get("picTitle") != null ? imageInfoMap
										.get("picTitle").toString() : null, i);
						//2018年4月20日，追加图片的宽、高
						String picPath = newsPicture.getImagePath();
						String url = picPath+RongLianConstant.PICTURE_URL_END;
						String result = HttpClientUtil.doGet(url);
						Map<String, Object> picMap = GetRequestJsonUtils.parseObject(result);
						Object withObj = picMap.get("ImageWidth");
						Object heightObj = picMap.get("ImageHeight");
						
						Map<String, Object> withMap = GetRequestJsonUtils.parseObject(withObj.toString());
						Map<String, Object> heightMap = GetRequestJsonUtils.parseObject(heightObj.toString());
						int width = Integer.parseInt(withMap.get("value").toString());
						int height = Integer.parseInt(heightMap.get("value").toString());
//						withMap.get("value").toString();
						newsPicture.setHeight(height);
						newsPicture.setWidth(width);
						i++;
						newsPictureDao.save(newsPicture);
					}
				}
				newsInfo.setImageList(i);
				
				this.jedisDao.del("newsContent"+newsId);
				//发消息通知job，持久化accessNum
				String num = this.jedisDao.get("accessNum"+newsId);
				//通知update访问统计数据
				if(StringUtils.isNotBlank(num)){
//					this.newsInfoDao.updateReadNumByAccessNum(newsId,Integer.parseInt(num));
					newsInfo.setAccessNum(Integer.parseInt(num));
				}
				this.newsInfoDao.save(newsInfo);
				this.jedisDao.del("accessNum"+newsId);
				this.jedisDao.remove("channelNews"+newsInfo.getChannelUniqueId()+"*");
				Byte isEdit = newsInfo.getIsEditRecom();
				Byte istop = newsInfo.getIsToTop();
				Byte isTopNews = newsInfo.getIsTopnewsTotop();
				Integer beTopic = newsInfo.getIsTopic();
					this.jedisDao.del("editrecommen");
					this.jedisDao.del("channelTop"+newsInfo.getChannelUniqueId());
					this.jedisDao.del("topnewshead");
					this.jedisDao.remove("topicNews"+"*");
					
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
			Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)")
					.matcher(img);
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
			// liyang 0326 追加代码逻辑
			cutStr = cutStr.replace(",https:", " ,https:");

			images = cutStr.split(" ,");
		}
		return images;
	}

	/**
	 * @author liyang
	 * @createTime 2017年12月27日
	 */
	@Override
	public RongLianResult getPhotoNewsByNewsId(String chanelUniqueId,
			Integer incNo) {
		if (incNo == null) {
			return RongLianResult.build(200, "the param incNo can not be null");
		}
		List<NewsInfo> list = this.newsInfoDao.selectNewsInfoNearUpByIncNo(
				incNo, chanelUniqueId);
		List<NewsInfo> list2 = this.newsInfoDao.selectNewsInfoNearDownByIncNo(
				incNo, chanelUniqueId);
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
				// result.put("publishTime",
				// RongLianUtils.changeDateTime(news.getPublishTime()));
				result.put("dataMode", news.getDataMode());
				result.put("chanelUniqueId", news.getChannelUniqueId());
				result.put("hasVideo", news.getHasVideo());
				result.put("publishTime",
						RongLianUtils.getUTCtime(news.getPublishTime()));
				result.put("newsSort", news.getNewsSort());
				result.put("appriseDown", news.getAppriseDownNum());
				result.put("appriseUp", news.getAppriseUpNum());

				List<NewsPicture> photos = this.newsPictureDao
						.selectNewsPictureByNewsId(news.getNewsId());
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
			if (resultList.size() == 4 || resultList.size() == 2
					|| resultList.size() == 1) {
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
		String resultStr = jedisDao.get("topnewshead");
		List<Map> resultList = new ArrayList<Map>();
		if(StringUtils.isNotBlank(resultStr)){
			jedisDao.expire("topnewshead", RongLianConstant.REDIS_NEWS_EXPIRE);
			resultList = JsonUtils.jsonToList(resultStr, Map.class);
			return RongLianResult.ok(resultList);
		}
		List<NewsInfo> list = this.newsInfoDao.selectTopnewsAhead();
		if (list == null || !(list.size() > 0)) {
			return RongLianResult.build(200, "result is null", null);
		}
		resultList = this.changeDataContent(list);
		jedisDao.set("topnewshead", JsonUtils.objectToJson(resultList));
		jedisDao.expire("topnewshead", RongLianConstant.REDIS_NEWS_EXPIRE);
		return RongLianResult.ok(resultList);
	}

	/*
	 * @param List<NewsInfo> list ----数据转换
	 */
	public List<Map> changeDataContent(List<NewsInfo> list) {
		List<Map> resultList = new ArrayList<Map>();
		for (NewsInfo news : list) {
			Map resultMap = new HashMap();
			resultMap.put("newsTitle", news.getShortTitle());
			resultMap.put("newsId", news.getNewsId());
			resultMap.put("newsTags", news.getNewsTags());
			resultMap.put("channelName", news.getChannelName());
			resultMap.put("channelUniqueId", news.getChannelUniqueId());
			resultMap.put("publishTime",RongLianUtils.getUTCtime(news.getPublishTime()));
			resultMap.put("newsSort", news.getNewsSort());
			resultMap.put("showType", news.getShowType());
			resultMap.put("fullColumnImgUrl", news.getFullColumnImgUrl());
			resultMap.put("hasVideo", news.getHasVideo());
			resultMap.put("isLive", news.getIsLive());
			resultMap.put("isLiveReplay", news.getIsLiveReplay());
			resultMap.put("appointCoverImage", news.getAppointCoverImage());
			resultMap.put("liveUrl", news.getLiveUrl());
			resultMap.put("liveReplayUrl", news.getLiveReplayUrl());
			resultMap.put("liveHostChatid", news.getLiveHostChatid());
			resultMap.put("liveUsChatid", news.getLiveUsChatid());
			resultMap.put("link", news.getLink());
			resultMap.put("dataMode", news.getDataMode());
			resultMap.put("videoDuration", news.getVideoDuration());
			Integer isTopic = news.getIsTopic();
			resultMap.put("isTopic", news.getIsTopic());
			String topicUniqueId = null;
			if (isTopic > 0) {
				topicUniqueId = news.getTopicUniqueId();
				resultMap.put("topicUniqueId", topicUniqueId);
			}
			//problem
			if (StringUtils.isNotBlank(topicUniqueId)) {
				NewsTopic topic = this.topicDao.getNewsTopicByTopicId(topicUniqueId);
				if (topic != null) {
					Map topicDetail = new HashMap();
					topicDetail.put("topicDesc", topic.getTopicDesc());
					topicDetail.put("bannerPhoto", topic.getBannerImage());
					topicDetail.put("coverPhoto", topic.getListImage());
					resultMap.put("topicDetail", topicDetail);
				}
			}
			// 视频相关字段
			Integer videoDuration = null;
			videoDuration = news.getVideoDuration();
			resultMap.put("videoDuration", videoDuration);
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
				resultList = this.findTopicNews(resultList, topicUniqueId,
						RongLianConstant.TOPIC_NEWS_NUMBER);
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
	public List<Map> findTopicNews(List<Map> resultList, String topicUniqueId,
			int n) {
		List<String> newsIdList = this.topicAndNewsDao
				.selectNewsInfoIdByTopic(topicUniqueId);
		if (newsIdList != null && newsIdList.size() <= n
				&& newsIdList.size() > 0) {
			List<NewsInfo> newsInfoList = this.newsInfoDao
					.selectByNewsID(newsIdList);
			List<Map> topicNewsList = new ArrayList<Map>();
			for (NewsInfo newsInfo : newsInfoList) {
				Map topicNews = new HashMap();
				topicNews.put("newsTitle", newsInfo.getNewsTitle());
				topicNews.put("newsId", newsInfo.getNewsId());
				topicNews.put("newsTags", newsInfo.getNewsTags());
				topicNews.put("channelName", newsInfo.getChannelName());
				topicNews.put("channelUniqueId", newsInfo.getChannelUniqueId());
				topicNews.put("publishTime",
						RongLianUtils.getUTCtime(newsInfo.getPublishTime()));

				topicNews.put("newsSort", newsInfo.getNewsSort());
				topicNews.put("showType", newsInfo.getShowType());
				topicNews.put("fullColumnImgUrl",
						newsInfo.getFullColumnImgUrl());
				topicNews.put("hasVideo", newsInfo.getHasVideo());
				topicNews.put("isLive", newsInfo.getIsLive());
				topicNews.put("isLiveReplay", newsInfo.getIsLiveReplay());
				topicNews.put("appointCoverImage ",
						newsInfo.getAppointCoverImage());
				topicNews.put("liveUrl", newsInfo.getLiveUrl());
				topicNews.put("liveReplayUrl", newsInfo.getLiveReplayUrl());
				topicNews.put("liveHostChatid", newsInfo.getLiveHostChatid());
				topicNews.put("liveUsChatid", newsInfo.getLiveUsChatid());
				topicNews.put("link", newsInfo.getLink());
				topicNews.put("dataMode", newsInfo.getDataMode());
				// 视频总长
				topicNews.put("videoDuration", newsInfo.getVideoDuration());
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

	@Override
	public RongLianResult getNewsInfoList(String[] newsIds) {
		// TODO Auto-generated method stub
		if(newsIds == null || newsIds.length < 1){
			return RongLianResult.build(200,"newsIds can no be null");
		}
		List<String> newsIdList = Arrays.asList(newsIds);
		List<NewsInfo> newsInfoList = null;
		newsInfoList = this.newsInfoDao.selectNewsIdList(newsIdList);
		if(newsInfoList == null){
			return RongLianResult.build(200,"the results  which  about the newsIds were null");
		}
		List feedList = new ArrayList<Map>();
		for(NewsInfo newsInfo:newsInfoList){
			Map result = new HashMap();
			result.put("feedID", newsInfo.getNewsId());
			result.put("viewCount", newsInfo.getAccessNum());
			result.put("likeCount", newsInfo.getAppriseUpNum());
			result.put("dislikeCount", newsInfo.getAppriseDownNum());
			result.put("saveCount", newsInfo.getCollectNum());
			result.put("shareCount", newsInfo.getShareNum());
			feedList.add(result);
		}
		Map map = new HashMap();
		map.put("feedList", feedList);
		return RongLianResult.ok(map);
	}

	/**
	 * 新增专题置顶新闻查询
	 * @createTime 2018年5月24
	 * @param topicId
	 * @return
	 */
	@Override
	public RongLianResult findTopicNewsToTop(String topicId) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(topicId)){
			return RongLianResult.build(200, "请求参数topicId不能为空");
		}
		List<Map> resultList = new ArrayList<Map>();
		String resultStr = jedisDao.get("topicNewsToTop"+topicId);
		if(StringUtils.isNotBlank(resultStr)){
			jedisDao.expire("topicNewsToTop"+topicId, RongLianConstant.REDIS_NEWS_EXPIRE);
			resultList = JsonUtils.jsonToList(resultStr, Map.class);
			return RongLianResult.ok(resultList);
		}
		List<NewsInfo> newsInfoList = null;
		newsInfoList = this.newsInfoDao.selectTopicNewsToTopBytopicId(topicId);
		resultList = this.changeDataContent(newsInfoList);
		jedisDao.set("topicNewsToTop"+topicId, JsonUtils.objectToJson(resultList));
		jedisDao.expire("topicNewsToTop"+topicId, RongLianConstant.REDIS_NEWS_EXPIRE);
		return RongLianResult.ok(resultList);
	}

	/***
	 * 新增   api/1.1/getTopicNews
	 * 
	 */
	@Override
	public PageCountResult findTopicNewsList2(String topicId, int pageNo,
			int pageSize, String newsId) {
		// TODO Auto-generated method stub
		if (topicId == null) {
			return PageCountResult.error(200, "topicId can not be null ",
					pageNo, pageSize);
		}
		int counter;
		List<Map> resultList = new ArrayList<Map>();
		String resultStr = jedisDao.get("topicNews2"+topicId+pageNo+pageSize+newsId);
		String counterStr = jedisDao.get("topicNews2"+topicId+"counter");
		
		if(StringUtils.isNotBlank(resultStr) && StringUtils.isNotBlank(counterStr)){
			counter = Integer.valueOf(counterStr);
			jedisDao.expire("topicNews2"+topicId+pageNo+pageSize+newsId, RongLianConstant.REDIS_NEWS_EXPIRE);
			jedisDao.expire("topicNews2"+topicId+"counter", RongLianConstant.REDIS_NEWS_EXPIRE);
			resultList = JsonUtils.jsonToList(resultStr, Map.class);
			return PageCountResult.build(0, "ok", counter, pageNo, pageSize,resultList);
		}
		int start = (pageNo - 1) * pageSize;
		int count = 0;
		List<NewsInfo> newsInfoList = null;
		if (newsId == null) {
			newsInfoList = this.newsInfoDao.selectTopicNewsByNewsInfoId2(topicId, start, pageSize);
		} else {
			NewsInfo news = this.newsInfoDao.findOne(newsId);
			if (news == null) {
				return PageCountResult.error(200, "newsId was not right",pageNo, pageSize);
			}
			Date dd = news.getPublishTime();
			newsInfoList = this.newsInfoDao.selectTopicNewsByNewsId2(topicId,start, pageSize, dd);
		}
		counter = this.newsInfoDao.selectTopicNewsByNewsInfoId2(topicId, 0,200).size();
		jedisDao.set("topicNews2"+topicId+"counter", counter+"");
		jedisDao.expire("topicNews2"+topicId+"counter", RongLianConstant.REDIS_NEWS_EXPIRE);
		
		count = newsInfoList.size();
		if (newsInfoList == null || count == 0) {
			return PageCountResult.error(200, "result is null", pageNo,pageSize);
		}
		resultList = this.changeDataContent(newsInfoList);
		jedisDao.set("topicNews2"+topicId+pageNo+pageSize+newsId, JsonUtils.objectToJson(resultList));
		jedisDao.expire("topicNews2"+topicId+pageNo+pageSize+newsId, RongLianConstant.REDIS_NEWS_EXPIRE);
		return PageCountResult.build(0, "ok", counter, pageNo, pageSize,resultList);
	}

}