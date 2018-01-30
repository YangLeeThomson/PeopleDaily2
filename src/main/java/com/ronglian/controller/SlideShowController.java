/**
 * 
 */
package com.ronglian.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.NewsSlideshow;
import com.ronglian.service.NewsSlideShowService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

@RestController
@RequestMapping("/api")
public class SlideShowController {

	@Autowired
	private NewsSlideShowService slideShowService;
	@RequestMapping(value="/1.0/setSlideShow",method=RequestMethod.POST)
	public RongLianResult addNewsSlideShow(@RequestBody RongLianRequest<NewsSlideshow> slideShow){
		return this.slideShowService.addSlideShow(slideShow.getData());
	}
	//��Ŀ�ֲ�ͼ�б�����ӿ�
	@RequestMapping(value="/1.0/channelSlidePictures",method=RequestMethod.GET)
	public RongLianResult getChannelSlidePictureList(String channelUniqueId){

		return this.slideShowService.getSlideShowByChannel(channelUniqueId);
	}
}
