/**
 * 
 */
package com.ronglian.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.NewsInfo;

/**
 * @author liyang
 * @createTime 2017年12月27
 */
public interface NewsInfoDao extends CrudRepository<NewsInfo, String> {
	@Query(value="select * from news_info news where news.data_status = 2 and news_id = ?1 ",nativeQuery=true)
	NewsInfo selectNewsInfo(String id);

	@Query(value="select * from news_info news where news.data_status = 2 and is_topnews = 1 and is_topnews_totop = 1 and now() < edit_expire order by topnews_sort desc,publish_time desc",nativeQuery=true)
	List<NewsInfo> selectTopnewsAhead();
	
	@Query(value="select * from news_info news where news.data_status = 2 and news.channel_unique_id = ?1 and is_to_top = 0 and news.news_id not in (select topic.news_id from topic_and_news topic)order by publish_time desc limit ?2,?3",nativeQuery= true)
	List<NewsInfo> selectNewsInfoByChannel(String channelUniqueId,int pageNo,int pageSize);
	
	@Query(value="select * from news_info news where news.data_status = 2 and news.channel_unique_id = ?1 and publish_time < ?4 and is_to_top = 0 and news.news_id not in (select topic.news_id from topic_and_news topic) order by publish_time desc limit ?2,?3",nativeQuery= true)
	List<NewsInfo> selectNewsInfoByChannelAndNewsId(String channelUniqueId,int pageNo,int pageSize,Date incNo);
	
	@Query(value="select * from news_info news where news.data_status = 2 and news.news_id in ?1 ",nativeQuery= true)
	List<NewsInfo> selectPageInfo(List<String> newsIdList);
	
	@Query(value="select * from news_info news where news.data_status = 2 and news.news_id in ?1 ",nativeQuery= true)
	List<NewsInfo> selectNewsIdList(List<String> newsIdList);
	
	@Query(value="select count(*) from news_info news where news.channel_unique_id = ?1  and news.is_to_top = 0 ",nativeQuery= true)
	int countNewsInfoByChannel(String channelUniqueId);
	
	@Query(value="select * from news_info news where news.channel_unique_id = :channelUniqueId and news.is_to_top = 1 and news.data_status = 2 and news.news_id not in (select topic.news_id from topic_and_news topic) order by news_sort desc,publish_time desc",nativeQuery= true)
	List<NewsInfo> selectTopnewsByChannel(@Param("channelUniqueId") String channelUniqueId);
	
	@Query(value="select * from news_info news where news.is_edit_recom = 1 and news.is_topnews = 1 and news.data_status = 2 and now() < news.edit_expire  order by topnews_sort desc",nativeQuery= true)
	List<NewsInfo> selectEditorNewsByChannel();

	@Query(value="select news.* from news_info news,topic_and_news topic where news.data_status = 2 and topic.topic_uniqueID = ?1 and topic.news_id = news.news_id order by publish_time DESC limit ?2,?3",nativeQuery= true)
	List<NewsInfo> selectTopicNewsByNewsInfoId( String topicId, int pageNo, int pageSize );
	
