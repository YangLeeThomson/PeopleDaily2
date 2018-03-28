package com.ronglian.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.service.NewsInfoService;
import com.ronglian.service.NewsPushService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.NewsPushRequestBody;
import com.ronglian.utils.model.request.RongLianRequest;
/**
 * @author liyang
 * @createTime 2018-03-27
 * */
@RestController
@RequestMapping("/api")
public class NewsPushController {

	@Autowired
	private NewsPushService newsPushService;
	/*
	 * 推送列表同步接口
	 */
	@RequestMapping(value="/1.0/setpush",method=RequestMethod.POST)
	public RongLianResult addNewsPush(@RequestBody RongLianRequest<NewsPushRequestBody> request){
		if(request == null){
			return RongLianResult.build(200, "request can not be null");
		}
		NewsPushRequestBody requestBody = request.getData();
		return this.newsPushService.addNewsPush(requestBody);
	}
	/*
	 * 查看推送列表接口
	 */
	@RequestMapping(value="/1.0/pushlist",method=RequestMethod.GET)
	public RongLianResult getNewsPushList(String deviceId,String userId,
			@RequestParam(value="pageSize",defaultValue="10",required=false)Integer pagesize,
			@RequestParam(value="pageNo",defaultValue="1",required=false)Integer pageNo){
		return this.newsPushService.getNewsPush(deviceId, userId, pagesize, pageNo);
	}
}
