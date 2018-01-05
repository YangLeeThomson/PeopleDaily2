package com.ronglian.entity;

import java.io.Serializable;
import javax.persistence.*;
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
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="slideshow_id")
	private String slideshowId;

	@Column(name="channel_id")
	private int channelId;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="image_url")
	private String imageUrl;

	@Column(name="news_id")
	private String newsId;

	private int sort;

	public NewsSlideshow() {
	}

	public String getSlideshowId() {
		return this.slideshowId;
	}

	public void setSlideshowId(String slideshowId) {
		this.slideshowId = slideshowId;
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

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}