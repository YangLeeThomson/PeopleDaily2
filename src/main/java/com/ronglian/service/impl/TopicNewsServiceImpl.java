
package com.ronglian.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.NewsInfoDao;
import com.ronglian.dao.TopicDao;
import com.ronglian.dao.TopicNewsDao;
import com.ronglian.entity.TopicAndNews;
import com.ronglian.entity.TopicNewsKey;
import com.ronglian.service.TopicNewsService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.TopicNewsRelation;

@Service
public class TopicNewsServiceImpl implements TopicNewsService {
	@Autowired
	private TopicNewsDao topicNewsDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private NewsInfoDao newsInfoDao;
	@Override
	public RongLianResult addTopicNews(List<TopicNewsRelation> topicNewses) {
		List<TopicAndNews> topicAndNewses=new LinkedList<TopicAndNews>();
		for(TopicNewsRelation topicNewsRelation:topicNewses){
			topicAndNewses.add(new TopicAndNews(new TopicNewsKey(topicNewsRelation)));
		}
		Iterable<TopicAndNews> entities = null; 
		entities = topicNewsDao.save(topicAndNewses);
		if(entities != null){
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(500, "saved error");
		}
	}
	@Override
	public RongLianResult deleteTopicNewsByNewsID(List<String> list) {
		if(list!=null){
			List<String> topicList=new LinkedList<String>();
			for(String newsId :list){
				topicList.addAll(this.topicNewsDao.selectTopicByNewsInfoId(newsId));
			}
			newsInfoDao.deleteByNewsID(list);
			if(topicList.size()>0){
				topicDao.deleteByTopicUniqueID(topicList);
			}
			this.topicNewsDao.deleteByNewsID(list);
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(500, "无数据");
		}
	}
	@Override
	public RongLianResult deleteTopicNewsByByTopicUniqueID(List<String> list) {
		if(list!=null){
			List<String> newsList=new LinkedList<String>();
			for(String topicId :list){
				newsList.addAll(this.topicNewsDao.selectNewsInfoIdByTopic(topicId));
			}
			topicDao.deleteByTopicUniqueID(list);
			if(newsList.size()>0){
				newsInfoDao.deleteByNewsID(newsList);
			}
			this.topicNewsDao.deleteByTopicUniqueID(list);
			return RongLianResult.ok();
	}else{
		return RongLianResult.build(500, "无数据");
	}
	}
	@Override
	public List<String> getNewsInfoId(String topicId) {
		List<String> list = this.topicNewsDao.selectNewsInfoIdByTopic(topicId);
		return list;
	}
}