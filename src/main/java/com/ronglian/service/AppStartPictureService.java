/**
 * 
 */
package com.ronglian.service;

import com.ronglian.entity.AppStartPicture;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ28ÈÕ
 */
public interface AppStartPictureService {

	public RongLianResult addAppStartPicture(AppStartPicture appStartPicture);
	
	public RongLianResult getStartPicture();
}
