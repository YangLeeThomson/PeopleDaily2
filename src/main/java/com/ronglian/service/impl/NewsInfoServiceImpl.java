/**
 * 
 */
package com.ronglian.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.ronglian.service.NewsInfoService;
import com.ronglian.utils.PageResult;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.ImageInfo;
import com.ronglian.dao.NewsPictureDao;

/**
 * @author liyang
 * @createTime 2017年12月27日
 */
@Service
public class NewsInfoServiceImpl implements NewsInfoService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#inserNewsInfo(com.ronglian.entity.NewsInfo)
	 */
	@Autowired
	private NewsInfoDao newsInfoDao;
	@Autowired
	private NewsPictureDao newsPictureDao;
	@Override
	public RongLianResult inserNewsInfo(NewsInfo newsInfo) {
		NewsInfo result = this.newsInfoDao.save(newsInfo);
		if(result != null){
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(500, "save error");
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findNewsList(int, int, java.lang.String)
	 */
	@Override
	public PageResult findNewsList(int pageSize, int pageNo, String channelId) {
		List<NewsInfo> list = this.newsInfoDao.selectNewsInfoByChannel(pageSize, pageNo, channelId);
		if(list !=null && list.size() > 0){
			return PageResult.build(0, "ok", pageNo, pageSize, list);
		}else{
			return PageResult.error(500, "error", pageNo, pageSize);
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findTopnewsList(java.lang.String)
	 */
	@Override
	public RongLianResult findTopnewsList(String channelId) {
		List<NewsInfo> list = this.newsInfoDao.selectTopnewsByChannel(channelId);
		if(list != null && list.size() > 0){
			return RongLianResult.ok(list);
		}else{
			return RongLianResult.build(500, "error");
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findEditorNewsList(java.lang.String)
	 */
	@Override
	public RongLianResult findEditorNewsList(String channelId) {
		List<NewsInfo> list = this.newsInfoDao.selectEditorNewsByChannel(channelId);
		if(list != null && list.size() > 0){
			return RongLianResult.ok(list);
		}else{
			return RongLianResult.build(500, "error");
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findTopicNewsList(java.util.List)
	 */
	@Override
	public PageResult findTopicNewsList(List<String> list,int pageSize,int pageNo) {
		List<NewsInfo> newsInfoList = this.newsInfoDao.selectTopicNewsByNewsInfoId(list,pageSize,pageNo);
		if(newsInfoList !=null && newsInfoList.size() > 0){
			return PageResult.build(0, "ok", pageNo, pageSize, newsInfoList);
		}else{
			return PageResult.error(500, "error", pageNo, pageSize);
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#getNewsInfoContent(java.lang.String)
	 */
	@Override
	public RongLianResult getNewsInfoContent(String newsId) {
		NewsInfo newsInfo = this.newsInfoDao.findOne(newsId);
		if(newsInfo != null){
			return RongLianResult.ok(newsInfo);
		}else{
			return RongLianResult.build(500, "error");
		}
	}
	@Override
	public RongLianResult addNewsInfo(String newsStr) throws JsonParseException, JsonMappingException, IOException, NumberFormatException, ParseException{
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		try{
		Map map = mapper.readValue(newsStr, Map.class);
		if(map != null){
			if(map.get("newsId")==null){
				return RongLianResult.build(500, "newsId不能为空");
			}else{
				NewsInfo newsInfo=new NewsInfo(map.get("newsId").toString(), (map.get("canComment")!=null)?map.get("canComment").toString():null, (map.get("channelId")!=null)?map.get("channelId").toString():null,
						(map.get("channelName")!=null)?map.get("channelName").toString():null, null, null,
						null, (map.get("createTime")!=null)?sdf.parse(map.get("createTime").toString()):null, (map.get("editExpire")!=null)?sdf.parse(map.get("editExpire").toString()):null,
						null, (map.get("isEditRecom")!=null)?(Boolean.getBoolean(map.get("isEditRecom").toString())?(byte)1:(byte)0):null, (map.get("isToTop")!=null)?(Boolean.getBoolean(map.get("isToTop").toString())?(byte)1:(byte)0):null , (map.get("isTopic")!=null)?Integer.valueOf(map.get("isTopic").toString()):null,
						null, null, null,
						(map.get("modifyTime")!=null)?sdf.parse(map.get("modifyTime").toString()):null, (map.get("newsAuthors")!=null)?map.get("newsAuthors").toString():null, (map.get("newsContent")!=null)?map.get("newsContent").toString():null,
								(map.get("newsOrganization")!=null)?map.get("newsOrganization").toString():null, (map.get("newsOriginal")!=null)?Integer.valueOf(map.get("newsOriginal").toString()):null, (map.get("newsSort")!=null)?Integer.valueOf(map.get("newsSort").toString()):null,
										(map.get("newsSource")!=null)?map.get("newsSource").toString():null, (map.get("newsSourceUrl")!=null)?map.get("newsSourceUrl").toString():null, null,
												(map.get("newsSummary")!=null)?map.get("newsSummary").toString():null, (map.get("newsTags")!=null)?map.get("newsTags").toString():null, (map.get("newsTitle")!=null)?map.get("newsTitle").toString():null,
														(map.get("publishTime")!=null)?sdf.parse(map.get("publishTime").toString()):null, (map.get("topExpire")!=null)?sdf.parse(map.get("topExpire").toString()):null, null,null, null,
																
																(map.get("dataStatus")!=null)?Integer.valueOf(map.get("dataStatus").toString()):null, (map.get("showType")!=null)?Integer.valueOf(map.get("showType").toString()):null,(map.get("fullColumnImgUrl")!=null)?map.get("fullColumnImgUrl").toString():null,
																		(map.get("hasVideo")!=null)?(Boolean.getBoolean(map.get("hasVideo").toString())?(byte)1:(byte)0):null, (map.get("isLive")!=null)?(Boolean.getBoolean(map.get("isLive").toString())?(byte)1:(byte)0):null,(map.get("isLiveReplay")!=null)?(Boolean.getBoolean(map.get("isLiveReplay").toString())?(byte)1:(byte)0):null);
				String imageJson=newsStr.substring(newsStr.lastIndexOf("["), newsStr.lastIndexOf("]")+1);
				List<ImageInfo> imageList = JSONArray.parseArray(imageJson, ImageInfo.class);
		        for(ImageInfo imageInfo:imageList){
		        	NewsPicture newsPicture=new NewsPicture(imageInfo.getPictureId(),imageInfo.getPicPath(),imageInfo.getPicDesc(),imageInfo.getPicTitle());
		        	newsPictureDao.save(newsPicture);
		        }
				this.newsInfoDao.save(newsInfo);
				return RongLianResult.ok(newsInfo);
			}
		}else{
			return RongLianResult.build(500, "未传参数或参数格式不对");
		}
		}catch(Exception e){
			return RongLianResult.build(500, e.getMessage());
		}
	}
}