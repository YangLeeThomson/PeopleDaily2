/**
 * 
 */
package com.ronglian.service;

import com.ronglian.entity.NewsInfoDislike;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2018��1��10��
 */
public interface ContentDislikeService {

	public RongLianResult addContentDislike(NewsInfoDislike obj);
}
