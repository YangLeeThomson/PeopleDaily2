/**
 * 
 */
package com.ronglian.dao;

import java.util.List;

import javax.persistence.Column;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.TopicAndNews;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
public interface TopicNewsDao extends CrudRepository<TopicAndNews, Integer> {

    @Transactional
    @Modifying
    @Query("delete TopicAndNews where topicUniqueID in ( :list)")
	int deleteByTopicUniqueID(@Param("list") List<String> list); 
//	 private int topicUniqueID ;
//	 private String newsID;

    @Transactional
    @Modifying
    @Query("delete TopicAndNews where newsID in ( :list)")
	int deleteByNewsID(@Param("list") List<String> list);

	/**
	 * @param topicId
	 * @return
	 */
    @Query("select news.newsID from TopicAndNews news where news.topicUniqueID = :topicId")
	List<String> selectNewsInfoIdByTopic(@Param("topicId")String topicId); 
}
