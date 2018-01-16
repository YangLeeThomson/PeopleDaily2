package com.ronglian.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.NewsPicture;

public interface NewsPictureDao extends CrudRepository<NewsPicture, String> {
	@Transactional
    @Modifying
    @Query("delete from NewsPicture picture where picture.newsId = :newsId")
	int deleteByNewsID(@Param("newsId") String newsId);
}
