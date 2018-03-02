/**
 * 
 */
package com.ronglian.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.CollectionDao;
import com.ronglian.entity.Collection;
import com.ronglian.service.CollectionService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018年1月2日
 */
@Service
public class CollectionServiceImpl implements CollectionService {

	/** 
	 * @parameter 
	 */
	@Autowired 
	private CollectionDao collectionDao;
	
	/**
	 * @author liyang
	 * @createTime 2018年1月2日
	 */
	@Override
	public RongLianResult insertUserCollection(Collection collection) {
		// TODO Auto-generated method stub
		String userId = collection.getUserId();
		String newsId = collection.getNewsId();
		String deviceId = collection.getDeviceId();
		Collection result = null;
		if( StringUtils.isNotBlank(newsId)
				&& StringUtils.isNotBlank(deviceId)	
				){
			//用户登录状态时
			if(StringUtils.isNotBlank(userId) ){
				result = this.collectionDao.selectCollectionByUserId(userId, newsId);
			}else{//没有登录时
				result = this.collectionDao.selectCollectionByDeviceId(deviceId, newsId);
			}
			//判断Result在数据库中是否存在
			if(result == null){
				//数据库不存在，则收藏该新闻
				collection.setCreateTime(new Date());
				collection.setCollectionId(UUID.randomUUID().toString());
				result = this.collectionDao.save(collection);
				return RongLianResult.ok(result);
			}else{
				//数据库存在该新闻
				return RongLianResult.build(200, "save failed，as you have saved this news");
			}
		}else{
			return RongLianResult.build(200, "request params must not be null");
		}
	}

	/* (non-Javadoc)
	 * @see com.ronglian.service.CollectionService#getUserCollection(java.lang.String, java.lang.String)
	 */
	@Override
	public RongLianResult getUserCollection(String userId, String deviceId) {
		// TODO Auto-generated method stub
		List<Collection> list = null;
		if(StringUtils.isNotBlank(deviceId)){
			if(StringUtils.isBlank(userId)){
				//sql语句剔除userId不为null的数据
				list = this.collectionDao.selectCollectionListByDeviceId(deviceId);
			}else{
				list = this.collectionDao.selectCollectionListByUserId(userId);
			}
				return RongLianResult.ok(list);
		}else{
			return RongLianResult.build(200, "deviceId can not be null");
		}
	}

}
