/**
 * 
 */
package com.ronglian.service;

import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018��1��2��
 */
public interface CollectionService {

	public RongLianResult insertUserCollection(String deviceId,String newsId,String userId);

	public RongLianResult getUserCollection(String userId,String deviceId);
}
