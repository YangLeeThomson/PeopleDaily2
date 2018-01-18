/**
 * 
 */
package com.ronglian.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.TopicDao;
import com.ronglian.entity.NewsTopic;
import com.ronglian.service.TopicService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017年12月27日
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
	@Override
	public RongLianResult addTopicMap(Map requestMap) {
		if(requestMap.get("topicId")==null||requestMap.get("title")==null||requestMap.get("desc")==null
				||requestMap.get("bannerPhoto")==null||requestMap.get("uniqueID")==null){
			return RongLianResult.build(500, "缺少数据");
		}
		NewsTopic originTopic=topicDao.findOne((int)requestMap.get("topicId"));
		Date date=new Date();
		NewsTopic newsTopic=new NewsTopic((int)requestMap.get("topicId"), requestMap.get("bannerPhoto").toString(), requestMap.get("channelUniqueId")!=null?requestMap.get("channelUniqueId").toString():null,
				originTopic!=null?originTopic.getCreateTime():date, requestMap.get("coverPhoto")!=null?requestMap.get("coverPhoto").toString():null, date,
						requestMap.get("desc").toString(), (requestMap.get("dataStatus")!=null)?Byte.valueOf(requestMap.get("dataStatus").toString()):null, requestMap.get("title").toString(),
						requestMap.get("uniqueID").toString());
		NewsTopic result = topicDao.save(newsTopic);
		if(result != null){
			return RongLianResult.ok();	
		}else{
			return RongLianResult.build(500, "保存失败");
		}
	}

}
