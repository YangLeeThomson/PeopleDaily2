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
import com.ronglian.utils.PageResult;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
public interface NewsInfoService {

	public RongLianResult inserNewsInfo(NewsInfo newsInfo);
	
	public PageResult findNewsList(int pageSize,int pageNo,String channelId); 
	
	public RongLianResult findTopnewsList(String channelId);
	
	public RongLianResult findEditorNewsList(String channelId);
	
	public PageResult findTopicNewsList(List<String> list,int pageSize,int pageNo);
	
	public RongLianResult getNewsInfoContent(String newsId);
	
	public RongLianResult addNewsInfo(String newsStr) throws JsonParseException, JsonMappingException, IOException, NumberFormatException, ParseException;

	public RongLianResult getPhotoNewsByNewsId(String newsID,Integer incNo);
}
