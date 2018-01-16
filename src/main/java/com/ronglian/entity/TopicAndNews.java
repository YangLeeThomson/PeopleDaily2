/**
 * 
 */
package com.ronglian.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ronglian.entity.TopicNewsKey;
/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
@Entity
@Table(name = "topic_and_news")
public class TopicAndNews implements Serializable{
	@EmbeddedId
    private TopicNewsKey topicNewsKey;

	public TopicNewsKey getTopicNewsKey() {
		return topicNewsKey;
	}

	public void setTopicNewsKey(TopicNewsKey topicNewsKey) {
		this.topicNewsKey = topicNewsKey;
	}

	public TopicAndNews(TopicNewsKey topicNewsKey) {
		super();
		this.topicNewsKey = topicNewsKey;
	}

	public TopicAndNews() {
		super();
	}
}
