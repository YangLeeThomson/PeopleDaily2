/**
 * 
 */
package com.ronglian.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.ronglian.service.NewsInfoService;
import com.ronglian.utils.PageResult;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.ImageInfo;
import com.ronglian.dao.NewsPictureDao;

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
	public PageResult findNewsList(int pageSize, int pageNo, String channelId) {
		int start = 0;
		if(channelId != null){
			start = (pageNo-1)*pageSize;
			List<NewsInfo> list = this.newsInfoDao.selectNewsInfoByChannel(channelId,start,pageSize);
			if(list != null && list.size() > 0){
				return PageResult.build(0, "ok", pageNo, pageSize, list);
			}else{
				return PageResult.error(500, "查询结果为空或内容不存在", pageNo, pageSize);
			}
		}else{
			return PageResult.error(500, "请求参数channelId不能为空", pageNo, pageSize);
		}
		
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findTopnewsList(java.lang.String)
	 */
	@Override
	public RongLianResult findTopnewsList(String channelId) {
		if(channelId != null){
			List<NewsInfo> list = this.newsInfoDao.selectTopnewsByChannel(channelId);
			if(list != null && list.size() > 0){
				return RongLianResult.ok(list);
			}else{
				return RongLianResult.build(500, "查询结果为空或内容不存在");
			}
		}else{
			return RongLianResult.build(500, "请求参数channelId不能为空");
		}
		
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findEditorNewsList(java.lang.String)
	 */
	@Override
	public RongLianResult findEditorNewsList(String channelId) {
		if(channelId != null){
			List<NewsInfo> list = this.newsInfoDao.selectEditorNewsByChannel(channelId);
			if(list != null && list.size() > 0){
				return RongLianResult.ok(list);
			}else{
				return RongLianResult.build(500, "查询结果为null");
			}
		}else{
			return RongLianResult.build(500, "请求参数channelId不能为空");
		}
		
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findTopicNewsList(java.util.List)
	 */
	@Override
	public PageResult findTopicNewsList(List<String> list,int pageNo,int pageSize) {
		if(list == null ){
			return PageResult.build(500, "专题没有对应的新闻", pageNo, pageSize, list);
		}
		pageNo = (pageNo-1)*pageSize;
		List<NewsInfo> newsInfoList = this.newsInfoDao.selectTopicNewsByNewsInfoId(list,pageNo,pageSize);
		if(newsInfoList != null && newsInfoList.size() > 0){
			return PageResult.build(0, "ok", pageNo, pageSize, newsInfoList);
		}else{
			return PageResult.error(500, "专题对应的新闻内容不存在", pageNo, pageSize);
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
			return RongLianResult.ok(newsInfo);
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
				if(map.get("channelId")==null||map.get("channelName")==null){
					return RongLianResult.build(500, "缺少参数");
				}
				NewsInfo newsInfo=new NewsInfo(map.get("newsId").toString(), (map.get("canComment")!=null)?map.get("canComment").toString():null, (map.get("channelId")!=null)?map.get("channelId").toString():null,
						(map.get("channelName")!=null)?map.get("channelName").toString():null, null, (map.get("contentId")!=null)?(int)map.get("contentId"):null,
						null, (map.get("createTime")!=null)?sdf.parse(map.get("createTime").toString()):null, (map.get("editExpire")!=null)?sdf.parse(map.get("editExpire").toString()):null,
						null, (map.get("isEditRecom")!=null)?(map.get("isEditRecom").toString().toString().equals("1")?(byte)1:(byte)0):null, (map.get("isToTop")!=null)?(map.get("isToTop").toString().equals("1")?(byte)1:(byte)0):null , (map.get("isTopic")!=null)?(int)map.get("isTopic"):null,
						null, null, null,
						(map.get("modifyTime")!=null)?sdf.parse(map.get("modifyTime").toString()):null, (map.get("newsAuthors")!=null)?map.get("newsAuthors").toString():null, (map.get("newsContent")!=null)?map.get("newsContent").toString():null,
								(map.get("newsOrganization")!=null)?map.get("newsOrganization").toString():null, (map.get("newsOriginal")!=null)?(int)map.get("newsOriginal"):null, (map.get("newsSort")!=null)?(int)map.get("newsSort"):null,
										(map.get("newsSource")!=null)?map.get("newsSource").toString():null, (map.get("newsSourceUrl")!=null)?map.get("newsSourceUrl").toString():null, null,
												(map.get("newsSummary")!=null)?map.get("newsSummary").toString():null, (map.get("newsTags")!=null)?map.get("newsTags").toString():null, (map.get("newsTitle")!=null)?map.get("newsTitle").toString():null,
														(map.get("publishTime")!=null)?sdf.parse(map.get("publishTime").toString()):null, (map.get("topExpire")!=null)?sdf.parse(map.get("topExpire").toString()):null, null,null, null,
																(map.get("dataStatus")!=null)?(int)map.get("dataStatus"):null, (map.get("showType")!=null)?(int)map.get("showType"):null,(map.get("fullColumnImgUrl")!=null)?map.get("fullColumnImgUrl").toString():null,
																		(map.get("hasVideo")!=null)?(map.get("hasVideo").toString().equals("true")?(byte)1:(byte)0):null, (map.get("isLive")!=null)?(map.get("isLive").toString().equals("true")?(byte)1:(byte)0):null,(map.get("isLiveReplay")!=null)?(map.get("isLiveReplay").toString().equals("true")?(byte)1:(byte)0):null);
				newsPictureDao.deleteByNewsID(newsInfo.getNewsId());
				String[] imgs=getImgs(newsInfo.getNewsContent());
				int i=0;
				if(imgs!=null){
					for(;i<imgs.length;i++){
						NewsPicture newsPicture=new NewsPicture(newsInfo.getNewsId(),newsInfo.getNewsId()+"_"+i,imgs[i],i);
						newsPictureDao.save(newsPicture);
					}
				}
				if(map.get("imageList")!=null){
				List<Map> imageList = (List<Map>)map.get("imageList");
		        for(Map imageInfoMap:imageList){
		        	NewsPicture newsPicture=new NewsPicture(newsInfo.getNewsId()
		        			,newsInfo.getNewsId()+"_"+i
		        			,imageInfoMap.get("picPath")!=null?imageInfoMap.get("picPath").toString():null
		        			,imageInfoMap.get("picDesc")!=null?imageInfoMap.get("picDesc").toString():null
		        			,imageInfoMap.get("picTitle")!=null?imageInfoMap.get("picTitle").toString():null);
		        	i++;
		        	newsPictureDao.save(newsPicture);
		        }
				}
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
		List<Integer> list = new ArrayList<Integer>();
		list.add(incNo+1);
		list.add(incNo+2);
		list.add(incNo-1);
		list.add(incNo-2);
		List<NewsInfo> resultList = this.newsInfoDao.selectNewsInfoNearByIncNo(list);
		if(resultList != null && resultList.size() > 0){
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