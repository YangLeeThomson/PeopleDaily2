/**
 * 
 */
package com.ronglian.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.service.NewsInfoService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��28��
 */
@RestController
@RequestMapping("/api")
public class PhotoNewsInfoController {


	@Autowired
	private NewsInfoService newsInfoService;
	
	/**
	 * Photo��Ŀ���ͼ������ӿ�(ͼƬ���ţ���ͼ�����)
	 * */
	@RequestMapping(value="/1.0/getNearbyNews",method=RequestMethod.GET)
	public Object getNearByNews(String newsID,Integer incNo){
		return this.newsInfoService.getPhotoNewsByNewsId(newsID, incNo);
		}
}
