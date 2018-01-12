/**
 * 
 */
package com.ronglian.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.AppStartPicture;
import com.ronglian.service.AppStartPictureService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017年12月28日
 */
@RestController 
@RequestMapping("/api")
public class AppStartPictureController {

	@Autowired
	private AppStartPictureService appStartPictureService;
	
	@RequestMapping("/1.0/setAppStartPicture")
	public RongLianResult addAppStartPicture(@RequestBody AppStartPicture appStartPicture){
		return this.appStartPictureService.addAppStartPicture(appStartPicture);
	}
	@RequestMapping(value="/1.0/appStartPictures",method=RequestMethod.GET)
	public RongLianResult getAppStartPicture(){
//		return this.appStartPictureService.getStartPicture();
		
		Map map = new HashMap();
		map.put("id", "7432659874232");
		map.put("lastTime", 3);
		map.put("imageUrl", " 图片地址");
		map.put("to", "id|http:www");
		Map map2 = new HashMap();
		map2.put("id", "7xe43265987432");
		map2.put("lastTime", "3");
		map2.put("imageUrl", "图片地址2");
		map2.put("to", "id|http:www");
		
		List list = new LinkedList<>();
		list.add(map);
		list.add(map2);
		return RongLianResult.ok(list);
	}
}
