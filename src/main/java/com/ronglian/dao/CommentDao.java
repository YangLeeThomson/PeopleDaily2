/**
 * 
 */
package com.ronglian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.NewsComment;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ29ÈÕ
 */
public interface CommentDao extends CrudRepository<NewsComment, String>{

	@Query("from NewsComment comment where comment.deviceId = :deviceId")
	public List<NewsComment> getCommentsByDeviceId(@Param("deviceId") String deviceId);

	@Query("from NewsComment comment where comment.userId = :userId")
	public  List<NewsComment> getCommentsByUserId(@Param("userId") String userId);
	
	@Query("from NewsComment comment where comment.newsId = :newsId and comment.userId = :userId")
	public  List<NewsComment> getUserCommentList(@Param("newsId") String newsId,@Param("userId") String userId);

	@Modifying
	@Query("update NewsComment  set  appriseNum = appriseNum + 1  where commentId = :commentId")
	public void updateNewsCommentById(@Param("commentId")String commentId);
}
