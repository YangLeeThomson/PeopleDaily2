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

import com.ronglian.entity.NewsInfo;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
public interface NewsInfoDao extends CrudRepository<NewsInfo, String> {

	@Query(value="select * from news_info  where channel_id = :channelId limit :startIndex,:endIndex",nativeQuery= true)
	List<NewsInfo> selectNewsInfoByChannel( @Param("channelId")String channelId,@Param(value = "startIndex") int startIndex, @Param(value = "endIndex") int endIndex);

	@Query(value="select * from news_info news where news.channel_id = :channelId and news.is_to_top = 1",nativeQuery= true)
	List<NewsInfo> selectTopnewsByChannel(@Param("channelId") String channelId);
	
	@Query(value="select * from news_info news where news.channel_id = :channelId and is_edit_recom =1",nativeQuery= true)
	List<NewsInfo> selectEditorNewsByChannel(@Param("channelId") String channelId);

	@Query(value="select news.* from news_info news,topic_and_news relation where news.news_id=relation.news_id and relation.topic_uniqueID=:topicId limit :startIndex,:endIndex",nativeQuery= true)
	List<NewsInfo> selectTopicNewsByNewsInfoId(@Param("topicId") String topicId,@Param(value = "startIndex") int startIndex, @Param(value = "endIndex") int endIndex);
	
	@Modifying
	@Query(value="update news_info  set apprise_up_num = apprise_up_num + 1 where news_Id = :newsId",nativeQuery= true)
	void updateAppriseUpNum(@Param("newsId")String newsId);
	@Modifying
	@Query(value="update news_info  set apprise_down_num = apprise_down_num + 1 where news_Id = :newsId",nativeQuery= true)
	void updateAppriseDownNum(@Param("newsId")String newsId);
	
	@Modifying
	@Query(value="update news_info  set apprise_up_num = apprise_up_num - 1 where news_Id = :newsId",nativeQuery= true)
	void deleteAppriseUpNum(@Param("newsId")String newsId);
	@Modifying
	@Query(value="update news_info  set apprise_down_num = apprise_down_num - 1 where news_Id = :newsId",nativeQuery= true)
	void deleteAppriseDownNum(@Param("newsId")String newsId);
	
	@Modifying
	@Query(value="update news_info  set share_num = share_num + 1 where news_Id = :newsId",nativeQuery= true)
	void updateShareNum(@Param("newsId")String newsId);
	
	@Transactional
    @Modifying
    @Query("delete from NewsInfo news where news.newsId in ( :list)")
	int deleteByNewsID(@Param("list") List<String> list); 
}
