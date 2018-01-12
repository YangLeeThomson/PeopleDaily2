package com.ronglian.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the news_share database table.
 * 
 */
@Entity
@Table(name="news_share")
@NamedQuery(name="NewsShare.findAll", query="SELECT n FROM NewsShare n")
public class NewsShare implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="share_id")
	private String shareId;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="news_id")
	private String newsId;

	@Column(name="share_platform")
	private Integer sharePlatform;

	@Column(name="user_id")
	private String userId;
	
	@Column(name="device_id")
	private String deviceId;
	
	public NewsShare() {
	}

	public String getShareId() {
		return this.shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
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


	public Integer getSharePlatform() {
		return sharePlatform;
	}

	public void setSharePlatform(Integer sharePlatform) {
		this.sharePlatform = sharePlatform;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}