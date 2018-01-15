/**
 * 
 */
package com.ronglian.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.NewsTopic;
import com.ronglian.entity.TopicAndNews;
import com.ronglian.service.TopicNewsService;
import com.ronglian.service.TopicService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.TopicNewsRelation;

/**
 * @author liyang
 * @createTime 2017��12��27��
 */
@RestController
@RequestMapping("/api")
public class TopicController {
	@Autowired
	private TopicService topicService;
	@Autowired
	private TopicNewsService topicNewsService;
	
	/**
	 * �ṩ��iMedia��̨ͬ��ר��Ľӿ�
	 * */
	@RequestMapping(value="/1.0/setTopic",method=RequestMethod.POST)
	public RongLianResult addTopic(@RequestBody Map requestMap){
		return topicService.addTopicMap(requestMap);
	}
	/**
	 * �ṩ��iMediaר���Ӧ���ݹ�ϵ�Ľӿ�
	 * */
	@RequestMapping(value="/1.0/setNewsOfTopic",method=RequestMethod.POST)
	public RongLianResult addTopicAndNews(@RequestBody List<TopicNewsRelation> requestList){
		return this.topicNewsService.addTopicNews(requestList);
	}

	/**
	 * ����ר�⣬ɾ����Ӧ��������ӳ��
	 * */
	@RequestMapping(value="/1.0/delTopicOfAllNews",method=RequestMethod.POST)
	public RongLianResult removeTopicAndNewsByTopic(@RequestBody List<Map> requestList){
		List<String> list = new ArrayList<>();
		if(requestList != null){
			for(Map map:requestList){
				list.add((String) map.get("topicUniqueID"));
			}
		}
		return this.topicNewsService.deleteTopicNewsByByTopicUniqueID(list);
	}
	/**
	 * �������ݣ�ɾ����Ӧ����ר�� ӳ��
	 * */
	@RequestMapping(value="/1.0/delNewsOfAllTopic",method=RequestMethod.POST)
	public RongLianResult removeTopicAndNewsByNews(@RequestBody List<Map> requestList){
		List<String> list = new ArrayList<>();
		if(requestList != null){
			for(Map map:requestList){
				list.add((String) map.get("newsID"));
			}
		}
		return this.topicNewsService.deleteTopicNewsByNewsID(list);
	}
}