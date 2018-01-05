/**
 * 
 */
package com.ronglian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.NewsInfoDao;
import com.ronglian.entity.NewsInfo;
import com.ronglian.service.NewsInfoService;
import com.ronglian.utils.PageResult;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
@Service
public class NewsInfoServiceImpl implements NewsInfoService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#inserNewsInfo(com.ronglian.entity.NewsInfo)
	 */
	@Autowired
	private NewsInfoDao newsInfoDao;
	@Override
	public RongLianResult inserNewsInfo(NewsInfo newsInfo) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		List<NewsInfo> list = this.newsInfoDao.selectNewsInfoByChannel(pageSize, pageNo, channelId);
		if(list !=null && list.size() > 0){
			return PageResult.build(0, "ok", pageNo, pageSize, list);
		}else{
			return PageResult.error(500, "error", pageNo, pageSize);
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findTopnewsList(java.lang.String)
	 */
	@Override
	public RongLianResult findTopnewsList(String channelId) {
		// TODO Auto-generated method stub
		List<NewsInfo> list = this.newsInfoDao.selectTopnewsByChannel(channelId);
		if(list != null && list.size() > 0){
			return RongLianResult.ok(list);
		}else{
			return RongLianResult.build(500, "error");
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findEditorNewsList(java.lang.String)
	 */
	@Override
	public RongLianResult findEditorNewsList(String channelId) {
		// TODO Auto-generated method stub
		List<NewsInfo> list = this.newsInfoDao.selectEditorNewsByChannel(channelId);
		if(list != null && list.size() > 0){
			return RongLianResult.ok(list);
		}else{
			return RongLianResult.build(500, "error");
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#findTopicNewsList(java.util.List)
	 */
	@Override
	public PageResult findTopicNewsList(List<String> list,int pageSize,int pageNo) {
		// TODO Auto-generated method stub
		List<NewsInfo> newsInfoList = this.newsInfoDao.selectTopicNewsByNewsInfoId(list,pageSize,pageNo);
		if(newsInfoList !=null && newsInfoList.size() > 0){
			return PageResult.build(0, "ok", pageNo, pageSize, newsInfoList);
		}else{
			return PageResult.error(500, "error", pageNo, pageSize);
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsInfoService#getNewsInfoContent(java.lang.String)
	 */
	@Override
	public RongLianResult getNewsInfoContent(String newsId) {
		// TODO Auto-generated method stub
		NewsInfo newsInfo = this.newsInfoDao.findOne(newsId);
		if(newsInfo != null){
			return RongLianResult.ok(newsInfo);
		}else{
			return RongLianResult.build(500, "error");
		}
	}

}
