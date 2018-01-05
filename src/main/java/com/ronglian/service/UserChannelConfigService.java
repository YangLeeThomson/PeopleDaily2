/**
 * 
 */
package com.ronglian.service;

import java.util.List;

import com.ronglian.entity.UserChannelConfig;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ29ÈÕ
 */
public interface UserChannelConfigService {

	public RongLianResult addUserChannelConfig(List<UserChannelConfig> list);
	
	public RongLianResult getUserChannelConfig(String deviceId,String tokenId,String userId);
}
