/**
 * 
 */
package com.ronglian.service.impl;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.NewsInfoDislikeDao;
import com.ronglian.entity.NewsInfoDislike;
import com.ronglian.service.ContentDislikeService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018��1��10��
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
		// ���ݲ�ȫУ��
		if(obj != null && StringUtils.isNotBlank(obj.getNewsId())
				&& StringUtils.isNotBlank(obj.getDeviceId())
				){
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			obj.setId(id);
			NewsInfoDislike newsInfoDislike = newsInfoDislikeDao.save(obj);
			return RongLianResult.ok(newsInfoDislike);
		}else{
			return RongLianResult.build(500, "�������������");
		}
		
	} 
	
}
