/**
 * 
 */
package com.ronglian.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
@Entity
@Table(name = "topic_and_news")
public class TopicAndNews implements Serializable{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id",nullable = false)
	private String id;
	 @Column(nullable = false, name="topic_uniqueID")
	 private String topicUniqueID ;
	 @Column(nullable = false, name="news_id")
	 private String newsID;

	 public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTopicUniqueID() {
		return topicUniqueID;
	}
	public void setTopicUniqueID(String topicUniqueID) {
		this.topicUniqueID = topicUniqueID;
	}
	public String getNewsID() {
		return newsID;
	}
	public void setNewsID(String newsID) {
		this.newsID = newsID;
	}
}
