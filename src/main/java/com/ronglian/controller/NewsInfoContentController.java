/**
 * 
 */
package com.ronglian.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public RongLianResult getNewsInfoContent(String newsId){
		return this.newsInfoService.getNewsInfoContent(newsId);
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
