/**
 * 
 */
package com.ronglian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.NewsSlideshow;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ28ÈÕ
 */
public interface SlideShowDao extends CrudRepository<NewsSlideshow, Integer> {

	@Query("select slide from NewsSlideshow slide where slide.channelId = :channelId ")
	public List<NewsSlideshow> selectSlideShowByChannel(@Param("channelId") String channelId);
}
