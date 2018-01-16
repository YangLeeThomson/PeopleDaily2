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

	@Query(value="select * from news_info news where news.channel_id = ?1 limit ?2,?3",nativeQuery= true)
	List<NewsInfo> selectNewsInfoByChannel(String channelId,int pageNo,int pageSize);

	@Query(value="select * from news_info news where news.channel_id = :channelId and news.is_to_top = 1",nativeQuery= true)
	List<NewsInfo> selectTopnewsByChannel(@Param("channelId") String channelId);
	
	@Query(value="select * from news_info news where news.channel_id = :channelId and is_edit_recom = 1",nativeQuery= true)
	List<NewsInfo> selectEditorNewsByChannel(@Param("channelId") String channelId);

	@Query(value="select * from news_info news where news.news_id in (?1) limit ?2,?3",nativeQuery= true)
	List<NewsInfo> selectTopicNewsByNewsInfoId( List<String> list, int pageNo, int pageSize );
	
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
	

	@Query("from NewsInfo where contentId in (:list)")
	List<NewsInfo> selectNewsInfoNearByIncNo(@Param("list")List<Integer> list);

	@Transactional
    @Modifying
    @Query("delete from NewsInfo news where news.newsId in ( :list)")
	int deleteByNewsID(@Param("list") List<String> list); 
}
