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
 * @createTime 2017年12月27日
 */
@RestController
@RequestMapping("/api")
public class NewsInfoController {

	@Autowired
	private NewsInfoService newsInfoService;
	
	@Autowired
	private TopicNewsService topicNewsService;
	
	//imedia同步news内容接口
	@RequestMapping(value="/1.0/setNewsInfo",method=RequestMethod.POST)
	public RongLianResult addNewsInfo(@RequestBody NewsInfo newsInfo){
		return RongLianResult.ok();
	}
	/**
	 * 获取channel新闻列表接口
	 * */
	@RequestMapping(value="/1.0/getChannelNews",method=RequestMethod.GET)
	public PageResult getNewsList(int pageSize,int pageNo,String channelId){
//		return this.newsInfoService.findNewsList(pageSize, pageNo, channelId);
		Map resultMap = new HashMap();
		resultMap.put("newsTitle", " 美国总统特朗普访华");
		resultMap.put("newsId", "7346576926643483554");
		resultMap.put("newsTags", " 特朗普访华");
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
		photoMap.put("picPath", " 图片地址");
		photoMap.put("picTitle", " 特朗普习近平我收");
		photoMap.put("picDesc",  " 特朗普与习近平故宫照");
		photoList.add(photoMap);
		
		Map photoMap2 = new HashMap();
		photoMap2.put("pictureId", "74326432789");
		photoMap2.put("picPath", " 图片地址");
		photoMap2.put("picTitle", " 特朗普习近平我收");
		photoMap2.put("picDesc",  " 特朗普、习近平北京游");
		photoList.add(photoMap2);
		
		resultMap.put("photoList", photoList);
		List resultList = new ArrayList();
		resultList.add(resultMap);
		
		Map resultMap1 = new HashMap();
		resultMap1.put("newsTitle", " 梅西与习近平增球衣");
		resultMap1.put("newsId", "7346576926648412");
		resultMap1.put("newsTags", "习近平与曼联");
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
		resultMap2.put("newsTitle", " 梅西与习近平增球衣");
		resultMap2.put("newsId", "7346576926648412");
		resultMap2.put("newsTags", "习近平与曼联");
		resultMap2.put("publishTime", " 2017-12-13 17:53:22");
		resultMap2.put("newsSort", 2);
		resultMap2.put("showType", 0);
		resultMap2.put("fullColumnImgUrl", "http://1232.jpg");
		resultMap2.put("hasVideo", false);
		resultMap2.put("isLive", false);
		resultMap2.put("isLiveReplay", false);
		resultMap2.put("isTopic", 5);
		Map topicDetail = new HashMap();
		topicDetail.put("topicDesc", "中国近5年经济发展良好势头");
		topicDetail.put("bannerPhoto", "pic url");
		topicDetail.put("coverPhoto", "pic url");
		resultMap2.put("topicDetail", topicDetail);
		resultMap2.put("imageCount", 0);
		resultMap2.put("photoList", null);
		
		resultList.add(resultMap2);
		return PageResult.build(0, "ok", 1, 5, resultList);
	}
	
