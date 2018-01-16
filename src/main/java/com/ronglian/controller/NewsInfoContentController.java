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
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.NewsInfo;
import com.ronglian.service.NewsInfoService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��28��
 */
@RestController
@RequestMapping("/api")
public class NewsInfoContentController {

	@Autowired
	private NewsInfoService newsInfoService;
	
	@RequestMapping(value="/1.0/content",method=RequestMethod.GET)
	public RongLianResult getNewsInfoContent(String newsId){
	/*	Map data = new HashMap();
		data.put("incNo",32423 );
		data.put("newsContent"," ��������" );
		data.put("newsOrganization", " �����ձ�");
		data.put("newsAuthors", " ���ٰ�");
		data.put("publishTime", " 2017-12-23 19:22:23");
		data.put("newsTitle","ʮ�Ŵ���龫��" );
		data.put("appriseCount", 108);
		data.put("commentNum", 98);
		data.put("imageCount",1 );
		
		List imageList = new ArrayList();
		Map photo = new HashMap();
		photo.put("pictureId", "743265987432");
		photo.put("picPath", "ͼƬ��ַ");
		photo.put("picTitle", "��������ϰ��ƽ��Ƭ");
		photo.put("picDesc", "��������ϰ��ƽ�ʹ���");
		Map photo1 = new HashMap();
		photo1.put("pictureId", "74326432789");
		photo1.put("picPath", "ͼƬ��ַ");
		photo1.put("picTitle", "������ϰ��ƽ����");
		photo1.put("picDesc", "�����ա�ϰ��ƽ������");
		imageList.add(photo1);
		imageList.add(photo);
		data.put("imageList", imageList);*/
		
		return this.newsInfoService.getNewsInfoContent(newsId);
	}
}
