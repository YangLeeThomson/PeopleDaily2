/**
 * 
 */
package com.ronglian.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ronglian.dao.NewsInfoDao;
import com.ronglian.entity.NewsInfo;
import com.ronglian.entity.NewsPicture;
import com.ronglian.entity.NewsTopic;
import com.ronglian.service.NewsInfoService;
import com.ronglian.utils.PageCountResult;
import com.ronglian.utils.PageResult;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.RongLianUtils;
import com.ronglian.utils.model.request.ImageInfo;
import com.ronglian.dao.NewsPictureDao;
import com.ronglian.dao.TopicDao;

/**
 * @author liyang
 * @createTime 2017年12月27日
 */
@Service
public class NewsInfoServiceImpl implements NewsInfoService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#inserNewsInfo(com.ronglian.entity.NewsInfo)
	 */
	@Autowired
	private NewsInfoDao newsInfoDao;
	@Autowired
	private NewsPictureDao newsPictureDao;
	@Autowired
	private TopicDao topicDao;
	@Override
	public RongLianResult inserNewsInfo(NewsInfo newsInfo) {
		NewsInfo result = this.newsInfoDao.save(newsInfo);
		if(result != null){
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(500, "save error");
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findNewsList(int, int, java.lang.String)
	 */
	@Override
	public PageCountResult findNewsList(int pageSize, int pageNo, String channelUniqueId) {
		int start = 0;
		int counter = 0;
		List<Map> resultList = new ArrayList<Map>();
		if(channelUniqueId != null){
			start = (pageNo-1)*pageSize;
			List<NewsInfo> list = this.newsInfoDao.selectNewsInfoByChannel(channelUniqueId,start,pageSize);
			if(list != null && list.size() > 0){
//				counter = list.size();
				counter = this.newsInfoDao.countNewsInfoByChannel(channelUniqueId);
				for(NewsInfo news:list){
					Map resultMap = new HashMap();
					resultMap.put("newsTitle", news.getNewsTitle());
					resultMap.put("newsId", news.getNewsId());
					resultMap.put("newsTags", news.getNewsTags());
					resultMap.put("publishTime", RongLianUtils.changeDateTime(news.getPublishTime()));
					resultMap.put("newsSort", news.getNewsSort());
					resultMap.put("showType", news.getShowType());
					resultMap.put("fullColumnImgUrl", news.getFullColumnImgUrl());
					resultMap.put("hasVideo", news.getHasVideo());
					resultMap.put("isLive", news.getIsLive());
					resultMap.put("isLiveReplay", news.getIsLiveReplay());
					
					Integer topicId = news.getIsTopic();
					resultMap.put("isTopic", topicId);
					//如果topicId 不等于0，说明是专题，需要进一步查询
					if(topicId > 0){
						//查询专题
						NewsTopic topic = this.topicDao.findOne(topicId);
						if(topic != null){
							Map topicDetail = new HashMap();
							topicDetail.put("topicDesc", topic.getTopicDesc());
							topicDetail.put("bannerPhoto", topic.getBannerImage());
							topicDetail.put("coverPhoto", topic.getListImage());
							resultMap.put("topicDetail",topicDetail);
						}else{
							resultMap.put("topicDetail",null);
						}
					}else{//isTopic() 等于 0
						resultMap.put("topicDetail",null);
					}
					
					//查看图片数目是否是0，数量大于0，需要进一步查看
					Integer imageCount = news.getImageList();
					if(imageCount == null){
						imageCount = 0;
					}
					resultMap.put("imageCount", imageCount);
					
					if(imageCount > 0){
						List<NewsPicture> pictures = this.newsPictureDao.selectNewsPictureByNewsId(news.getNewsId());
						if(pictures != null && pictures.size() > 0){
							List<Map> photoList = new ArrayList<Map>();
							for(NewsPicture picture:pictures){
								Map photo = new HashMap();
								photo.put("pictureId", picture.getPictureId());
								photo.put("picPath", picture.getImagePath());
								photo.put("picTitle", picture.getPictureTitle());
								photo.put("picDesc", picture.getPictureDesc());
								photoList.add(photo);
							}
							resultMap.put("photoList",photoList);
						}else{
							resultMap.put("photoList",null);
						}
					}else{//imageList等于0
						resultMap.put("photoList",null);
					}
					resultList.add(resultMap);
				}
				return PageCountResult.build(0, "ok",counter,pageNo, pageSize, resultList);
			}else{
				return PageCountResult.error(500, "查询结果为空或内容不存在", pageNo, pageSize);
			}
		}else{
			return PageCountResult.error(500, "请求参数channelUniqueId不能为空", pageNo, pageSize);
		}
		
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findTopnewsList(java.lang.String)
	 */
	@Override
	public RongLianResult findTopnewsList(String channelUniqueId) {
		if(channelUniqueId != null){
			List<Map> resultList = new ArrayList<Map>();
			List<NewsInfo> list = this.newsInfoDao.selectTopnewsByChannel(channelUniqueId);
			if(list != null && list.size() > 0){
				for(NewsInfo news:list){
					Map resultMap = new HashMap();
					resultMap.put("newsTitle", news.getNewsTitle());
					resultMap.put("newsId", news.getNewsId());
					resultMap.put("newsTags", news.getNewsTags());
					resultMap.put("publishTime", news.getPublishTime());
					resultMap.put("newsSort", news.getNewsSort());
					resultMap.put("showType", news.getShowType());
					resultMap.put("fullColumnImgUrl", news.getShowType());
					resultMap.put("hasVideo", news.getHasVideo());
					resultMap.put("isLive", news.getIsLive());
					resultMap.put("isLiveReplay", news.getIsLiveReplay());
					
					Integer topicId = news.getIsTopic();
					resultMap.put("isTopic", topicId);
					//如果topicId 不等于0，说明是专题，需要进一步查询
					if(topicId > 0){
						//查询专题
						NewsTopic topic = this.topicDao.findOne(topicId);
						if(topic != null){
							Map topicDetail = new HashMap();
							topicDetail.put("topicDesc", topic.getTopicDesc());
							topicDetail.put("bannerPhoto", topic.getBannerImage());
							topicDetail.put("coverPhoto", topic.getListImage());
							resultMap.put("topicDetail",topicDetail);
						}else{
							resultMap.put("topicDetail",null);
						}
					}else{//isTopic() 等于 0
						resultMap.put("topicDetail",null);
					}
					
					//查看图片数目是否是0，数量大于0，需要进一步查看
					Integer imageCount = news.getImageList();
					if(imageCount == null){
						imageCount = 0;
					}
					resultMap.put("imageCount", imageCount);
					
					if(imageCount > 0){
						List<NewsPicture> pictures = this.newsPictureDao.selectNewsPictureByNewsId(news.getNewsId());
						if(pictures != null && pictures.size() > 0){
							List<Map> photoList = new ArrayList<Map>();
							for(NewsPicture picture:pictures){
								Map photo = new HashMap();
								photo.put("pictureId", picture.getPictureId());
								photo.put("picPath", picture.getImagePath());
								photo.put("picTitle", picture.getPictureTitle());
								photo.put("picDesc", picture.getPictureDesc());
								photoList.add(photo);
							}
							resultMap.put("photoList",photoList);
						}else{
							resultMap.put("photoList",null);
						}
					}else{//imageList等于0
						resultMap.put("photoList",null);
					}
					resultList.add(resultMap);
				}
				return RongLianResult.ok(resultList);
			}else{
				return RongLianResult.build(500, "查询结果为空或内容不存在");
			}
		}else{
			return RongLianResult.build(500, "请求参数channelUniqueId不能为空");
		}
		
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findEditorNewsList(java.lang.String)
	 */
	@Override
	public RongLianResult findEditorNewsList(String channelUniqueId) {
		if(channelUniqueId != null){
			List<Map> resultList = new ArrayList<Map>();
			List<NewsInfo> list = this.newsInfoDao.selectEditorNewsByChannel(channelUniqueId);
			if(list != null && list.size() > 0){
				for(NewsInfo news:list){
					Map resultMap = new HashMap();
					resultMap.put("newsTitle", news.getNewsTitle());
					resultMap.put("newsId", news.getNewsId());
					resultMap.put("newsTags", news.getNewsTags());
					resultMap.put("publishTime", news.getPublishTime());
					resultMap.put("newsSort", news.getNewsSort());
					resultMap.put("showType", news.getShowType());
					resultMap.put("fullColumnImgUrl", news.getShowType());
					resultMap.put("hasVideo", news.getHasVideo());
					resultMap.put("isLive", news.getIsLive());
					resultMap.put("isLiveReplay", news.getIsLiveReplay());
					
					Integer topicId = news.getIsTopic();
					resultMap.put("isTopic", topicId);
					//如果topicId 不等于0，说明是专题，需要进一步查询
					if(topicId > 0){
						//查询专题
						NewsTopic topic = this.topicDao.findOne(topicId);
						if(topic != null){
							Map topicDetail = new HashMap();
							topicDetail.put("topicDesc", topic.getTopicDesc());
							topicDetail.put("bannerPhoto", topic.getBannerImage());
							topicDetail.put("coverPhoto", topic.getListImage());
							resultMap.put("topicDetail",topicDetail);
						}else{
							resultMap.put("topicDetail",null);
						}
					}else{//isTopic() 等于 0
						resultMap.put("topicDetail",null);
					}
					
					//查看图片数目是否是0，数量大于0，需要进一步查看
					Integer imageCount = news.getImageList();
					if(imageCount == null){
						imageCount = 0;
					}
					resultMap.put("imageCount", imageCount);
					
					if(imageCount > 0){
						List<NewsPicture> pictures = this.newsPictureDao.selectNewsPictureByNewsId(news.getNewsId());
						if(pictures != null && pictures.size() > 0){
							List<Map> photoList = new ArrayList<Map>();
							for(NewsPicture picture:pictures){
								Map photo = new HashMap();
								photo.put("pictureId", picture.getPictureId());
								photo.put("picPath", picture.getImagePath());
								photo.put("picTitle", picture.getPictureTitle());
								photo.put("picDesc", picture.getPictureDesc());
								photoList.add(photo);
							}
							resultMap.put("photoList",photoList);
						}else{
							resultMap.put("photoList",null);
						}
					}else{//imageList等于0
						resultMap.put("photoList",null);
					}
					resultList.add(resultMap);
				}
				return RongLianResult.ok(resultList);
			}else{
				return RongLianResult.build(500, "查询结果为null");
			}
		}else{
			return RongLianResult.build(500, "请求参数channelUniqueId不能为空");
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findTopicNewsList(java.util.List)
	 */
	@Override
	public PageCountResult findTopicNewsList(String topicId,int pageNo,int pageSize) {
		if(topicId == null ){
			return PageCountResult.error(500, "topicId不能为空", pageNo, pageSize);
		}
		pageNo = (pageNo-1)*pageSize;
		int count = 0;
		List<NewsInfo> newsInfoList = this.newsInfoDao.selectTopicNewsByNewsInfoId(topicId,pageNo,pageSize);
		List<Map> resultList = new ArrayList<Map>();
		if(newsInfoList != null && newsInfoList.size() > 0){
			count = this.newsInfoDao.countTopicNewsByNewsInfoId(topicId);
			for(NewsInfo news:newsInfoList){
				Map resultMap = new HashMap();
				resultMap.put("newsTitle", news.getNewsTitle());
				resultMap.put("newsId", news.getNewsId());
				resultMap.put("newsTags", news.getNewsTags());
				resultMap.put("publishTime", news.getPublishTime());
				resultMap.put("newsSort", news.getNewsSort());
				resultMap.put("showType", news.getShowType());
				resultMap.put("fullColumnImgUrl", news.getShowType());
				resultMap.put("hasVideo", news.getHasVideo());
				resultMap.put("isLive", news.getIsLive());
				resultMap.put("isLiveReplay", news.getIsLiveReplay());
				resultMap.put("isTopic", news.getIsTopic());
				resultMap.put("topicId", news.getTopicId());
				
				//如果isTopic=1，说明是专题，需要进一步查询
				if(news.getIsTopic() == 1){
					//查询专题
					NewsTopic topic = this.topicDao.getNewsTopicByTopicId(topicId);
					if(topic != null){
						Map topicDetail = new HashMap();
						topicDetail.put("topicDesc", topic.getTopicDesc());
						topicDetail.put("bannerPhoto", topic.getBannerImage());
						topicDetail.put("coverPhoto", topic.getListImage());
						resultMap.put("topicDetail",topicDetail);
					}else{
						resultMap.put("topicDetail",null);
					}
				}else{//isTopic() 等于 0
					resultMap.put("topicDetail",null);
				}
				
				//查看图片数目是否是0，数量大于0，需要进一步查看
				Integer imageCount = news.getImageList();
				if(imageCount == null){
					imageCount = 0;
				}
				resultMap.put("imageCount", imageCount);
				
				if(imageCount > 0){
					List<NewsPicture> pictures = this.newsPictureDao.selectNewsPictureByNewsId(news.getNewsId());
					if(pictures != null && pictures.size() > 0){
						List<Map> photoList = new ArrayList<Map>();
						for(NewsPicture picture:pictures){
							Map photo = new HashMap();
							photo.put("pictureId", picture.getPictureId());
							photo.put("picPath", picture.getImagePath());
							photo.put("picTitle", picture.getPictureTitle());
							photo.put("picDesc", picture.getPictureDesc());
							photoList.add(photo);
						}
						resultMap.put("photoList",photoList);
					}else{
						resultMap.put("photoList",null);
					}
				}else{//imageList等于0
					resultMap.put("photoList",null);
				}
				resultList.add(resultMap);
			}
			return PageCountResult.build(0, "ok",count,pageNo, pageSize, resultList);
		}else{
			return PageCountResult.error(500, "专题对应的新闻内容不存在", pageNo, pageSize);
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#getNewsInfoContent(java.lang.String)
	 */
	@Override
	public RongLianResult getNewsInfoContent(String newsId) {
		if(newsId == null){
			return RongLianResult.build(500, "请求参数newsId不可以为空");
		}
		NewsInfo newsInfo = this.newsInfoDao.findOne(newsId);
		if(newsInfo != null){
			Map data = new HashMap();
			data.put("incNo",newsInfo.getContentId() );
			data.put("newsContent",newsInfo.getNewsContent() );
			data.put("newsOrganization", newsInfo.getNewsOrganization());
			data.put("newsAuthors", newsInfo.getNewsAuthors());
			data.put("publishTime", RongLianUtils.changeDateTime(newsInfo.getPublishTime()));
			data.put("newsTitle",newsInfo.getNewsTitle() );
			data.put("appriseUPCount",newsInfo.getAppriseUpNum());
			data.put("appriseDownCount",newsInfo.getAppriseDownNum());
			data.put("commentNum", newsInfo.getCommentNum());
			Integer imageCount = newsInfo.getImageList();
			if(imageCount == null){
				imageCount = 0;
			}
			data.put("imageCount",imageCount );
			
			if(imageCount > 0){
				List<NewsPicture> pictures = this.newsPictureDao.selectNewsPictureByNewsId(newsInfo.getNewsId());
				if(pictures != null && pictures.size() > 0){
					List<Map> photoList = new ArrayList<Map>();
					for(NewsPicture picture:pictures){
						Map photo = new HashMap();
						photo.put("pictureId", picture.getPictureId());
						photo.put("picPath", picture.getImagePath());
						photo.put("picTitle", picture.getPictureTitle());
						photo.put("picDesc", picture.getPictureDesc());
						photoList.add(photo);
					}
					data.put("imageList",photoList);
				}else{
					data.put("imageList",null);
				}
			}else{
				data.put("imageList",null);
			}
			return RongLianResult.ok(data);
		}else{
			return RongLianResult.build(500, "所查询的新闻内容不存在");
		}
	}
	@Override
	public RongLianResult addNewsInfo(String newsStr) throws JsonParseException, JsonMappingException, IOException, NumberFormatException, ParseException{
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/*try{*/
		Map map = mapper.readValue(newsStr, Map.class);
		if(map != null){
			if(map.get("newsId")==null){
				return RongLianResult.build(500, "newsId不能为空");
			}else{
				if(map.get("channelUniqueId")==null||map.get("channelName")==null){
					return RongLianResult.build(500, "缺少参数");
				}
				NewsInfo newsInfo=new NewsInfo(map.get("newsId").toString(), (map.get("canComment")!=null)?map.get("canComment").toString():null, (map.get("channelUniqueId")!=null)?map.get("channelUniqueId").toString():null,
						(map.get("channelName")!=null)?map.get("channelName").toString():null, null, (map.get("contentId")!=null)?(int)map.get("contentId"):null,
						null, (map.get("createTime")!=null)?sdf.parse(map.get("createTime").toString()):null, (map.get("editExpire")!=null)?sdf.parse(map.get("editExpire").toString()):null,
						null, (map.get("isEditRecom")!=null)?(map.get("isEditRecom").toString().toString().equals("true")?(byte)1:(byte)0):null, (map.get("isToTop")!=null)?(map.get("isToTop").toString().equals("true")?(byte)1:(byte)0):null , (map.get("isTopic")!=null)?(int)map.get("isTopic"):null,
						null, null, null,
						(map.get("modifyTime")!=null)?sdf.parse(map.get("modifyTime").toString()):null, (map.get("newsAuthors")!=null)?map.get("newsAuthors").toString():null, (map.get("newsContent")!=null)?map.get("newsContent").toString():null,
								(map.get("newsOrganization")!=null)?map.get("newsOrganization").toString():null, (map.get("newsOriginal")!=null)?(int)map.get("newsOriginal"):null, (map.get("newsSort")!=null)?(int)map.get("newsSort"):null,
										(map.get("newsSource")!=null)?map.get("newsSource").toString():null, (map.get("newsSourceUrl")!=null)?map.get("newsSourceUrl").toString():null, null,
												(map.get("newsSummary")!=null)?map.get("newsSummary").toString():null, (map.get("newsTags")!=null)?map.get("newsTags").toString():null, (map.get("newsTitle")!=null)?map.get("newsTitle").toString():null,
														(map.get("publishTime")!=null)?sdf.parse(map.get("publishTime").toString()):null, (map.get("topExpire")!=null)?sdf.parse(map.get("topExpire").toString()):null, null,null, null,
																(map.get("dataStatus")!=null)?(int)map.get("dataStatus"):null, (map.get("showType")!=null)?(int)map.get("showType"):null,(map.get("fullColumnImgUrl")!=null)?map.get("fullColumnImgUrl").toString():null,
																		(map.get("hasVideo")!=null)?(map.get("hasVideo").toString().equals("true")?(byte)1:(byte)0):null, (map.get("isLive")!=null)?(map.get("isLive").toString().equals("true")?(byte)1:(byte)0):null,(map.get("isLiveReplay")!=null)?(map.get("isLiveReplay").toString().equals("true")?(byte)1:(byte)0):null);
				newsPictureDao.deleteByNewsID(newsInfo.getNewsId());
				int i=0;
				boolean less=false;
				if(newsInfo.getNewsContent()!=null){
				String[] imgs=getImgs(newsInfo.getNewsContent());
				if(imgs!=null){
					for(;i<imgs.length;i++){
						NewsPicture newsPicture=new NewsPicture(newsInfo.getNewsId(),newsInfo.getNewsId()+"_"+i,imgs[i],i);
						newsPictureDao.save(newsPicture);
					}
					less=true;
				}
				}
				if(map.get("imageList")!=null){
				List<Map> imageList = (List<Map>)map.get("imageList");
		        for(Map imageInfoMap:imageList){
		        	NewsPicture newsPicture=new NewsPicture(newsInfo.getNewsId()
		        			,newsInfo.getNewsId()+"_"+i
		        			,imageInfoMap.get("picPath")!=null?imageInfoMap.get("picPath").toString():null
		        			,imageInfoMap.get("picDesc")!=null?imageInfoMap.get("picDesc").toString():null
		        			,imageInfoMap.get("picTitle")!=null?imageInfoMap.get("picTitle").toString():null
		        			,i);
		        	i++;
		        	newsPictureDao.save(newsPicture);
		        }
				}
				newsInfo.setImageList(i);
				this.newsInfoDao.save(newsInfo);
				return RongLianResult.ok();
			}
		}else{
			return RongLianResult.build(500, "未传参数或参数格式不对");
		}
	}
	
	private String[] getImgs(String content) {  
	    String img = "";  
	    Pattern p_image;  
	    Matcher m_image;  
	    String str = "";  
	    String[] images = null; 
	    String regEx_img = "<(img|IMG)(.*?)(/>|></img>|>)";
	    p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);  
	    m_image = p_image.matcher(content);  
	    while (m_image.find()) {  
	        img = m_image.group();  
	        Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);  
	        while (m.find()) {  
	            String tempSelected = m.group(1);  
	  
	            if ("".equals(str)) {  
	                str = tempSelected;  
	            } else {  
	                String temp = tempSelected;  
	                str = str + "," + temp;  
	            }  
	        }  
	    }  
	    if (!"".equals(str)) {  
	        images = str.split(",");  
	    }  
	    return images;  
	}  
	@Override
	public RongLianResult getPhotoNewsByNewsId(String newsID,Integer incNo){
		if(incNo == null){
			return RongLianResult.build(500, "请求参数incNo不能为null");
		}
		List<NewsInfo> list = this.newsInfoDao.selectNewsInfoNearUpByIncNo(incNo);
		List<NewsInfo> list2 = this.newsInfoDao.selectNewsInfoNearDownByIncNo(incNo);
		if(list != null){
			list.addAll(list2);
		}else{
			list = list2;
		}
		if(list != null && list.size() > 0){
			List<Map> resultList = new ArrayList<Map>();
			
			for(NewsInfo news:list){
				Map result = new HashMap();
				result.put("newsTitle", news.getNewsTitle());
				result.put("newsId", news.getNewsId());
				result.put("newsTags", news.getNewsTags());
				result.put("publishTime", RongLianUtils.changeDateTime(news.getPublishTime()));
				result.put("newsSort", news.getNewsSort());
				
				List<NewsPicture> photos = this.newsPictureDao.selectNewsPictureByNewsId(news.getNewsId());
				List pictureList = new ArrayList();
					for(NewsPicture picture:photos){
						Map photo = new HashMap();
						photo.put("pictureId", picture.getPictureId());
						photo.put("picTitle", picture.getPictureTitle());
						photo.put("picPath", picture.getImagePath());
						photo.put("picDesc", picture.getPictureDesc());
						pictureList.add(photo);
					}
				result.put("pictureList", pictureList);
				resultList.add(result);
			}
			//4条数据，2条数据、1条都输出
			if(resultList.size() == 4 || resultList.size() == 2 || resultList.size() == 1){
				return RongLianResult.ok(resultList);
			}else{
				//3条数据，删除1条，输出2条
				resultList.remove(0);
				return RongLianResult.ok(resultList);
			}
		}else{
			return RongLianResult.build(500, "当前图集附近无其它图集");
		}
	}
	
}