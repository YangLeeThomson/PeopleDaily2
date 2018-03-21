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
 * @createTime 2017年12月27日
 */
@RestController
@RequestMapping("/api")
public class TopicController {
	@Autowired
	private TopicService topicService;
	@Autowired
	private TopicNewsService topicNewsService;

	/**
	 * 3.3.2 专题相关
	 * */
	@RequestMapping(value = "/1.0/setTopic", method = RequestMethod.POST)
	public RongLianResult addTopic(@RequestBody RongLianRequest<Map> requestMap) {
		return topicService.addTopicMap(requestMap.getData());
	}

	/**
	 * 3.3.3 专题内容映射
	 * */
	@RequestMapping(value = "/1.0/setNewsOfTopic", method = RequestMethod.POST)
	public RongLianResult addTopicAndNews(
			@RequestBody RongLianRequest<List<TopicNewsRelation>> requestList) {
		return this.topicNewsService.addTopicNews(requestList.getData());
	}

	/**
	 * 3.3.4 删除专题对应所有内容映射
	 * */
	@RequestMapping(value = "/1.0/delTopicOfAllNews", method = RequestMethod.POST)
	public RongLianResult removeTopicAndNewsByTopic(
			@RequestBody RongLianRequest<List<Map>> requestList) {
		List<String> list = new ArrayList<>();
		List<Map> mapList = requestList.getData();

		if (mapList == null || mapList.size() == 0) {
			return RongLianResult.build(200,
					"requestBody is null or it is size equals 0 ！");
		}
		for (Map map : mapList) {
			if (map.get("topicUniqueID") == null) {
				continue;
			}
			list.add((String) map.get("topicUniqueID"));
		}
		return this.topicNewsService.deleteTopicNewsByByTopicUniqueID(list);
	}

	/**
	 * 3.3.5 删除内容对应所有专题映射
	 * */
	@RequestMapping(value = "/1.0/delNewsOfAllTopic", method = RequestMethod.POST)
	public RongLianResult removeTopicAndNewsByNews(
			@RequestBody RongLianRequest<List<Map>> requestList) {
		List<String> list = new ArrayList<>();
		List<Map> mapList = requestList.getData();
		if (mapList == null || mapList.size() == 0) {
			return RongLianResult.build(200,
					"requestBody is null or it is size equals 0 ！");
		}
		for (Map map : mapList) {
			if (map.get("newsID") == null) {
				continue;
			}
			list.add((String) map.get("newsID"));
		}
		return this.topicNewsService.deleteTopicNewsByNewsID(list);
	}
}