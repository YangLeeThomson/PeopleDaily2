package com.ronglian.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.ronglian.entity.NewsPicture;

public interface NewsPictureDao extends CrudRepository<NewsPicture, String> {
	
}
