package com.ronglian.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the news_info_dislike database table.
 * 
 */
@Entity
@Table(name="news_info_dislike")
@NamedQuery(name="NewsInfoDislike.findAll", query="SELECT n FROM NewsInfoDislike n")
public class NewsInfoDislike implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="device_id")
	private String deviceId;

	@Column(name="news_id")
	private String newsId;

	@Column(name="user_id")
	private String userId;

	public NewsInfoDislike() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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