/**
 * 
 */
package com.ronglian.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ28ÈÕ
 */
@Entity
@Table(name="app_start_picture")
public class AppStartPicture implements Serializable {
	
		@Id
		@Column(name="picture_id",nullable=false)
	    private String id;
		@Column(name="last_time",nullable=false)
	    private Integer lastTime;
		@Column(name="image_url",nullable=false)
	    private String imageUrl;
		@Column(name="go_url",nullable=false)
	    private String to;
		@Column(name="data_status",nullable=false)
	    private Integer dataStatus;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public Integer getLastTime() {
			return lastTime;
		}
		public void setLastTime(Integer lastTime) {
			this.lastTime = lastTime;
		}
		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public Integer getDataStatus() {
			return dataStatus;
		}
		public void setDataStatus(Integer dataStatus) {
			this.dataStatus = dataStatus;
		}

	    
}
