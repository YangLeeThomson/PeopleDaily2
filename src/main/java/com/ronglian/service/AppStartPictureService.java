/**
 * 
 */
package com.ronglian.service;

import com.ronglian.entity.AppStartPicture;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��28��
 */
public interface AppStartPictureService {

	public RongLianResult addAppStartPicture(AppStartPicture appStartPicture);
	
	public RongLianResult getStartPicture();
}
