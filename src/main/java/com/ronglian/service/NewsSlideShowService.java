/**
 * 
 */
package com.ronglian.service;

import com.ronglian.entity.NewsSlideshow;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��28��
 */
public interface NewsSlideShowService {

	public RongLianResult addSlideShow(NewsSlideshow slideShow);
	
	public RongLianResult getSlideShowByChannel(String channelId);
}
