/**
 * 
 */
package com.ronglian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.TopicDao;
import com.ronglian.entity.NewsTopic;
import com.ronglian.service.TopicService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
@Service
public class TopicServiceImpl implements TopicService{

	/* (non-Javadoc)
	 * @see com.ronglian.service.TopicService#addTopic(com.ronglian.entity.NewsTopic)
	 */
	@Autowired
	private TopicDao topicDao;
	@Override
	public RongLianResult addTopic(NewsTopic topic) {
		// TODO Auto-generated method stub
		NewsTopic result = topicDao.save(topic);
		if(result != null){
			return RongLianResult.ok();	
		}else{
			return RongLianResult.build(500, "saved error1");
		}
	}

}