	/**
	 * 新增   api/1.1/getTopicNews
	 * @createTime 2018年5月24
	 * @param topicId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Query(value="select news.* from news_info news,topic_and_news topic where news.data_status = 2 and  news.is_to_top = 0 and topic.topic_uniqueID = ?1 and topic.news_id = news.news_id order by publish_time DESC limit ?2,?3",nativeQuery= true)
	List<NewsInfo> selectTopicNewsByNewsInfoId2( String topicId, int pageNo, int pageSize );
	/**
	 * 新增专题置顶新闻查询
	 * @createTime 2018年5月24
	 * @param topicId
	 * @return
	 */
	@Query(value="select news.* from news_info news,topic_and_news topic where news.data_status = 2 and news.is_to_top =1 and topic.topic_uniqueID = ?1 and topic.news_id = news.news_id order by news_sort desc,publish_time DESC",nativeQuery= true)
	List<NewsInfo> selectTopicNewsToTopBytopicId(String topicId);
	
	
	@Query(value="select news.* from news_info news,topic_and_news topic where news.data_status = 2 and topic.topic_uniqueID = ?1 and topic.news_id = news.news_id and publish_time < ?4 order by publish_time DESC limit ?2,?3",nativeQuery= true)
	List<NewsInfo> selectTopicNewsByNewsId( String topicId, int pageNo, int pageSize ,Date publishTime);
	/**
	 * 新增   api/1.1/getTopicNews
	 * @createTime 2018年5月24
	 * @param topicId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Query(value="select news.* from news_info news,topic_and_news topic where news.data_status = 2 and  news.is_to_top=0 and topic.topic_uniqueID = ?1 and topic.news_id = news.news_id and publish_time < ?4 order by publish_time DESC limit ?2,?3",nativeQuery= true)
	List<NewsInfo> selectTopicNewsByNewsId2( String topicId, int pageNo, int pageSize ,Date publishTime);
	
	@Query(value="select count(*) from news_info news where news.topic_id = ?1 ",nativeQuery= true)
	int countTopicNewsByNewsInfoId( String topicId );
	@Transactional
	@Modifying
	@Query(value="update news_info  set apprise_up_num = apprise_up_num + 1 where news_Id = :newsId",nativeQuery= true)
	void updateAppriseUpNum(@Param("newsId")String newsId);
	@Transactional
	@Modifying
	@Query(value="update news_info  set apprise_down_num = apprise_down_num + 1 where news_Id = :newsId",nativeQuery= true)
	void updateAppriseDownNum(@Param("newsId")String newsId);
	
	@Transactional
	@Modifying                          
	@Query(value="update news_info  set collect_num = collect_num + 1 where news_Id = ?1",nativeQuery= true)
	void updateCollectNum(String newsId);
	
	@Transactional
	@Modifying
	@Query(value="update news_info  set access_num = access_num + 1 where news_Id = ?1",nativeQuery= true)
	void updateReadNum(String newsId);
	
	@Transactional
	@Modifying
	@Query(value="update news_info  set access_num = ?2 where news_Id = ?1",nativeQuery= true)
	void updateReadNumByAccessNum(String newsId,int num);
	
	@Transactional
	@Modifying
	@Query(value="update news_info  set apprise_up_num = apprise_up_num - 1 where news_Id = :newsId",nativeQuery= true)
	void deleteAppriseUpNum(@Param("newsId")String newsId);
	
	@Transactional
	@Modifying
	@Query(value="update news_info  set apprise_down_num = apprise_down_num - 1 where news_Id = :newsId",nativeQuery= true)
	void deleteAppriseDownNum(@Param("newsId")String newsId);
	
	@Modifying
	@Query(value="update news_info  set share_num = share_num + 1 where news_Id = :newsId",nativeQuery= true)
	void updateShareNum(@Param("newsId")String newsId);
	

//	@Query("from NewsInfo where contentId in (:list)")
//	List<NewsInfo> selectNewsInfoNearByIncNo(@Param("list")List<Integer> list);

	@Query(value="select * from news_info where channel_unique_id = ?2 and content_id < ?1 and data_status = 2 and data_mode = 5 order by content_id DESC limit 2",nativeQuery= true)
	List<NewsInfo> selectNewsInfoNearUpByIncNo(int incNo,String chanelUniqueId);
	
	@Query(value="select * from news_info where channel_unique_id = ?2 and content_id > ?1 and data_status = 2 and data_mode = 5 order by content_id DESC limit 2",nativeQuery= true)
	List<NewsInfo> selectNewsInfoNearDownByIncNo(int incNo,String chanelUniqueId);
	
	@Transactional
    @Modifying
    @Query("delete from NewsInfo news where news.newsId in ( :list)")
	int deleteByNewsID(@Param("list") List<String> list); 
	
    @Query(value="select * from news_info news where news.news_id in ( :list) order by news_sort desc,publish_time desc",nativeQuery = true)
    List<NewsInfo>  selectByNewsID(@Param("list") List<String> list); 
	
	@Transactional
    @Modifying
    @Query(value="update news_info  set comment_num = comment_num + 1 where news_Id = :newsId",nativeQuery= true)
	void updateCommentNum(@Param("newsId")String newsId);
	
	@Query(value="select * from news_info news where news.news_Id = (:newsId)",nativeQuery= true)
	NewsInfo selectByNewsId(@Param("newsId")String newsId);
}
