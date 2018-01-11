package com.ronglian.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the news_comment database table.
 * 
 */
@Entity
@Table(name="news_comment")
@NamedQuery(name="NewsComment.findAll", query="SELECT n FROM NewsComment n")
public class NewsComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="comment_id")
	private String commentId;
	
	@Column(name="device_id")
	private String deviceId;


	@Column(name="comment_content")
	private String commentContent;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Temporal(TemporalType.DATE)
	@Column(name="modify_time")
	private Date modifyTime;

	@Column(name="news_id")
	private String newsId;

	@Column(name="nickname")
	private String nickName;

	public int getAppriseNum() {
		return appriseNum;
	}

	public void setAppriseNum(int appriseNum) {
		this.appriseNum = appriseNum;
	}

	//	×´Ì¬0£ºÎ´ÉóºË£¬1£ºÒÑÉóºË',3:É¾³ý
	@Column(name="status")
	private int status;
	
	@Column(name="apprise_num")
	private int appriseNum;

	@Column(name="user_id")
	private String userId;

	@Column(name="user_image")
	private String userImage;

	public NewsComment() {
	}

	public String getCommentId() {
		return this.commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getNewsId() {
		return this.newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserImage() {
		return this.userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

}