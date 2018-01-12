package com.ronglian.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the news_info_apprise database table.
 * 
 */
@Entity
@Table(name="news_info_apprise")
@NamedQuery(name="NewsInfoApprise.findAll", query="SELECT n FROM NewsInfoApprise n")
public class NewsInfoApprise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="apprise_id")
	private String appriseId;

	@Column(name="device_id")
	private String deviceId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="good")
	private Integer good;

	@Column(name="news_id")
	private String newsId;

	@Column(name="user_id")
	private String userId;

	public NewsInfoApprise() {
	}

	public String getAppriseId() {
		return this.appriseId;
	}

	public void setAppriseId(String appriseId) {
		this.appriseId = appriseId;
	}


	public Date getCreateTime() {
		return this.createTime;
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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	public Integer getGood() {
		return good;
	}

	public void setGood(Integer good) {
		this.good = good;
	}

}