/**
 * 
 */
package com.ronglian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.NewsInfoApprise;

/**
 * @author liyang
 * @createTime 2018年12月11日
 */
public interface AppriseDao extends CrudRepository<NewsInfoApprise, String> {

	/**
	 * @param deviceId
	 * @param userId
	 * @param newsId
	 * @return
	 */
	@Query("select  apprise from NewsInfoApprise apprise where deviceId = :deviceId and userId = :userId and newsId = :newsId")
	public NewsInfoApprise getNewsInfoApprise(@Param("deviceId")String deviceId, @Param("userId")String userId,@Param("newsId") String newsId);
	
	@Query("select  apprise from NewsInfoApprise apprise where deviceId = :deviceId and good = :good and newsId = :newsId")
	public List<NewsInfoApprise> selectIsApprised(@Param("deviceId")String deviceId, @Param("good")Integer good,@Param("newsId") String newsId);

	@Query("select  apprise from NewsInfoApprise apprise where deviceId = :deviceId and userId = :userId")
	public List<NewsInfoApprise> selectAppriseList(@Param("deviceId")String deviceId,@Param("userId")String userId);
}
