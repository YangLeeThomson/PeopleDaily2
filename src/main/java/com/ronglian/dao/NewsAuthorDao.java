package com.ronglian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ronglian.entity.NewsAuthor;

public interface NewsAuthorDao extends CrudRepository<NewsAuthor, String>{

	@Query("select author from NewsAuthor author where newsId = :newsId")
	public List<NewsAuthor> selectByNewsId(@Param("newsId")String newsId);
	
	@Modifying
	@Transactional
	@Query(value="delete from news_author  where news_id = :newsId",nativeQuery= true)
	public void deleteByNewsId(@Param("newsId")String newsId);

}
