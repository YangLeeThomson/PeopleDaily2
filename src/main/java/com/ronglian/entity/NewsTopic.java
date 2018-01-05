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
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="topic_id")
	private int topicId;

	@Column(name="banner_image")
	private String bannerImage;

	@Column(name="channel_id")
	private int channelId;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="list_image")
	private String listImage;

	@Temporal(TemporalType.DATE)
	@Column(name="modify_time")
	private Date modifyTime;

	@Lob
	@Column(name="topic_desc")
	private String topicDesc;

	@Column(name="topic_status")
	private byte topicStatus;

	@Column(name="topic_title")
	private String topicTitle;

	//bi-directional many-to-many association to NewsInfo
	@ManyToMany(mappedBy="newsTopics")
	private List<NewsInfo> newsInfos;

	public NewsTopic() {
	}

	public int getTopicId() {
		return this.topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getBannerImage() {
		return this.bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public int getChannelId() {
		return this.channelId;
	}

	public void setChannelId(int channelId) {
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

	public byte getTopicStatus() {
		return this.topicStatus;
	}

	public void setTopicStatus(byte topicStatus) {
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

}