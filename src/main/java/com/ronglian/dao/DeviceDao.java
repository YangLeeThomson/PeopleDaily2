/**   
 * Copyright © 2018 北京荣之联科技股份有限公司 All rights reserved.
 * 
 * @Package: com.ronglian.dao 
 * @author: YeohLee   
 * @date: 2018年7月26日 下午4:44:56 
 */
package com.ronglian.dao;

import org.springframework.data.repository.CrudRepository;

import com.ronglian.entity.Device;

 /** 
 * @ClassName: DeviceDao 
 * @Description: TODO
 * @author: YeohLee
 * @date: 2018年7月26日 下午4:44:56  
 */
public interface DeviceDao extends CrudRepository<Device, String>{

	
}
