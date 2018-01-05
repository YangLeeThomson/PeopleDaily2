package com.ronglian.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.APPManagerConfig;
/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ22ÈÕ
 */
public interface APPManagerConfigDao extends CrudRepository<APPManagerConfig, Integer> {

	@Query("select a from APPManagerConfig a where a.appId = :appId")
	APPManagerConfig selectAPPManagerConfigByAppId(@Param("appId")String appId);
}
