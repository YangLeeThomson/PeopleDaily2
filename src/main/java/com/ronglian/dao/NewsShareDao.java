/**
 * 
 */
package com.ronglian.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.NewsShare;

/**
 * @author liyang
 * @createTime 2018Äê1ÔÂ12ÈÕ
 */
public interface NewsShareDao extends CrudRepository<NewsShare, String> {

	@Query("select share from NewsShare share where userId = :userId and deviceId = :deviceId and newsId = :newsId and sharePlatform = :sharePlatform")
	NewsShare getNewsShare(@Param("userId")String userId,@Param("deviceId")String deviceId,@Param("newsId")String newsId,@Param("sharePlatform")Integer sharePlatform); 
}
