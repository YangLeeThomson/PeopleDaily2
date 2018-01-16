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

import com.ronglian.entity.NewsSlideshow;
import com.ronglian.service.NewsSlideShowService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017年12月27日
 */
@RestController
@RequestMapping("/api")
public class SlideShowController {

	@Autowired
	private NewsSlideShowService slideShowService;
	@RequestMapping(value="/1.0/setSlideShow",method=RequestMethod.POST)
	public RongLianResult addNewsSlideShow(@RequestBody NewsSlideshow slideShow){
		return this.slideShowService.addSlideShow(slideShow);
	}
	//栏目轮播图列表输出接口
	@RequestMapping(value="/1.0/channelSlidePictures",method=RequestMethod.GET)
	public RongLianResult getChannelSlidePictureList(String channelId){

		return this.slideShowService.getSlideShowByChannel(channelId);
	}
}
