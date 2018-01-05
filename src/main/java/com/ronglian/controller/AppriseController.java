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

import com.ronglian.service.AppriseService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018��1��2��
 */
@RestController
@RequestMapping("/api")
public class AppriseController {

	@Autowired
	private AppriseService appriseService;
	
	/**
	 * ���޽ӿ�
	 * */
	@RequestMapping(value="/1.0/connectapprises",method=RequestMethod.POST)
	public RongLianResult addApprise( String deviceId,String userId,String newsId){
		
		return RongLianResult.ok();
	}
	
	/**
	 * ȡ�����޽ӿ�
	 * */
	@RequestMapping(value="/1.0/cancleapprises",method=RequestMethod.POST)
	public RongLianResult cancleApprise(String deviceId,String userId,String newsId){
		return RongLianResult.ok();
	}

	/**
	 * ��ȡ�����б�  	
	 * */
	@RequestMapping(value="/1.0/appriselist",method=RequestMethod.GET)
	public RongLianResult getAppriseList(String deviceId,String userId){
		List resultList = new ArrayList<Map>();
		Map result = new HashMap();
		result.put("newsId", "v24387543");
		result.put("newsTitle", "sϰ��ƽ���ö�ŷ6��");
		result.put("createTime", "2017-08-12 14:23:13");
		
		Map result2 = new HashMap();
		result2.put("newsId", "456464156");
		result2.put("newsTitle", "�����ڰ˽��ӷ�������ķ˹��Ͽ");
		result2.put("createTime", "2017-08-12 14:23:13");
		resultList.add(result);
		resultList.add(result2);
		return RongLianResult.ok(resultList);
	}
}
