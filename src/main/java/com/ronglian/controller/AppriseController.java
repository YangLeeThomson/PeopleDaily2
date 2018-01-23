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

import com.ronglian.entity.NewsInfoApprise;
import com.ronglian.service.AppriseService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

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
	 * 
	 * good:1,����
	 * good��-1���²�
	 * */
	@RequestMapping(value="/1.0/connectapprises",method=RequestMethod.POST)
	public RongLianResult addApprise(@RequestBody RongLianRequest<NewsInfoApprise> appriseBody){
		NewsInfoApprise apprise = null;
		if(appriseBody != null){
			apprise = appriseBody.getObj();
		}
		try {
			return this.appriseService.addNewsInfoApprise(apprise);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "����ʧ�ܣ�");
		}
	}
	
	/**
	 * ȡ�����޽ӿ�
	 * */
	@RequestMapping(value="/1.0/cancleapprises",method=RequestMethod.POST)
	public RongLianResult cancleApprise(@RequestBody RongLianRequest<NewsInfoApprise> appriseBody){
		NewsInfoApprise apprise = null;
		if(appriseBody != null){
			apprise = appriseBody.getObj();
		}
		try {
			return this.appriseService.removeApprise(apprise);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "ȡ������");
		}
	}

	/**
	 * ��ȡ�����б�  	
	 * */
	@RequestMapping(value="/1.0/appriselist",method=RequestMethod.GET)
	public RongLianResult getAppriseList(String deviceId, String userId){
		try {
			return this.appriseService.getAppriseList(deviceId,userId);
		} catch (Exception e) {
			// TODO: handle exception
			return RongLianResult.build(500, "��ȡ�����б�ʧ��");
		}
	}
}
