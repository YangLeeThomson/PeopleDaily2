/**
 * 
 */
package com.ronglian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ronglian.entity.AppStartPicture;
import com.ronglian.entity.NewsInfo;

/**
 * @author liyang
 * @createTime 2017年12月28日
 */
public interface AppStartPictureDao extends CrudRepository<AppStartPicture, String> {

	@Query(value="select * from app_start_picture app where app.data_status = 0",nativeQuery=true)
	List<AppStartPicture> selectAppStartPicture();
}
