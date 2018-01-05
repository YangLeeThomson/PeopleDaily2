/**
 * 
 */
package com.ronglian.service;

import java.util.List;

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
}
