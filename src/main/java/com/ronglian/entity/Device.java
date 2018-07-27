/**   
 * Copyright © 2018 北京荣之联科技股份有限公司 All rights reserved.
 * 
 * @Package: com.ronglian.entity 
 * @author: YeohLee   
 * @date: 2018年7月26日 上午11:10:16 
 */
package com.ronglian.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

 /** 
 * @ClassName: Device 
 * @Description: TODO
 * @author: YeohLee
 * @date: 2018年7月26日 上午11:10:16  
 */
@Entity
@Table(name="device_info")
@NamedQuery(name="Device.findAll", query="SELECT n FROM Device n")
public class Device implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * advertisingid (Android 设备) 
	 * idfa (iOS 设备)
	 * 主键
	 * */
	@Id
	@Column(name="rdid")
	private String rdid;
	/*
	 * 固定值 
	 * Adroid传入 advertisingid
	 * iOS 设备传入idfa
	 * */
	@Column(name="id_type")
	private String id_type;
	/*
	 * 广告追踪 
	 * 设备的“限制广告跟踪”状态。
	 * 0 或 1
	*/
	@Column(name="lat")
	private Integer lat;
	/*
	 * APP版本
	 * iOS 设备:
	 * Android 设备:
	 * */
	@Column(name="app_version")
	private String app_version;
	/*
	 * 系统类型，
	 * 固定值： iOS  或者 Android
	 * */
	
	@Column(name="os_type")
	private String os_type;
	/*
	 * 系统版本
	 * */
	@Column(name="os_version")
	private String os_version;
	
	/*
	 * 如果应用未使用 SDK，
	 * 请传递与 app_version 相同的值
	 * */
	@Column(name="sdk_version")
	private String sdk_version;
	/*
	 * 必须 首次打开时间的 UNIX 时间戳
	 * （以秒为单位，精确到微秒）
	 * */
	@Column(name="timestamp")
	private BigDecimal timestamp;
	

	/*
	 * 记录创建时间
	 * */
	@Column(name="create_time")
	private Date create_time;
	
	/*	
	 * 调用google接口返回结果,
	 * 0或者1 允许空
	 * */
	@Column(name="push_result")
	private Integer push_result;

	public String getRdid() {
		return rdid;
	}

	public void setRdid(String rdid) {
		this.rdid = rdid;
	}

	public String getId_type() {
		return id_type;
	}

	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	public Integer getLat() {
		return lat;
	}

	public void setLat(Integer lat) {
		this.lat = lat;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	public String getOs_type() {
		return os_type;
	}

	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}


	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getSdk_version() {
		return sdk_version;
	}

	public void setSdk_version(String sdk_version) {
		this.sdk_version = sdk_version;
	}


	public BigDecimal getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(BigDecimal timestamp) {
		this.timestamp = timestamp;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getPush_result() {
		return push_result;
	}

	public void setPush_result(Integer push_result) {
		this.push_result = push_result;
	}
	
	
	
}
