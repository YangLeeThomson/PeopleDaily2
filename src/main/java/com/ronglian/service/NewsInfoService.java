/**
 * 
 */
package com.ronglian.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ronglian.entity.NewsInfo;
import com.ronglian.utils.PageCountResult;
import com.ronglian.utils.PageResult;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��27��
 */
public interface NewsInfoService {

	public RongLianResult inserNewsInfo(NewsInfo newsInfo);
	
	public PageResult findNewsList(int pageSize,int pageNo,String channelUniqueId,String newsId); 
	
	public RongLianResult findTopnewsList(String channelUniqueId);
	
	public RongLianResult findEditorNewsList(String channelUniqueId);
	
	public PageCountResult findTopicNewsList(String topicId,int pageSize,int pageNo,String newsId);
	
	public RongLianResult getNewsInfoContent(String newsId) throws JsonParseException, JsonMappingException, IOException;
	
	public RongLianResult addNewsInfo(String newsStr) throws JsonParseException, JsonMappingException, IOException, NumberFormatException, ParseException;

	public RongLianResult getPhotoNewsByNewsId(String newsID,Integer incNo);
	
	public RongLianResult getTopnewsAhead();
	
	public RongLianResult getNewsInfoList(String[] newsIds);
}
