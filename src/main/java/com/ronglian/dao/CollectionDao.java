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

	@Query(value="select   * from news_collection c where c.user_id = ?1 order by create_time desc limit ?2,?3",nativeQuery= true)
	public List<Collection> selectCollectionListByUserIdSort(String userId,int start,int pageSize);
	
	@Query(value="select  * from news_collection c where c.device_id = ?1 and c.user_id is null order by create_time desc limit ?2,?3",nativeQuery= true)
	public List<Collection> selectCollectionListByDeviceIdSort(String deviceId,int start,int pageSize);
}
