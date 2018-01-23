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

import com.ronglian.entity.Collection;
import com.ronglian.service.CollectionService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
@RestController
@RequestMapping("/api")
public class CollectionController {
	
	@Autowired
	private CollectionService collectionService;

	/**
	 * 用户内容收藏接口
	 * */
	@RequestMapping(value="/1.0/usercollection",method=RequestMethod.POST)
	public RongLianResult addCollection(@RequestBody RongLianRequest<Collection> collectionBody){
		Collection collection = null;
		if(collectionBody != null){
			collection = collectionBody.getObj();
		}
		try {
			return collectionService.insertUserCollection(collection);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "保存收藏新闻失败");
		}
	}
	
	@RequestMapping(value="/1.0/collectionList",method=RequestMethod.GET)
	public RongLianResult getCollection(String deviceId,String userId){
		try {
			return this.collectionService.getUserCollection(userId, deviceId);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "查询失败");
		}
		
	}
		
}
