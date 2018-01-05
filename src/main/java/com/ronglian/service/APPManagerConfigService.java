package com.ronglian.service;

import com.ronglian.entity.APPManagerConfig;
/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ22ÈÕ
 */
public interface APPManagerConfigService {

	public String getTokenByAppId(String appId,String timeStamp,String sign);
	
	public boolean getTokenBytokenId(String tokenId);
}
