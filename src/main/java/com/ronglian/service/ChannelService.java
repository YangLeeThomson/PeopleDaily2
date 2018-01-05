/**
 * 
 */
package com.ronglian.service;

import com.ronglian.entity.Channel;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
public interface ChannelService {

	public RongLianResult addChannel(Channel channel);
	
	public RongLianResult getChannelList();
}
