package com.ronglian.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="news_author")
@NamedQuery(name="NewsAuthor.findAll", query="SELECT n FROM NewsAuthor n")
public class NewsAuthor  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	private String id;

	@Column(name="author_name")
	private String authorName;

	@Column(name="author_unique_id")
	private String authorUniqueId;

	@Column(name="news_id")
	private String newsId;

	@Column(name="photo_url")
	private String photoUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorUniqueId() {
		return authorUniqueId;
	}

	public void setAuthorUniqueId(String authorUniqueId) {
		this.authorUniqueId = authorUniqueId;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	@Column(name="media_name")
	private String mediaName;
	
}

	
	
