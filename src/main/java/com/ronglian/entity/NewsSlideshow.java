package com.ronglian.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


/**
 * The persistent class for the news_slideshow database table.
 * 
 */
@Entity
@Table(name="news_slideshow")
@NamedQuery(name="NewsSlideshow.findAll", query="SELECT n FROM NewsSlideshow n")
public class NewsSlideshow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="slideshow_id")
	private String slideShowId;

	@Column(name="channel_unique_id")
	private String channelUniqueId;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@Column(name="create_time")
	private Date createTime;

	@Column(name="image_url")
	private String imageUrl;

	@Column(name="news_id")
	private String newsId;

	@Column(name="title")
	private String title;

	@Column(name="descript")
	private String desc;
	
	@Column(name="data_status")
	private String dataStatus;

	@Column(name="sort")
	private Integer sort;
	
	public NewsSlideshow() {
	}

	public String getSlideShowId() {
		return this.slideShowId;
	}

	public void setSlideShowId(String slideShowId) {
		this.slideShowId = slideShowId;
	}

	public String getChannelUniqueId() {
		return this.channelUniqueId;
	}

	public void setChannelUniqueId(String channelUniqueId) {
		this.channelUniqueId = channelUniqueId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getNewsId() {
		return this.newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}