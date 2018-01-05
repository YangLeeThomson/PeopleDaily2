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

import com.ronglian.entity.NewsInfo;
import com.ronglian.service.NewsInfoService;
import com.ronglian.service.TopicNewsService;
import com.ronglian.utils.PageResult;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��27��
 */
@RestController
@RequestMapping("/api")
public class NewsInfoController {

	@Autowired
	private NewsInfoService newsInfoService;
	
	@Autowired
	private TopicNewsService topicNewsService;
	
	//imediaͬ��news���ݽӿ�
	@RequestMapping(value="/1.0/setNewsInfo",method=RequestMethod.POST)
	public RongLianResult addNewsInfo(@RequestBody NewsInfo newsInfo){
		return RongLianResult.ok();
	}
	/**
	 * ��ȡchannel�����б�ӿ�
	 * */
	@RequestMapping(value="/1.0/getChannelNews",method=RequestMethod.GET)
	public PageResult getNewsList(int pageSize,int pageNo,String channelId){
//		return this.newsInfoService.findNewsList(pageSize, pageNo, channelId);
		Map resultMap = new HashMap();
		resultMap.put("newsTitle", " ������ͳ�����շû�");
		resultMap.put("newsId", "7346576926643483554");
		resultMap.put("newsTags", " �����շû�");
		resultMap.put("publishTime", " 2017-12-13 17:53:22");
		resultMap.put("newsSort", 3);
		resultMap.put("showType", 1);
		resultMap.put("fullColumnImgUrl", "http://1232.jpg");
		resultMap.put("hasVideo", true);
		resultMap.put("isLive", false);
		resultMap.put("isLiveReplay", false);
		resultMap.put("isTopic", 0);
		resultMap.put("topicDetail", null);
		resultMap.put("imageCount", 2);
		List photoList = new ArrayList();
		
		Map photoMap = new HashMap();
		photoMap.put("pictureId", "743265987432");
		photoMap.put("picPath", " ͼƬ��ַ");
		photoMap.put("picTitle", " ������ϰ��ƽ����");
		photoMap.put("picDesc",  " ��������ϰ��ƽ�ʹ���");
		photoList.add(photoMap);
		
		Map photoMap2 = new HashMap();
		photoMap2.put("pictureId", "74326432789");
		photoMap2.put("picPath", " ͼƬ��ַ");
		photoMap2.put("picTitle", " ������ϰ��ƽ����");
		photoMap2.put("picDesc",  " �����ա�ϰ��ƽ������");
		photoList.add(photoMap2);
		
		resultMap.put("photoList", photoList);
		List resultList = new ArrayList();
		resultList.add(resultMap);
		
		Map resultMap1 = new HashMap();
		resultMap1.put("newsTitle", " ÷����ϰ��ƽ������");
		resultMap1.put("newsId", "7346576926648412");
		resultMap1.put("newsTags", "ϰ��ƽ������");
		resultMap1.put("publishTime", " 2017-12-13 17:53:22");
		resultMap1.put("newsSort", 2);
		resultMap1.put("showType", 0);
		resultMap1.put("fullColumnImgUrl", "http://1232.jpg");
		resultMap1.put("hasVideo", false);
		resultMap1.put("isLive", false);
		resultMap1.put("isLiveReplay", false);
		resultMap1.put("isTopic", 0);
		resultMap1.put("topicDetail", null);
		resultMap1.put("imageCount", 0);
		resultMap1.put("photoList", null);
		resultList.add(resultMap1);
		
		Map resultMap2 = new HashMap();
		resultMap2.put("newsTitle", " ÷����ϰ��ƽ������");
		resultMap2.put("newsId", "7346576926648412");
		resultMap2.put("newsTags", "ϰ��ƽ������");
		resultMap2.put("publishTime", " 2017-12-13 17:53:22");
		resultMap2.put("newsSort", 2);
		resultMap2.put("showType", 0);
		resultMap2.put("fullColumnImgUrl", "http://1232.jpg");
		resultMap2.put("hasVideo", false);
		resultMap2.put("isLive", false);
		resultMap2.put("isLiveReplay", false);
		resultMap2.put("isTopic", 5);
		Map topicDetail = new HashMap();
		topicDetail.put("topicDesc", "�й���5�꾭�÷�չ������ͷ");
		topicDetail.put("bannerPhoto", "pic url");
		topicDetail.put("coverPhoto", "pic url");
		resultMap2.put("topicDetail", topicDetail);
		resultMap2.put("imageCount", 0);
		resultMap2.put("photoList", null);
		
		resultList.add(resultMap2);
		return PageResult.build(0, "ok", 1, 5, resultList);
	}
	
