/**
 * 
 */
package com.ronglian.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ronglian.entity.NewsInfo;
import com.ronglian.service.NewsInfoService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017年12月28日
 */
@RestController
@RequestMapping("/api")
public class NewsInfoContentController {

	@Autowired
	private NewsInfoService newsInfoService;
	
	@RequestMapping(value="/1.0/content",method=RequestMethod.GET)
	@ResponseBody
	public RongLianResult getNewsInfoContent(String newsId){
		try {
			return this.newsInfoService.getNewsInfoContent(newsId);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return RongLianResult.build(200, e.getMessage());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return RongLianResult.build(200, e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return RongLianResult.build(200, e.getMessage());
		}
	}
	
	/**
	 * @article 文章统计分析接口（追加）
	 * @author liyang
	 * @createTime 2018年4月11日
	 */
	@RequestMapping(value="/1.0/account",method=RequestMethod.POST)
	public RongLianResult getNewsAccountInfo(@RequestBody RongLianRequest<String[]> request ){
		if(request == null || request.getData() == null){
			return RongLianResult.build(200, "requestBody can not be null");
		}
		String[] newsIds = request.getData();
		return this.newsInfoService.getNewsInfoList(newsIds);
	}
}
