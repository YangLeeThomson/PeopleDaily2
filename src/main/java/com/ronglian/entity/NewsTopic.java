package com.ronglian.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the news_topic database table.
 * 
 */
@Entity
@Table(name="news_topic")
@NamedQuery(name="NewsTopic.findAll", query="SELECT n FROM NewsTopic n")
public class NewsTopic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="topic_id")
	private Integer topicId;

	@Column(name="banner_image")
	private String bannerImage;

	@Column(name="channel_id")
	private String channelId;

	@Column(name="create_time")
	private Date createTime;

	@Column(name="list_image")
	private String listImage;

	@Column(name="modify_time")
	private Date modifyTime;

	@Lob
	@Column(name="topic_desc")
	private String topicDesc;

	@Column(name="topic_status")
	private Byte topicStatus;

	@Column(name="topic_title")
	private String topicTitle;

	//bi-directional many-to-many association to NewsInfo
	@ManyToMany(mappedBy="newsTopics")
	private List<NewsInfo> newsInfos;
	
	@Column(name="unique_id")
	private String uniqueId;

	public NewsTopic() {
	}

	public Integer getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getBannerImage() {
		return this.bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getListImage() {
		return this.listImage;
	}

	public void setListImage(String listImage) {
		this.listImage = listImage;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getTopicDesc() {
		return this.topicDesc;
	}

	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}

	public Byte getTopicStatus() {
		return this.topicStatus;
	}

	public void setTopicStatus(Byte topicStatus) {
		this.topicStatus = topicStatus;
	}

	public String getTopicTitle() {
		return this.topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public List<NewsInfo> getNewsInfos() {
		return this.newsInfos;
	}

	public void setNewsInfos(List<NewsInfo> newsInfos) {
		this.newsInfos = newsInfos;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public NewsTopic(Integer topicId, String bannerImage, String channelId,
			Date createTime, String listImage, Date modifyTime,
			String topicDesc, Byte topicStatus, String topicTitle,
			 String uniqueId) {
		super();
		this.topicId = topicId;
		this.bannerImage = bannerImage;
		this.channelId = channelId;
		this.createTime = createTime;
		this.listImage = listImage;
		this.modifyTime = modifyTime;
		this.topicDesc = topicDesc;
		this.topicStatus = topicStatus;
		this.topicTitle = topicTitle;
		this.uniqueId = uniqueId;
	}
	
}