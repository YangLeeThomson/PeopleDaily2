/**
 * 
 */
package com.ronglian.service;

import java.util.Map;

import com.ronglian.entity.Channel;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
public interface ChannelService {

	public RongLianResult addChannel(Channel channel);
	
	public RongLianResult getChannelList();
	
	public RongLianResult addChannelMap(Map requestMap);
}
