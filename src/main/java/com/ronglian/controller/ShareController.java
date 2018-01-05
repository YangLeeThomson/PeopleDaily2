/**
 * 
 */
package com.ronglian.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018��1��2��
 */
@RestController
@RequestMapping("/api")
public class ShareController {

	/**
	 * �û��������¼�������
	 * */
	@RequestMapping(value="/1.0/sharecount",method=RequestMethod.GET)
	public RongLianResult ShareCount(String newsId,String userId,String deviceId){
		Map data = new HashMap();
		data.put("count", 87);	
		return RongLianResult.ok(data);
	}
}
