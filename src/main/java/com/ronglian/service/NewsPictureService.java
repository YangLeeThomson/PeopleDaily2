package com.ronglian.service;

import java.util.List;

import com.ronglian.entity.NewsPicture;

public interface NewsPictureService {

	public List<NewsPicture> findPicture(String newsId);
}
