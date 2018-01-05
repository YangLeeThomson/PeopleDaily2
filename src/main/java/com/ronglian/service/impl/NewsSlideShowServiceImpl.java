/**
 * 
 */
package com.ronglian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.SlideShowDao;
import com.ronglian.entity.NewsSlideshow;
import com.ronglian.service.NewsSlideShowService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ28ÈÕ
 */
@Service
public class NewsSlideShowServiceImpl implements NewsSlideShowService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsSlideShowService#addSlideShow(com.ronglian.entity.NewsSlideshow)
	 */
	@Autowired
	private SlideShowDao slideShowDao;
	@Override
	public RongLianResult addSlideShow(NewsSlideshow slideShow) {
		// TODO Auto-generated method stub
		NewsSlideshow result = this.slideShowDao.save(slideShow);
		if(result != null){
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(500, "save error");
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.NewsSlideShowService#getSlidePictureByChannel(java.lang.String)
	 */
	@Override
	public RongLianResult getSlideShowByChannel(String channelId) {
		// TODO Auto-generated method stub
		List<NewsSlideshow> list = this.slideShowDao.selectSlideShowByChannel(channelId);
		return RongLianResult.ok(list);
	}

	
}
