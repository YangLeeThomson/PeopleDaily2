/**
 * 
 */
package com.ronglian.service;

import com.ronglian.entity.Collection;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018��1��2��
 */
public interface CollectionService {

	public RongLianResult insertUserCollection(Collection collect);

//	public RongLianResult getUserCollection(String userId,String deviceId);
	
	public RongLianResult getUserCollection(String userId,String deviceId,Integer pageNo,Integer pageSize);
	
	public RongLianResult delCollectionById(String collectionId);
}
