/**
 * 
 */
package com.ronglian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.AppStartPicture;
import com.ronglian.service.AppStartPictureService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;


@RestController 
@RequestMapping("/api")
public class AppStartPictureController {

	@Autowired
	private AppStartPictureService appStartPictureService;
	
	@RequestMapping("/1.0/setAppStartPicture")
	public RongLianResult addAppStartPicture(@RequestBody RongLianRequest<AppStartPicture> appStartPicture){
		return this.appStartPictureService.addAppStartPicture(appStartPicture.getData());
	}
	@RequestMapping(value="/1.0/appStartPictures",method=RequestMethod.GET)
	public RongLianResult getAppStartPicture(){
		return this.appStartPictureService.getStartPicture();
	}
}
