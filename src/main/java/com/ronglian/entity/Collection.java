package com.ronglian.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the news_collection database table.
 * 
 */
@Entity
@Table(name="news_collection")
@NamedQuery(name="Collection.findAll", query="SELECT n FROM Collection n")
public class Collection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="collection_id")
	private String collectionId;

	@Column(name="create_time")
	private Date createTime;

	@Column(name="news_id")
	private String newsId;
	
	@Column(name="news_title")
	private String newsTitle;

	public String getNewsTitle() {
		return newsTitle;
	}



	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	@Column(name="user_id")
	private String userId;
	
	@Column(name="device_id")
	private String deviceId;
	
	public String getDeviceId() {
		return deviceId;
	}



	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}



	public Collection() {
	}



	public String getCollectionId() {
		return collectionId;
	}



	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}



	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNewsId() {
		return this.newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*
	 * @author 李楊
	 * @comment 新闻图片链接url
	 * @createTime 2018/3/5新增字段
	 * */
	@Column(name="img_url")
	private String imgUrl;

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}