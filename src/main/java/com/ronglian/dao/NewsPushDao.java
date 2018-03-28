package com.ronglian.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ronglian.entity.NewsComment;
import com.ronglian.entity.NewsPush;

public interface NewsPushDao extends CrudRepository<NewsPush, String>{

	@Query(value="select * from news_push order by create_time desc limit ?1,?2",nativeQuery= true)
	public  List<NewsPush> selectPushList(Integer start, Integer pageSize);
	
}
