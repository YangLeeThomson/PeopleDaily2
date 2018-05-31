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
 * @createTime 2017年12月28日
 */
public interface SlideShowDao extends CrudRepository<NewsSlideshow, String> {

	@Query(value="select * from news_slideshow  slide where slide.channel_unique_id = :channelUniqueId and slide.data_status = 2 order by sort desc,create_time desc limit 0,5",nativeQuery=true)
	public List<NewsSlideshow> selectSlideShowByChannel(@Param("channelUniqueId") String channelUniqueId);
}
