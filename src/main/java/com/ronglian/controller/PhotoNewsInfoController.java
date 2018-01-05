/**
 * 
 */
package com.ronglian.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��28��
 */
@RestController
@RequestMapping("/api")
public class PhotoNewsInfoController {

	@RequestMapping(value="/1.0/getNearbyNews",method=RequestMethod.GET)
	public Object getNearByNews(String newsID,int incNo){
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("newsTitle", "������ͳ�����շû�");
			resultMap.put("newsId", "7346576926643483554"); 
			resultMap.put("newsTags", "�����շû�");
			resultMap.put("publishTime", "2017-12-13 17: 53: 22");
			resultMap.put("newsSort", 3);
			resultMap.put("pictureId", "743265987432");
			resultMap.put("picTitle", "ͼƬ����");
			resultMap.put("picPath", "ͼƬ��ַ");
			resultMap.put("picDesc", "��������ϰ��ƽ�ʹ���");
			
			Map<String,Object> resultMap2 = new HashMap<String,Object>();
			resultMap2.put("newsTitle", "�����շû�");
			resultMap2.put("newsId", "7346576926643483554");
			resultMap2.put("newsTags", "�����շû�");
			resultMap2.put("publishTime", "2017-12-12 16: 50: 40");
			resultMap2.put("newsSort", 3);
			resultMap2.put("pictureId", "784416114547");
			resultMap2.put("picTitle", "ͼƬ����");
			resultMap2.put("picPath", "ͼƬ��ַ");
			resultMap2.put("picDesc", "��������ϰ��ƽ�Ͳ�");
			
			List list = new ArrayList<>();
			list.add(resultMap2);
			list.add(resultMap);
		return RongLianResult.ok(list);
		}
}
