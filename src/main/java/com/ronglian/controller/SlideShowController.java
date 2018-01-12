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
	public RongLianResult getChannelSlidePictureList(String ChannelId){

		Map map1 = new HashMap();
		map1.put("slideShowId", "743265987432");
		map1.put("imageUrl", "图片地址");
		map1.put("title", "特朗普与习近平照片");
		map1.put("desc", "特朗普与习近平故宫照");
		map1.put("newsId", "237647824365743");
		
		Map map = new HashMap();
		map.put("slideShowId", "7432659878998");
		map.put("imageUrl", "图片地址2");
		map.put("title", "习近平照片");
		map.put("desc", "特朗普照");
		map.put("newsId", "23764782489494");
		List data = (List) new ArrayList();
		data.add(map);
		data.add(map1);
		return RongLianResult.ok(data);
//		return this.slideShowService.getSlideShowByChannel(ChannelId);
	}
}
