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
 * @createTime 2017Äê12ÔÂ28ÈÕ
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
		return this.appStartPictureService.getStartPicture();
	}
}
