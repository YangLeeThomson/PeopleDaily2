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
	    private int lastTime;
		@Column(name="image_url",nullable=false)
	    private String imageUrl;
		@Column(name="go_url",nullable=false)
	    private String to;
		@Column(name="data_status",nullable=false)
	    private int dataStatus;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public int getLastTime() {
			return lastTime;
		}
		public void setLastTime(int lastTime) {
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
		public int getDataStatus() {
			return dataStatus;
		}
		public void setDataStatus(int dataStatus) {
			this.dataStatus = dataStatus;
		}

	    
}
