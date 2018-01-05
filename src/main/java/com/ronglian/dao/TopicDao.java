/**
 * 
 */
package com.ronglian.dao;

import org.springframework.data.repository.CrudRepository;

import com.ronglian.entity.Channel;
import com.ronglian.entity.NewsTopic;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
public interface TopicDao  extends CrudRepository<NewsTopic, Integer>{

}
