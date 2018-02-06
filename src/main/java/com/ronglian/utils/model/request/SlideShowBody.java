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
 * @createTime 2018Äê2ÔÂ2ÈÕ
 */
public class SlideShowBody implements Serializable{
		private static final long serialVersionUID = 1L;
		private String slideShowId;
		private String channelUniqueId;
		private String channelName;
		private String createTime;
		private String imageUrl;
		private String newsId;
		public String getChannelName() {
			return channelName;
		}
		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}
		private String title;
		private String desc;
		private String dataStatus;
		private Integer sort;
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
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
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
