/**
 * 
 */
package com.ronglian.service;

import java.util.Map;

import com.ronglian.entity.Channel;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��27��
 */
public interface ChannelService {

	public RongLianResult addChannel(Channel channel);
	
	public RongLianResult getChannelList();
	
	public RongLianResult addChannelMap(Map requestMap);
}
