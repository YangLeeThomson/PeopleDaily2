package com.ronglian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.NewsPictureDao;
import com.ronglian.entity.NewsPicture;
import com.ronglian.service.NewsPictureService;

@Service
public class NewsPictureServiceImpl implements NewsPictureService {

	@Autowired
	private NewsPictureDao pictureDao;
	@Override
	public List<NewsPicture> findPicture(String newsId) {
		// TODO Auto-generated method stub
		return this.pictureDao.selectNewsPictureByNewsId(newsId);
	}

}
