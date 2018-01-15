package com.ronglian.entity;

import java.io.Serializable;
import com.ronglian.utils.model.request.TopicNewsRelation;
import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class TopicNewsKey implements Serializable{
	 @Column(name="topic_uniqueID")
	 private String topicUniqueID ;
	 @Column(name="news_id")
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
	@Override
	public boolean equals(Object obj){
		if(this == obj)  
	        return false;  
	    if(obj == null)  
	        return false;  
	    if(getClass() != obj.getClass() )  
	        return false;  
	    TopicNewsKey other = (TopicNewsKey)obj;  
	    if(topicUniqueID == null) {  
	         if(other.topicUniqueID != null) {  
	              return false;  
	         }  
	    }else if (!topicUniqueID.equals(other.topicUniqueID))  
	    { return false;  }
	    if(newsID == null) {  
	         if(other.newsID != null) {  
	              return false;  
	         }  
	    }else if (!newsID.equals(other.newsID))  
	    { return false;  }  
	    return true; 
	}
	
	public TopicNewsKey(TopicNewsRelation topicNewsRelation){
		this.topicUniqueID=topicNewsRelation.getTopicUniqueID();
		this.newsID=topicNewsRelation.getNewsID();
	}
	public TopicNewsKey() {
		super();
	}
	
}