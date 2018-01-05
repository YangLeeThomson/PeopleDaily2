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
 * @createTime 2017Äê12ÔÂ29ÈÕ
 */
public interface UserChannelConfigDao extends CrudRepository<UserChannelConfig, String> {

	@Query("select config from UserChannelConfig config where config.deviceId = :deviceId and config.userId = :userId")
	public List< UserChannelConfig> selectUserChannelConfig(@Param("deviceId")String deviceId,@Param("userId") String userId);
}
