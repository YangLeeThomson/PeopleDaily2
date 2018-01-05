/**
 * 
 */
package com.ronglian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.TopicNewsDao;
import com.ronglian.entity.TopicAndNews;
import com.ronglian.service.TopicNewsService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
@Service
public class TopicNewsServiceImpl implements TopicNewsService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.TopicNewsService#addTopicNews(com.ronglian.entity.TopicAndNews)
	 */
	@Autowired
	private TopicNewsDao topicNewsDao;
	@Override
	public RongLianResult addTopicNews(List topicNewses) {
		// TODO Auto-generated method stub
		Iterable<TopicAndNews> entities = null; 
		entities = topicNewsDao.save(topicNewses);
		if(entities != null){
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(500, "saved error");
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.TopicNewsService#deleteTopicNews(java.util.List)
	 */
	@Override
	public RongLianResult deleteTopicNewsByNewsID(List list) {
		// TODO Auto-generated method stub
		try {
			this.topicNewsDao.deleteByNewsID(list);
			return RongLianResult.ok();
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "delete error");
		}

	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.TopicNewsService#deleteTopicNewsByByTopicUniqueID(java.util.List)
	 */
	@Override
	public RongLianResult deleteTopicNewsByByTopicUniqueID(List list) {
		// TODO Auto-generated method stub
		try {
			this.topicNewsDao.deleteByTopicUniqueID(list);
			return RongLianResult.ok();
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "delete error");
		}

	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.TopicNewsService#getNewsInfoId(java.lang.String)
	 */
	@Override
	public List<String> getNewsInfoId(String topicId) {
		// TODO Auto-generated method stub
		List<String> list = this.topicNewsDao.selectNewsInfoIdByTopic(topicId);
		return list;
	}

}