	/**
	 * channel�ö������б�Ľӿ�
	 * 
	 * */
	@RequestMapping(value="/1.0/channeltopnews",method=RequestMethod.GET)
	public RongLianResult getTopnewsList(String channelId){
		
//		return this.newsInfoService.findTopnewsList(channelId);
		Map resultMap = new HashMap();
		resultMap.put("newsTitle", " ������ͳ�����շû�");
		resultMap.put("newsId", "7346576926643483554");
		resultMap.put("fromChannel", 3);
		resultMap.put("newsTags", " �����շû�");
		resultMap.put("publishTime", " 2017-12-13 17:53:22");
		resultMap.put("newsSort", 3);
		resultMap.put("showType", 1);
		resultMap.put("fullColumnImgUrl", "http://1232.jpg");
		resultMap.put("hasVideo", true);
		resultMap.put("isLive", false);
		resultMap.put("isLiveReplay", false);
		resultMap.put("isTopic", 0);
		resultMap.put("topicDetail", null);
		resultMap.put("imageCount", 3);
		
		List photoList = new ArrayList();
		Map photoMap = new HashMap();
		photoMap.put("pictureId", "74326432789");
		photoMap.put("picPath", " ͼƬ��ַ");
		photoMap.put("picTitle", " ������ϰ��ƽ����");
		photoMap.put("picDesc", " �����ա�ϰ��ƽ������");
		photoList.add(photoMap);
		
		Map photoMap1 = new HashMap();
		photoMap1.put("pictureId", "484441311315");
		photoMap1.put("picPath", " ͼƬ��ַ");
		photoMap1.put("picTitle", " �˹�����");
		photoMap1.put("picDesc", " �˹��������ͳ");
		photoList.add(photoMap1);
		
		Map photoMap2 = new HashMap();
		photoMap2.put("pictureId", "484441311315");
		photoMap2.put("picPath", " ͼƬ��ַ");
		photoMap2.put("picTitle", " �վ��û�");
		photoMap2.put("picDesc", " ϰ��ƽ���վ�");
		photoList.add(photoMap2);
		
		resultMap.put("photoList", photoList);
		List data = new ArrayList();
		data.add(resultMap);
		
		Map resultMap2 = new HashMap();
		resultMap2.put("newsTitle", " ÷����ϰ��ƽ������");
		resultMap2.put("newsId", "7346576926643483554");
		resultMap2.put("fromChannel", 3);
		resultMap2.put("newsTags", " ϰ��ƽ������");
		resultMap2.put("publishTime", " 2017-12-13 17:53:22");
		resultMap2.put("newsSort", 2);
		resultMap2.put("showType", 0);
		resultMap2.put("fullColumnImgUrl", "");
		resultMap2.put("hasVideo", false);
		resultMap2.put("isLive", false);
		resultMap2.put("isLiveReplay", false);
		resultMap2.put("isTopic", 0);
		resultMap2.put("topicDetail", null);
		resultMap2.put("imageCount", 0);
		resultMap2.put("photoList", null);
		data.add(resultMap2);
		Map resultMap3 = new HashMap();
		resultMap3.put("newsTitle", " ÷����ϰ��ƽ������");
		resultMap3.put("newsId", "7346576926643483554");
		resultMap3.put("fromChannel", 3);
		resultMap3.put("newsTags", " ϰ��ƽ������");
		resultMap3.put("publishTime", " 2017-12-13 17:53:22");
		resultMap3.put("newsSort", 2);
		resultMap3.put("showType", 0);
		resultMap3.put("fullColumnImgUrl", "");
		resultMap3.put("hasVideo", false);
		resultMap3.put("isLive", false);
		resultMap3.put("isLiveReplay", false);
		resultMap3.put("isTopic", 5);
		Map topicDetail = new HashMap();
		topicDetail.put("topicDesc", "�й���5�꾭�÷�չ������ͷ");
		topicDetail.put("bannerPhoto", "pic url");
		topicDetail.put("coverPhoto", "pic url");
		
		resultMap3.put("topicDetail", topicDetail);
		
		resultMap3.put("imageCount", 0);
		resultMap3.put("photoList", null);
		
		data.add(resultMap3);
		
		return RongLianResult.ok(data);
	}
	/**
	 *TopNews��Ŀ  �༭�Ƽ� �б�����ӿ�
	 * */
	@RequestMapping(value="/1.0/editorrecommen",method=RequestMethod.GET)
	public RongLianResult getEditorList(String channelId){
//		return this.newsInfoService.findEditorNewsList(channelId);
		Map resultMap = new HashMap();
		resultMap.put("newsTitle", "������ͳ�����շû�");
		resultMap.put("newsId", "7346576926643483554");
		resultMap.put("newsTags", "�����շû�");
		resultMap.put("publishTime", "2017-12-13 17:53:22");
		resultMap.put("newsSort", 3);
		resultMap.put("showType", 1);
		resultMap.put("fullColumnImgUrl", "http://1232.jpg");
		resultMap.put("hasVideo", true);
		resultMap.put("isLive", false);
		resultMap.put("isLiveReplay", false);
		resultMap.put("isTopic", 0);
		resultMap.put("topicDetail", null);
		resultMap.put("imageCount", 3);
		List photoList = new ArrayList();
		Map photo = new HashMap();
		photo.put("pictureId","743265987432" );
		photo.put("picPath", " ͼƬ��ַ");
		photo.put("picDesc", " ��������ϰ��ƽ�ʹ���");
		Map photo1 = new HashMap();
		photo1.put("pictureId", "74326432789");
		photo1.put("picPath", " ͼƬ��ַ");
		photo1.put("picTitle", " ������ϰ��ƽ����");
		photo1.put("picDesc", " �����ա�ϰ��ƽ������");
		photoList.add(photo1);
		photoList.add(photo);
		resultMap.put("photoList", photoList);
		
		Map resultMap1 = new HashMap();
		resultMap1.put("newsTitle", "÷����ϰ��ƽ������");
		resultMap1.put("newsId", "7346576926643483554");
		resultMap1.put("newsTags", "ϰ��ƽ������");
		resultMap1.put("publishTime", "2017-12-1317: 53: 22");
		resultMap1.put("newsSort", 2);
		resultMap1.put("showType", 0);
		resultMap1.put("fullColumnImgUrl", "");
		resultMap1.put("hasVideo", false);
		resultMap1.put("isLive", false);
		resultMap1.put("isLiveReplay", false);
		resultMap1.put("isTopic", 0);
		resultMap1.put("topicDetail", null);
		resultMap1.put("imageCount",0 );
		resultMap1.put("photoList",null );
		Map resultMap2 = new HashMap();
		
		resultMap2.put("newsTitle","÷����ϰ��ƽ������" );
		resultMap2.put("newsId","7346576926643483554" );
		resultMap2.put("newsTags","ϰ��ƽ������" );
		resultMap2.put("publishTime", "2017-12-1317: 53: 22");
		resultMap2.put("newsSort", 2);
		resultMap2.put("showType", 0);
		resultMap2.put("fullColumnImgUrl", "");
		resultMap2.put("hasVideo", false);
		resultMap2.put("isLive",false );
		resultMap2.put("isLiveReplay", false);
		resultMap2.put("isTopic", 5);
		
		Map topicDetail = new HashMap();
		topicDetail.put("topicDesc", "�й���5�꾭�÷�չ������ͷ");
		topicDetail.put("topicDesc", "pic url");
		topicDetail.put("topicDesc", "pic url");
		
		resultMap2.put("topicDetail", topicDetail);
		resultMap2.put("imageCount", 0);
		resultMap2.put("photoList", null);
		
		List data = new ArrayList();
		data.add(resultMap);
		data.add(resultMap1);
		data.add(resultMap2);
		return RongLianResult.ok(data);
	}
	/**
	 * topicר�� �����б�
	 * */
	@RequestMapping(value="/1.0/getTopicNews",method=RequestMethod.GET)
	public PageResult getTopicNewsList(String topicId,int pageSize,int pageNo){
//		List<String> newsInfoIdList = this.topicNewsService.getNewsInfoId(topicId);
//		return this.newsInfoService.findTopicNewsList(newsInfoIdList,pageSize,pageNo);
		Map map = new HashMap();
		map.put("newsTitle", " ������ͳ�����շû�");
		map.put("newsId", "7346576926643483554");
		map.put("newsTags", "�����շû�");
		map.put("publishTime", " 2017-12-13 17:53:22");
		map.put("newsSort", "3");
		map.put("showType", "1");
		map.put("fullColumnImgUrl", "http://1232.jpg");
		map.put("hasVideo", true);
		map.put("isLive",false);
		map.put("isLiveReplay", false);
		map.put("isTopic", 0);
		map.put("topicDetail", null);
		map.put("imageCount", 3);
		List photoList = new ArrayList();
		Map photo = new HashMap();
		photo.put("pictureId", "743265987432");
		photo.put("picPath", "ͼƬ��ַ");
		photo.put("picTitle", "������ϰ��ƽ����");
		photo.put("picDesc", "��������ϰ��ƽ�ʹ���");
		Map photo2 = new HashMap();
		photo2.put("pictureId", "74326432789");
		photo2.put("picPath", "ͼƬ��ַ");
		photo2.put("picTitle", "������ϰ��ƽ����");
		photo2.put("picDesc", "�����ա�ϰ��ƽ������");
		photoList.add(photo2);
		photoList.add(photo);
		map.put("photoList", photoList);
		Map map1 = new HashMap();
		map1.put("newsTitle", "÷����ϰ��ƽ������");
		map1.put("newsId", "7346576926643483554");
		map1.put("newsTags", "ϰ��ƽ������");
		map1.put("publishTime", "2017-12-1317: 53: 22");
		map1.put("newsSort", 2);
		map1.put("showType", 0);
		map1.put("fullColumnImgUrl", "");
		map1.put("hasVideo", false);
		map1.put("isLive", false);
		map1.put("isLiveReplay", false);
		map1.put("isTopic", 0);
		map1.put("topicDetail",null);
		map1.put("imageCount", 0);
		map1.put("photoList", null);
		List data = new ArrayList();
		data.add(map);
		data.add(map1);
		return PageResult.build(0, "ok", 1, 5, data);
	}
}
