/**
 * 
 */
package com.ronglian.service;

import java.util.Map;

import com.ronglian.entity.NewsInfoApprise;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 */
public interface AppriseService {

	public RongLianResult addNewsInfoApprise(NewsInfoApprise apprise);

	/**
	 * @param apprise
	 * @return
	 */
	public RongLianResult removeApprise(NewsInfoApprise apprise);

	/**
	 * @param requestMap
	 * @return
	 */
	public RongLianResult getAppriseList(String deviceId, String userId);
	

	public boolean isApprised(String deviceId, String newsId,Integer good);
}
