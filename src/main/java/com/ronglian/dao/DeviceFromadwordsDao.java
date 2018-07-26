/**   
 * Copyright © 2018 北京荣之联科技股份有限公司 All rights reserved.
 * 
 * @Package: com.ronglian.dao 
 * @author: YeohLee   
 * @date: 2018年7月26日 下午6:20:16 
 */
package com.ronglian.dao;

import org.springframework.data.repository.CrudRepository;

import com.ronglian.entity.DeviceFromadwords;

 /** 
 * @ClassName: DeviceFromadwordsDao 
 * @Description: TODO
 * @author: YeohLee
 * @date: 2018年7月26日 下午6:20:16  
 */
public interface DeviceFromadwordsDao extends CrudRepository<DeviceFromadwords, String>{

}