	/**
	 * channel置顶新闻列表的接口
	 * 
	 * */
	@RequestMapping(value="/1.0/channeltopnews",method=RequestMethod.GET)
	public RongLianResult getTopnewsList(String channelId){
		
//		return this.newsInfoService.findTopnewsList(channelId);
		Map resultMap = new HashMap();
		resultMap.put("newsTitle", " 美国总统特朗普访华");
		resultMap.put("newsId", "7346576926643483554");
		resultMap.put("fromChannel", 3);
		resultMap.put("newsTags", " 特朗普访华");
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
		photoMap.put("picPath", " 图片地址");
		photoMap.put("picTitle", " 特朗普习近平我收");
		photoMap.put("picDesc", " 特朗普、习近平北京游");
		photoList.add(photoMap);
		
		Map photoMap1 = new HashMap();
		photoMap1.put("pictureId", "484441311315");
		photoMap1.put("picPath", " 图片地址");
		photoMap1.put("picTitle", " 邓公访美");
		photoMap1.put("picDesc", " 邓公与里根总统");
		photoList.add(photoMap1);
		
		Map photoMap2 = new HashMap();
		photoMap2.put("pictureId", "484441311315");
		photoMap2.put("picPath", " 图片地址");
		photoMap2.put("picTitle", " 普京访华");
		photoMap2.put("picDesc", " 习近平与普京");
		photoList.add(photoMap2);
		
		resultMap.put("photoList", photoList);
		List data = new ArrayList();
		data.add(resultMap);
		
		Map resultMap2 = new HashMap();
		resultMap2.put("newsTitle", " 梅西与习近平增球衣");
		resultMap2.put("newsId", "7346576926643483554");
		resultMap2.put("fromChannel", 3);
		resultMap2.put("newsTags", " 习近平与曼联");
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
		resultMap3.put("newsTitle", " 梅西与习近平增球衣");
		resultMap3.put("newsId", "7346576926643483554");
		resultMap3.put("fromChannel", 3);
		resultMap3.put("newsTags", " 习近平与曼联");
		resultMap3.put("publishTime", " 2017-12-13 17:53:22");
		resultMap3.put("newsSort", 2);
		resultMap3.put("showType", 0);
		resultMap3.put("fullColumnImgUrl", "");
		resultMap3.put("hasVideo", false);
		resultMap3.put("isLive", false);
		resultMap3.put("isLiveReplay", false);
		resultMap3.put("isTopic", 5);
		Map topicDetail = new HashMap();
		topicDetail.put("topicDesc", "中国近5年经济发展良好势头");
		topicDetail.put("bannerPhoto", "pic url");
		topicDetail.put("coverPhoto", "pic url");
		
		resultMap3.put("topicDetail", topicDetail);
		
		resultMap3.put("imageCount", 0);
		resultMap3.put("photoList", null);
		
		data.add(resultMap3);
		
		return RongLianResult.ok(data);
	}
	/**
	 *TopNews栏目  编辑推荐 列表输出接口
	 * */
	@RequestMapping(value="/1.0/editorrecommen",method=RequestMethod.GET)
	public RongLianResult getEditorList(String channelId){
//		return this.newsInfoService.findEditorNewsList(channelId);
		Map resultMap = new HashMap();
		resultMap.put("newsTitle", "美国总统特朗普访华");
		resultMap.put("newsId", "7346576926643483554");
		resultMap.put("newsTags", "特朗普访华");
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
		photo.put("picPath", " 图片地址");
		photo.put("picDesc", " 特朗普与习近平故宫照");
		Map photo1 = new HashMap();
		photo1.put("pictureId", "74326432789");
		photo1.put("picPath", " 图片地址");
		photo1.put("picTitle", " 特朗普习近平我收");
		photo1.put("picDesc", " 特朗普、习近平北京游");
		photoList.add(photo1);
		photoList.add(photo);
		resultMap.put("photoList", photoList);
		
		Map resultMap1 = new HashMap();
		resultMap1.put("newsTitle", "梅西与习近平增球衣");
		resultMap1.put("newsId", "7346576926643483554");
		resultMap1.put("newsTags", "习近平与曼联");
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
		
		resultMap2.put("newsTitle","梅西与习近平增球衣" );
		resultMap2.put("newsId","7346576926643483554" );
		resultMap2.put("newsTags","习近平与曼联" );
		resultMap2.put("publishTime", "2017-12-1317: 53: 22");
		resultMap2.put("newsSort", 2);
		resultMap2.put("showType", 0);
		resultMap2.put("fullColumnImgUrl", "");
		resultMap2.put("hasVideo", false);
		resultMap2.put("isLive",false );
		resultMap2.put("isLiveReplay", false);
		resultMap2.put("isTopic", 5);
		
		Map topicDetail = new HashMap();
		topicDetail.put("topicDesc", "中国近5年经济发展良好势头");
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
	 * topic专题 新闻列表
	 * */
	@RequestMapping(value="/1.0/getTopicNews",method=RequestMethod.GET)
	public PageResult getTopicNewsList(String topicId,int pageSize,int pageNo){
//		List<String> newsInfoIdList = this.topicNewsService.getNewsInfoId(topicId);
//		return this.newsInfoService.findTopicNewsList(newsInfoIdList,pageSize,pageNo);
		Map map = new HashMap();
		map.put("newsTitle", " 美国总统特朗普访华");
		map.put("newsId", "7346576926643483554");
		map.put("newsTags", "特朗普访华");
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
		photo.put("picPath", "图片地址");
		photo.put("picTitle", "特朗普习近平我收");
		photo.put("picDesc", "特朗普与习近平故宫照");
		Map photo2 = new HashMap();
		photo2.put("pictureId", "74326432789");
		photo2.put("picPath", "图片地址");
		photo2.put("picTitle", "特朗普习近平我收");
		photo2.put("picDesc", "特朗普、习近平北京游");
		photoList.add(photo2);
		photoList.add(photo);
		map.put("photoList", photoList);
		Map map1 = new HashMap();
		map1.put("newsTitle", "梅西与习近平增球衣");
		map1.put("newsId", "7346576926643483554");
		map1.put("newsTags", "习近平与曼联");
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
