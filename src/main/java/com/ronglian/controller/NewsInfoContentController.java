/**
 * 
 */
package com.ronglian.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.NewsInfo;
import com.ronglian.service.NewsInfoService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ28ÈÕ
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
}
