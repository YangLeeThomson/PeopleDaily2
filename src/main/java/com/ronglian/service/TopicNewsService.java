/**
 * 
 */
package com.ronglian.service;

import java.util.List;

import com.ronglian.entity.TopicAndNews;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
public interface TopicNewsService {

	public RongLianResult addTopicNews(List topicNewses);
	
	public RongLianResult deleteTopicNewsByNewsID(List list);
	
	public RongLianResult deleteTopicNewsByByTopicUniqueID(List list);

	/**
	 * @param topicId
	 * @return
	 */
	public List<String> getNewsInfoId(String topicId);
}
