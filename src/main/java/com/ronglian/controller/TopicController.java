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
	 * 提供从iMedia后台同步专题的接口
	 * */
	@RequestMapping(value="/1.0/setTopic",method=RequestMethod.POST)
	public RongLianResult addTopic(@RequestBody Map requestMap){
		//获取请求参数
		int topicId = (int) requestMap.get("topicId");
		int channelId = (int) requestMap.get("channelId");
		int dataStatus = (int) requestMap.get("dataStatus");
		String title = (String) requestMap.get("title");
		String desc = (String) requestMap.get("desc");
		String bannerPhoto = (String) requestMap.get("bannerPhoto");
		String coverPhoto = (String) requestMap.get("coverPhoto");
		String uniqueID = (String) requestMap.get("uniqueID");
		//补全数据
		NewsTopic topic = new NewsTopic();
		Date createTime = new Date();
		Date modifyTime = new Date();
		topic.setTopicId(topicId);
		topic.setBannerImage(bannerPhoto);
		topic.setChannelId(channelId);
		topic.setCreateTime(createTime);
		topic.setListImage(coverPhoto);
		topic.setModifyTime(modifyTime);
//		topic.setNewsInfos(newsInfos);
		topic.setTopicDesc(desc);
		topic.setTopicStatus((byte) dataStatus);
		topic.setTopicTitle(title);
		
//		return topicService.addTopic(topic);
		return RongLianResult.ok();
	}
	/**
	 * 提供从iMedia专题对应内容关系的接口
	 * */
	@RequestMapping(value="/1.0/setNewsOfTopic",method=RequestMethod.POST)
	public RongLianResult addTopicAndNews(@RequestBody List<TopicAndNews> requestList){
//		return this.topicNewsService.addTopicNews(requestList);
		return RongLianResult.ok();
	}

	/**
	 * 根据专题，删除对应所有内容映射
	 * */
	@RequestMapping(value="/1.0/delTopicOfAllNews",method=RequestMethod.POST)
	public RongLianResult removeTopicAndNewsByTopic(@RequestBody List<Map> requestList){
		List<String> list = new ArrayList<>();
		if(requestList != null){
			for(Map map:requestList){
				list.add((String) map.get("topicUniqueID"));
			}
		}
//		return this.topicNewsService.deleteTopicNewsByByTopicUniqueID(list);
		return RongLianResult.ok();
	}
	/**
	 * 根据内容，删除对应所有专题 映射
	 * */
	@RequestMapping(value="/1.0/delNewsOfAllTopic",method=RequestMethod.POST)
	public RongLianResult removeTopicAndNewsByNews(@RequestBody List<Map> requestList){
		List<String> list = new ArrayList<>();
		if(requestList != null){
			for(Map map:requestList){
				list.add((String) map.get("newsID"));
			}
		}
//		return this.topicNewsService.deleteTopicNewsByNewsID(list);
		return RongLianResult.ok();
	}
}
