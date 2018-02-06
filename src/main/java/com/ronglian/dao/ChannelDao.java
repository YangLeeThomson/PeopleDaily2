/**
 * 
 */
package com.ronglian.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.Channel;
import com.ronglian.entity.User;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
public interface ChannelDao extends CrudRepository<Channel, Integer>{

	 @Query("select u from Channel u ")
	    List<Channel> getList();
	 
	 @Query(value="select c.channel_name from news_channel c where c.unique_id = ?1",nativeQuery= true)
	 String selectChannelByUniqueId(String channelUniqueId);
}
