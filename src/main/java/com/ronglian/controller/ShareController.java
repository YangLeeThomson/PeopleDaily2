/**
 * 
 */
package com.ronglian.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.NewsShare;
import com.ronglian.service.NewsShareService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018年1月2日
 */
@RestController
@RequestMapping("/api")
public class ShareController {

	/**
	 * 用户分享文章计数接口
	 * */
	@Autowired
	private NewsShareService newsShareService;
	@RequestMapping(value="/1.0/sharecount",method=RequestMethod.POST)
	public RongLianResult ShareCount(@RequestBody NewsShare newsShare){
		try {
			return this.newsShareService.countNewsShare(newsShare);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return RongLianResult.build(500, "统计服务失败！");
		}
	}
}
