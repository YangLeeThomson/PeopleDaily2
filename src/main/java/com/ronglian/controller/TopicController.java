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

import com.ronglian.entity.NewsSlideshow;
import com.ronglian.entity.NewsTopic;
import com.ronglian.entity.TopicAndNews;
import com.ronglian.service.TopicNewsService;
import com.ronglian.service.TopicService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;
import com.ronglian.utils.model.request.TopicNewsRelation;

/**
 * @author liyang
 * @createTime 2017ï¿½ï¿½12ï¿½ï¿½27ï¿½ï¿½
 */
@RestController
@RequestMapping("/api")
public class TopicController {
	@Autowired
	private TopicService topicService;
	@Autowired
	private TopicNewsService topicNewsService;
	
	/**
	 * Í¬²½×¨Ìâ
	 * */
	@RequestMapping(value="/1.0/setTopic",method=RequestMethod.POST)
	public RongLianResult addTopic(@RequestBody RongLianRequest<Map> requestMap){
		return topicService.addTopicMap(requestMap.getData());
	}
	/**
	 * ×¨ÌâÄÚÈÝÓ³Éä
	 * */
	@RequestMapping(value="/1.0/setNewsOfTopic",method=RequestMethod.POST)
	public RongLianResult addTopicAndNews( @RequestBody RongLianRequest<List<TopicNewsRelation>> requestList){
		return this.topicNewsService.addTopicNews(requestList.getData());
	}
	/**
	 * ï¿½ï¿½ï¿½ï¿½×¨ï¿½â£¬É¾ï¿½ï¿½ï¿½ï¿½Ó¦ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ó³ï¿½ï¿½
	 * */
	@RequestMapping(value="/1.0/delTopicOfAllNews",method=RequestMethod.POST)
	public RongLianResult removeTopicAndNewsByTopic(@RequestBody RongLianRequest<List<Map>> requestList){
		List<String> list = new ArrayList<>();
		List<Map> mapList = requestList.getData();
		if(mapList != null&&mapList.size()!=0){
			for(Map map:mapList){
				if(map.get("topicUniqueID")==null){
					continue;
				}
				list.add((String) map.get("topicUniqueID"));
			}
		}else{
			return RongLianResult.build(500, "È±ï¿½Ù²ï¿½ï¿½ï¿½");
		}
		return this.topicNewsService.deleteTopicNewsByByTopicUniqueID(list);
	}
	/**
	 * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ý£ï¿½É¾ï¿½ï¿½ï¿½ï¿½Ó¦ï¿½ï¿½ï¿½ï¿½×¨ï¿½ï¿½ Ó³ï¿½ï¿½
	 * */
	@RequestMapping(value="/1.0/delNewsOfAllTopic",method=RequestMethod.POST)
	public RongLianResult removeTopicAndNewsByNews(@RequestBody RongLianRequest<List<Map>> requestList){
		List<String> list = new ArrayList<>();
		List<Map> mapList = requestList.getData();
		if(mapList != null&&mapList.size()!=0){
			for(Map map:mapList){
				if(map.get("newsID")==null){
					continue;
				}
				list.add((String) map.get("newsID"));
			}
		}else{
			return RongLianResult.build(500, "È±ï¿½Ù²ï¿½ï¿½ï¿½");
		}
		return this.topicNewsService.deleteTopicNewsByNewsID(list);
	}
}