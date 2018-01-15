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
import com.ronglian.entity.TopicNewsKey;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
public interface TopicNewsDao extends CrudRepository<TopicAndNews, TopicNewsKey> {

    @Transactional
    @Modifying
    @Query("delete from TopicAndNews news where news.topicNewsKey.topicUniqueID in ( :list)")
	int deleteByTopicUniqueID(@Param("list") List<String> list); 

    @Transactional
    @Modifying
    @Query("delete from TopicAndNews news where  news.topicNewsKey.newsID in ( :list)")
	int deleteByNewsID(@Param("list") List<String> list);

    @Query("select news.topicNewsKey.newsID from TopicAndNews news where news.topicNewsKey.topicUniqueID = :topicId")
	List<String> selectNewsInfoIdByTopic(@Param("topicId")String topicId); 
    
    @Query("select news.topicNewsKey.topicUniqueID from TopicAndNews news where news.topicNewsKey.newsID = :newsID")
	List<String> selectTopicByNewsInfoId(@Param("newsID")String newsID);
}
