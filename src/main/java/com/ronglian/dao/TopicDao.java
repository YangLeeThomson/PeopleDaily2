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
import com.ronglian.entity.NewsTopic;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
public interface TopicDao  extends CrudRepository<NewsTopic, Integer>{
	@Transactional
    @Modifying
    @Query("delete from NewsTopic topic where topic.uniqueId in ( :list)")
	int deleteByTopicUniqueID(@Param("list") List<String> list); 
	
	@Query(value="select * from news_topic where  unique_id = ?1",nativeQuery=true)
	public NewsTopic getNewsTopicByTopicId(String topicId);
}
