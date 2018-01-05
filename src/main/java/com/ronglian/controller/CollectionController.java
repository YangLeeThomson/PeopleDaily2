/**
 * 
 */
package com.ronglian.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.service.CollectionService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
@RestController
@RequestMapping("/api")
public class CollectionController {
	
	@Autowired
	private CollectionService collectionService;

	@RequestMapping(value="/1.0/usercollection",method=RequestMethod.POST)
	public RongLianResult addCollection(String deviceId,String newsId,String userId){
		
		return RongLianResult.ok();
	}
	
	@RequestMapping(value="/1.0/collectionLists",method=RequestMethod.GET)
	public RongLianResult getCollection(String deviceId,String userId){
		Map resultMap = new HashMap();
		resultMap.put("collectionId", "472837594327432");
		resultMap.put("newsid", "89347543434");
		resultMap.put("newsTitle", "美国总统特朗普访华");
		resultMap.put("createTime", "2017-12-13 17:53:22");
		Map resultMap2 = new HashMap();
		resultMap2.put("collectionId", "56145623115656");
		resultMap2.put("newsid", "7879864564");
		resultMap2.put("newsTitle", "日本首相安倍晋三访问俄罗斯");
		resultMap2.put("createTime", "2017-12-13 17:53:36");
		List resultData = new LinkedList();
		resultData.add(resultMap2);
		resultData.add(resultMap);
		return RongLianResult.ok(resultData);
	}
	
	
}
