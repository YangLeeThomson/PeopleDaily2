package com.ronglian.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="news_push")
@NamedQuery(name="NewsPush.findAll", query="SELECT n FROM NewsPush n")
public class NewsPush implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="push_id")
	private String pushId;

	@Column(name="news_id")
	private String newsId;

	@Column(name="push_title")
	private String pushTitle;

	@Column(name="create_time")
	private Date createTime;
	
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

	public Byte getIsLive() {
		return isLive;
	}

	public void setIsLive(Byte isLive) {
		this.isLive = isLive;
	}

	public Byte getIsLiveReplay() {
		return isLiveReplay;
	}

	public void setIsLiveReplay(Byte isLiveReplay) {
		this.isLiveReplay = isLiveReplay;
	}

	public Byte getIsRead() {
		return isRead;
	}

	public void setIsRead(Byte isRead) {
		this.isRead = isRead;
	}

	@Column(name="publish_time")
	private Date publishTime;

	@Column(name="has_video")
	private Byte hasVideo;

	@Column(name="data_mode")
	private Byte dataMode;
	
	@Column(name="is_live")
	private Byte isLive;
	
	@Column(name="is_live_replay")
	private Byte isLiveReplay;
	
	@Column(name="is_topic")
	private Integer isTopic;
	
	@Column(name="news_title")
	private String newsTitle;
	
	@Column(name="topic_unique_id")
	private String topicUniqueId;
	
	@Column(name="is_read")
	private Byte isRead ;
	
	

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}


	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getPushTitle() {
		return pushTitle;
	}

	public void setPushTitle(String pushTitle) {
		this.pushTitle = pushTitle;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Integer getIsTopic() {
		return isTopic;
	}

	public void setIsTopic(Integer isTopic) {
		this.isTopic = isTopic;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getTopicUniqueId() {
		return topicUniqueId;
	}

	public void setTopicUniqueId(String topicUniqueId) {
		this.topicUniqueId = topicUniqueId;
	}

}
