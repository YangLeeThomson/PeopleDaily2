package com.ronglian.entity;

import java.io.Serializable;
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
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="slideshow_id")
	private String slideshowId;

	@Column(name="create_time")
	private String createTime;

	@Column(name="news_id")
	private String newsId;

	@Column(name="user_id")
	private String userId;

	public Collection() {
	}

	public String getSlideshowId() {
		return this.slideshowId;
	}

	public void setSlideshowId(String slideshowId) {
		this.slideshowId = slideshowId;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
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

}