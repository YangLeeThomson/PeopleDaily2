/**
 * 
 */
package com.ronglian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.NewsComment;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ29ÈÕ
 */
public interface CommentDao extends CrudRepository<NewsComment, String>{

	@Query("from NewsComment comment where comment.deviceId = :deviceId and comment.newsId = :newsId")
	public List<NewsComment> getComments(@Param("deviceId") String deviceId,@Param("newsId") String newsId);

	@Query("from NewsComment comment where comment.newsId = :newsId")
	public  List<NewsComment> getAllComments(@Param("newsId") String newsId);
	
	@Query("from NewsComment comment where comment.newsId = :newsId and comment.userId = :userId")
	public  List<NewsComment> getUserCommentList(@Param("newsId") String newsId,@Param("userId") String userId);
}
