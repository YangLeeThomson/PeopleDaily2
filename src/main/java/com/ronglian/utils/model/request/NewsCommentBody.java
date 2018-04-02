package com.ronglian.utils.model.request;

import java.util.Date;

import javax.persistence.Column;

public class NewsCommentBody {

	private String commentId;
	private String deviceId;
	private String newsTitle;
	private String commentContent;
	private Date createTime;
	private Date modifyTime;
	private String newsId;
	private String nickname;
	private Integer status;
	private Integer appriseNum;
	private String userId;
	private String userImage;

	private Byte hasVideo;
	private Byte dataMode;
	private String chanelUniqueId;
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAppriseNum() {
		return appriseNum;
	}
	public void setAppriseNum(Integer appriseNum) {
		this.appriseNum = appriseNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public Byte getHasVideo() {
		return hasVideo;
	}
	public void setHasVideo(Byte hasVideo) {
		this.hasVideo = hasVideo;
	}
	public Byte getDataMode() {
		return dataMode;
	}
	public void setDataMode(Byte dataMode) {
		this.dataMode = dataMode;
	}
	public String getChanelUniqueId() {
		return chanelUniqueId;
	}
	public void setChanelUniqueId(String chanelUniqueId) {
		this.chanelUniqueId = chanelUniqueId;
	}
	

}
