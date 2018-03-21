/**
 * 
 */
package com.ronglian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.UserChannelConfig;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
public interface UserChannelConfigDao extends CrudRepository<UserChannelConfig, Integer> {

	@Query("select config from UserChannelConfig config where config.deviceId = :deviceId ")
	public List< UserChannelConfig> selectUserChannelConfigByDeviceId(@Param("deviceId")String deviceId);

	@Query("select config from UserChannelConfig config where config.userId = :userId")
	public List< UserChannelConfig> selectUserChannelConfigByUserId(@Param("userId") String userId);

	
}
