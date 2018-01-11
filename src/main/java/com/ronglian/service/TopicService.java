/**
 * 
 */
package com.ronglian.service;

import java.util.Map;

import com.ronglian.entity.NewsTopic;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
public interface TopicService {

	public RongLianResult addTopic(NewsTopic topic);
	
	public RongLianResult addTopicMap(Map requestMap);
}
