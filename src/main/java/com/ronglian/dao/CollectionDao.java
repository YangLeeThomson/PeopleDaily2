/**
 * 
 */
package com.ronglian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.Collection;

/**
 * @author liyang
 * @createTime 2018月12月28日
 */
public interface CollectionDao extends CrudRepository<Collection, String> {

	@Query("select  c from Collection c where c.userId = :userId and c.newsId = :newsId")
	public Collection selectCollectionByUserId(@Param("userId")String userId,@Param("newsId")String newsId);

	@Query("select  c from Collection c where c.deviceId = :deviceId and c.newsId = :newsId")
	public Collection selectCollectionByDeviceId(@Param("deviceId")String deviceId,@Param("newsId")String newsId);

//	@Query("select  c from Collection c where c.userId = :userId")
//	public List<Collection> selectCollectionListByUserId(@Param("userId")String userId);
//	
//	@Query("select  c from Collection c where c.deviceId = :deviceId and c.userId is null")
//	public List<Collection> selectCollectionListByDeviceId(@Param("deviceId")String deviceId);

	@Query("select  c from Collection c where c.userId = :userId order by newsId")
	public List<Collection> selectCollectionListByUserIdSort(@Param("userId")String userId);
	
	@Query("select  c from Collection c where c.deviceId = :deviceId and c.userId is null order by newsId")
	public List<Collection> selectCollectionListByDeviceIdSort(@Param("deviceId")String deviceId);
}
