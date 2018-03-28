package com.ronglian.service;

import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.NewsPushRequestBody;

public interface NewsPushService {

	public RongLianResult addNewsPush(NewsPushRequestBody requestBody);
	
	public RongLianResult getNewsPush(String deviceId,String userId,Integer pagesize,Integer pageNo);
}
