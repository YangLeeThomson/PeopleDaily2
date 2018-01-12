/**
 * 
 */
package com.ronglian.service;

import com.ronglian.entity.Collection;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018Äê1ÔÂ2ÈÕ
 */
public interface CollectionService {

	public RongLianResult insertUserCollection(Collection collect);

	public RongLianResult getUserCollection(String userId,String deviceId);
}
