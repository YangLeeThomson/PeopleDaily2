/**
 * 
 */
package com.ronglian.dao;

import org.springframework.data.repository.CrudRepository;

import com.ronglian.entity.Channel;
import com.ronglian.entity.NewsTopic;

/**
 * @author liyang
 * @createTime 2017��12��27��
 */
public interface TopicDao  extends CrudRepository<NewsTopic, Integer>{

}
