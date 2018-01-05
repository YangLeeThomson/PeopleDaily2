/**
 * 
 */
package com.ronglian.service;

import com.ronglian.entity.NewsSlideshow;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ28ÈÕ
 */
public interface NewsSlideShowService {

	public RongLianResult addSlideShow(NewsSlideshow slideShow);
	
	public RongLianResult getSlideShowByChannel(String channelId);
}
