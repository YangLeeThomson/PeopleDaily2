/**
 * 
 */
package com.ronglian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.NewsInfoDislikeDao;
import com.ronglian.entity.NewsInfoDislike;
import com.ronglian.service.ContentDislikeService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018Äê1ÔÂ10ÈÕ
 */
@Service
public class ContentDislikeServiceImpl implements ContentDislikeService {

	@Autowired
	private NewsInfoDislikeDao newsInfoDislikeDao;

	/* (non-Javadoc)
	 * @see com.ronglian.service.ContentDislikeService#addContentDislike(com.ronglian.entity.NewsInfoDislike)
	 */
	@Override
	public RongLianResult addContentDislike(NewsInfoDislike obj) {
		// TODO Auto-generated method stub
		if(obj != null){
			String id = 
		}
		return null;
	} 
	
}
