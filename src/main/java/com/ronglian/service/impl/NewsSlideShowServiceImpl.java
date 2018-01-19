/**
 * 
 */
package com.ronglian.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.SlideShowDao;
import com.ronglian.entity.NewsSlideshow;
import com.ronglian.service.NewsSlideShowService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017年12月28日
 */
@Service
public class NewsSlideShowServiceImpl implements NewsSlideShowService {
	@Autowired
	private SlideShowDao slideShowDao;
	@Override
	public RongLianResult addSlideShow(NewsSlideshow slideShow) {
		if(slideShow.getSlideShowId()==null||slideShow.getImageUrl()==null||slideShow.getChannelUniqueId()==null
				||slideShow.getTitle()==null||slideShow.getDesc()==null){
			return RongLianResult.build(500, "缺少数据");
		}
		slideShow.setCreateTime((slideShowDao.findOne(slideShow.getSlideShowId())!=null)?slideShowDao.findOne(slideShow.getSlideShowId()).getCreateTime():(new Date()));
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
	public RongLianResult getSlideShowByChannel(String channelUniqueId) {
		// TODO Auto-generated method stub
		List<NewsSlideshow> list = null;
		if(StringUtils.isNotBlank(channelUniqueId) ){
			list = this.slideShowDao.selectSlideShowByChannel(channelUniqueId);
		}else{
			return RongLianResult.build(500, "请求参数channelUniqueId不可以为null");
		}
		return RongLianResult.ok(list);
	}

	
}
