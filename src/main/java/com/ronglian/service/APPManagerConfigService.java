package com.ronglian.service;

import com.ronglian.entity.APPManagerConfig;
/**
 * @author liyang
 * @createTime 2017��12��22��
 */
public interface APPManagerConfigService {

	public String getTokenByAppId(String appId,String timeStamp,String sign);
	
	public boolean getTokenBytokenId(String tokenId);
}
