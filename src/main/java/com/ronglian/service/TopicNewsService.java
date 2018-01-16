/**
 * 
 */
package com.ronglian.service;

import java.util.List;

import com.ronglian.entity.TopicAndNews;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.TopicNewsRelation;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
public interface TopicNewsService {

	public RongLianResult addTopicNews(List<TopicNewsRelation> topicNewses);
	
	public RongLianResult deleteTopicNewsByNewsID(List<String> list);
	
	public RongLianResult deleteTopicNewsByByTopicUniqueID(List<String> list);

	/**
	 * @param topicId
	 * @return
	 */
	public List<String> getNewsInfoId(String topicId);
}
