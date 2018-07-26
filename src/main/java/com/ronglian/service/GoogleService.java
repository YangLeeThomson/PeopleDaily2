/**   
 * Copyright © 2018 北京荣之联科技股份有限公司 All rights reserved.
 * 
 * @Package: com.ronglian.service 
 * @author: YeohLee   
 * @date: 2018年7月26日 上午11:06:18 
 */
package com.ronglian.service;

import com.ronglian.entity.Device;
import com.ronglian.utils.RongLianResult;

 /** 
 * @ClassName: GoogleService 
 * @Description: TODO
 * @author: YeohLee
 * @date: 2018年7月26日 上午11:06:18  
 */
public interface GoogleService {

	public RongLianResult pushToGoogle(Device device);
}
