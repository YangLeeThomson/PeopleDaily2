package com.ronglian.utils.model.request;


public class TopicNewsRelation {
	private String topicUniqueID ;
	 private String newsID;
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
	public TopicNewsRelation() {
		super();
	}
	public TopicNewsRelation(String topicUniqueID, String newsID) {
		super();
		this.topicUniqueID = topicUniqueID;
		this.newsID = newsID;
	}
	 
}
