/**
 * 
 */
package com.ronglian.service;

import java.util.Map;

import com.ronglian.entity.NewsTopic;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��27��
 */
public interface TopicService {

	public RongLianResult addTopic(NewsTopic topic);
	
	public RongLianResult addTopicMap(Map requestMap);
}
