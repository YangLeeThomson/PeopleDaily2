/**
 * 
 */
package com.ronglian.service;

import com.ronglian.entity.Channel;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��27��
 */
public interface ChannelService {

	public RongLianResult addChannel(Channel channel);
	
	public RongLianResult getChannelList();
}
