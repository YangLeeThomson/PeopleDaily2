/**
 * 
 */
package com.ronglian.utils.model.request;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author liyang
 * @createTime 2018��2��2��
 */
public class SlideShowBody implements Serializable{
		private static final long serialVersionUID = 1L;
		private String slideShowId;
		private String channelUniqueId;
		private String channelName;
//		private String createTime;
		private Long createTime;
		private String imageUrl;
		private String newsId;
		public Long getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Long createTime) {
			this.createTime = createTime;
		}
		public String getChannelName() {
			return channelName;
		}
		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}
		private String title;
		private String desc;
		private Integer dataStatus;
		private Integer sort;
		public Integer getDataStatus() {
			return dataStatus;
		}
		public void setDataStatus(Integer dataStatus) {
			this.dataStatus = dataStatus;
		}
		public String getSlideShowId() {
			return slideShowId;
		}
		public void setSlideShowId(String slideShowId) {
			this.slideShowId = slideShowId;
		}
		public String getChannelUniqueId() {
			return channelUniqueId;
		}
		public void setChannelUniqueId(String channelUniqueId) {
			this.channelUniqueId = channelUniqueId;
		}

		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public String getNewsId() {
			return newsId;
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
		public Integer getSort() {
			return sort;
		}
		public void setSort(Integer sort) {
			this.sort = sort;
		}
		
		//追加轮播图对应新闻内容
		private NewsInfoBody newsInfoBody;
		public NewsInfoBody getNewsInfoBody() {
			return newsInfoBody;
		}
		public void setNewsInfoBody(NewsInfoBody newsInfoBody) {
			this.newsInfoBody = newsInfoBody;
		}
}
