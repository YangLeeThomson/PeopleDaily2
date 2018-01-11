package com.ronglian.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the news_comment_apprise database table.
 * 
 */
@Entity
@Table(name="news_comment_apprise")
@NamedQuery(name="NewsCommentApprise.findAll", query="SELECT n FROM NewsCommentApprise n")
public class NewsCommentApprise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="apprise_id")
	private String appriseId;

	@Column(name="comment_id")
	private String commentId;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="device_id")
	private String deviceId;

	@Column(name="user_id")
	private String userId;

	public NewsCommentApprise() {
	}

	public String getAppriseId() {
		return this.appriseId;
	}

	public void setAppriseId(String appriseId) {
		this.appriseId = appriseId;
	}

	public String getCommentId() {
		return this.commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeviceId() {
		return this.deviceId;
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