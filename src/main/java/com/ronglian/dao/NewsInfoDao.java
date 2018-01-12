/**
 * 
 */
package com.ronglian.dao;

import java.util.List;

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

	@Query(value="select news from news_info news where news.channel_id = : channelId limit (pageNo - 1)*pageSize,pageSize",nativeQuery= true)
	List<NewsInfo> selectNewsInfoByChannel(@Param(value = "pageSize") int pageSize, @Param(value = "pageNo") int pageNo, @Param("channelId")String channelId);

	@Query(value="select news from news_info news where news.channel_id = : channelId and news.is_to_top = 1",nativeQuery= true)
	List<NewsInfo> selectTopnewsByChannel(@Param("channelId") String channelId);
	
	@Query(value="select news from news_info news where news.channel_id = : channelId and is_edit_recom =1",nativeQuery= true)
	List<NewsInfo> selectEditorNewsByChannel(@Param("channelId") String channelId);

	@Query(value="select news from news_info news where news.news_id in (: list) limit (pageNo - 1)*pageSize,pageSize",nativeQuery= true)
	List<NewsInfo> selectTopicNewsByNewsInfoId(@Param("list") List<String> list,@Param(value = "pageSize") int pageSize, @Param(value = "pageNo") int pageNo);
	
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
}
