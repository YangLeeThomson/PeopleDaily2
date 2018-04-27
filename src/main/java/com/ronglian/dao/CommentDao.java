/**
 * 
 */
package com.ronglian.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.NewsComment;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
public interface CommentDao extends CrudRepository<NewsComment, String>{

//	@Query("from NewsComment comment where comment.deviceId = :deviceId order by newsId")
//	public List<NewsComment> getCommentsByDeviceId(@Param("deviceId") String deviceId);
	@Query("from NewsComment comment where comment.deviceId = :deviceId order by createTime")
	public List<NewsComment> getCommentsByDeviceId(@Param("deviceId") String deviceId);
	
	@Query("select newsId from NewsComment comment where comment.deviceId = :deviceId order by newsId")
	public List<String> getNewsIdListByDeviceId(@Param("deviceId") String deviceId);
	
	@Query("select newsId from NewsComment comment where comment.userId = :userId order by newsId")
	public List<String> getNewsIdListByUserId(@Param("userId") String userId);

//	@Query("from NewsComment comment where comment.userId = :userId order by newsId")
//	public  List<NewsComment> getCommentsByUserId(@Param("userId") String userId);
	@Query("from NewsComment comment where comment.userId = :userId order by createTime")
	public  List<NewsComment> getCommentsByUserId(@Param("userId") String userId);
	
	@Query(value="select * from news_comment comment where ((comment.news_id = :newsId and comment.status = 1) or (comment.news_id = :newsId and comment.user_id = :userId and comment.status = 0))order by create_time desc",nativeQuery= true)
	public  List<NewsComment> getUserCommentListByUserId(@Param("newsId") String newsId,@Param("userId") String userId);

	@Query(value="select * from news_comment comment where ((comment.news_id = ?1 and comment.status = 1) or (comment.news_id = ?1 and comment.user_id = ?2 and comment.status = 0))order by create_time desc limit ?3,?4",nativeQuery= true)
	public  List<NewsComment> getUserCommentListByUserIdLimt(String newsId,String userId,int start,int pageSize);
	
	@Query(value="select * from news_comment comment where (((comment.news_id = ?1 and comment.status = 1) or (comment.news_id = ?1 and comment.user_id = ?2 and comment.status = 0))) and create_time < ?5 order by create_time desc limit ?3,?4",nativeQuery= true)
	public  List<NewsComment> getUserCommentListByUserIdLimtSecond(String newsId,String userId,int start,int pageSize,Date createTime);

	@Query("from NewsComment comment where ((comment.newsId = :newsId and comment.status = 1) or (comment.newsId = :newsId and comment.deviceId = :deviceId and comment.status = 0)) order by create_time desc")
	public  List<NewsComment> getUserCommentListByDeviceId(@Param("newsId") String newsId,@Param("deviceId") String deviceId);

	@Query(value="select * from news_comment comment where ((comment.news_id = ?1 and comment.status = 1) or (comment.news_id = ?1 and comment.device_id = ?2 and comment.status = 0)) order by create_time desc limit ?3,?4",nativeQuery= true)
	public  List<NewsComment> getUserCommentListByDeviceIdLimt(String newsId,String deviceId,int start,int pageSize);
	
	@Query(value="select * from news_comment comment where (((comment.news_id = ?1 and comment.status = 1) or (comment.news_id = ?1 and comment.device_id = ?2 and comment.status = 0)))  and create_time < ?5 order by create_time desc limit ?3,?4",nativeQuery= true)
	public  List<NewsComment> getUserCommentListByDeviceIdLimtSecond(String newsId,String deviceId,int start,int pageSize,Date createTime);

	@Query(value="select * from news_comment comment where ((comment.news_id = ?1 and comment.status = 1) or (comment.news_id = ?1 and comment.user_id = ?2 and comment.status = 0))order by apprise_num  desc ,create_time desc limit ?3,?4",nativeQuery= true)
	public  List<NewsComment> getCommentListByUserIdAndAppriseNum(String newsId,String deviceId,int start,int pageSize);
	
	@Query(value="select * from news_comment comment where (((comment.news_id = ?1 and comment.status = 1) or (comment.news_id = ?1 and comment.user_id = ?2 and comment.status = 0)))  and create_time < ?5 order by apprise_num  desc ,create_time desc limit ?3,?4",nativeQuery= true)
	public  List<NewsComment> getCommentListByUserIdAndAppriseNumSecond(String newsId,String deviceId,int start,int pageSize,Date createTime);
	
	@Query(value="select * from news_comment comment where ((comment.news_id = ?1 and comment.status = 1) or (comment.news_id = ?1 and comment.device_id = ?2 and comment.status = 0)) order by apprise_num desc ,create_time desc limit ?3,?4",nativeQuery= true)
	public  List<NewsComment> getCommentListByDeviceIdAndAppriseNum(String newsId,String deviceId,int start,int pageSize);
	
	@Query(value="select * from news_comment comment where (((comment.news_id = ?1 and comment.status = 1) or (comment.news_id = ?1 and comment.device_id = ?2 and comment.status = 0))) and create_time < ?5  order by apprise_num desc ,create_time desc limit ?3,?4",nativeQuery= true)
	public  List<NewsComment> getCommentListByDeviceIdAndAppriseNumSecond(String newsId,String deviceId,int start,int pageSize,Date createTime);
	
	@Modifying
	@Query("update NewsComment  set  appriseNum = appriseNum + 1  where commentId = :commentId")
	public void updateNewsCommentById(@Param("commentId")String commentId);
	
	@Modifying
	@Query("update NewsComment  set  status = :status  where commentId = :commentId")
	public void updateStatusById(@Param("status")int status,@Param("commentId")String commentId);
	
	@Query(value="select * from news_comment where status = ?1 and news_title like %?2% limit ?3,?4",nativeQuery= true)
	public List<NewsComment> selectCommentList(int status,String newsTitle,int start,int pageSize);

	@Query(value="select * from news_comment where 1 = 1 limit ?1,?2",nativeQuery= true)
	public List<NewsComment> selectCommentListAll(int start,int pageSize);
	
	@Query(value="select * from news_comment where 1 = 1 and news_title like %?1% limit ?2,?3",nativeQuery= true)
	public List<NewsComment> selectCommentListByNewsTitle(String newsTitle,int start,int pageSize);

	@Query(value="select * from news_comment where 1 = 1 and status = ?1 limit ?2,?3",nativeQuery= true)
	public List<NewsComment> selectCommentListByStatus(int status,int start,int pageSize);

	@Query(value="select count(*) from news_comment where 1 = 1 and news_title like %?1%",nativeQuery= true)
	public int countComment(String newsTitle);
	
	@Query(value="select count(*) from news_comment ",nativeQuery= true)
	public int countCommentAll();
	
	@Query(value="select count(*) from news_comment where status = ?1 and news_title like %?2%",nativeQuery= true)
	public int countCommentByOthers(int status,String newsTitle);
	
	@Query(value="select count(*) from news_comment where status = ?1",nativeQuery= true)
	public int countCommentByStatus(int status);
}
