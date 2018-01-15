/**
 * 
 */
package com.ronglian.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ronglian.entity.NewsInfo;
import com.ronglian.service.NewsInfoService;
import com.ronglian.service.TopicNewsService;
import com.ronglian.utils.PageResult;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��27��
 */
@RestController
@RequestMapping("/api")
public class NewsInfoController {

	@Autowired
	private NewsInfoService newsInfoService;
	
	@Autowired
	private TopicNewsService topicNewsService;
	
	//imediaͬ��news���ݽӿ�
	@RequestMapping(value="/1.0/setNewsInfo",method=RequestMethod.POST)
	public RongLianResult addNewsInfo(HttpServletRequest request,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException, NumberFormatException, ParseException{
		StringBuffer str = new StringBuffer();
		BufferedReader reader = null;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			reader = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				str.append(line);
			}
		} catch (Exception ex) {
			return RongLianResult.build(500, ex.getMessage());
		}
		return newsInfoService.addNewsInfo(str.toString());
	}
	/**
	 * ��ȡchannel�����б�ӿ�
	 * */
	@RequestMapping(value="/1.0/getChannelNews",method=RequestMethod.GET)
	public PageResult getNewsList(Integer pageSize,Integer pageNo,String channelId){
		return this.newsInfoService.findNewsList(pageSize, pageNo, channelId);
	}
	
	/**
	 * channel�ö������б�Ľӿ�
	 * 
	 * */
	@RequestMapping(value="/1.0/channeltopnews",method=RequestMethod.GET)
	public RongLianResult getTopnewsList(String channelId){
		return this.newsInfoService.findTopnewsList(channelId);
	}
	/**
	 *TopNews��Ŀ  �༭�Ƽ� �б�����ӿ�
	 * */
	@RequestMapping(value="/1.0/editorrecommen",method=RequestMethod.GET)
	public RongLianResult getEditorList(String channelId){
		return this.newsInfoService.findEditorNewsList(channelId);
	
	}
	/**
	 * topicר�� �����б�
	 * */
	@RequestMapping(value="/1.0/getTopicNews",method=RequestMethod.GET)
	public PageResult getTopicNewsList(String topicId,int pageSize,int pageNo){
		//List<String> newsInfoIdList = this.topicNewsService.getNewsInfoId(topicId);
		return this.newsInfoService.findTopicNewsList(topicId,pageSize,pageNo);
	}
}
