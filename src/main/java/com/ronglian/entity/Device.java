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
	private String idType;
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
	private String appVersion;
	/*
	 * 系统类型，
	 * 固定值： iOS  或者 Android
	 * */
	
	@Column(name="os_type")
	private String osType;
	/*
	 * 系统版本
	 * */
	@Column(name="os_version")
	private String osVersion;
	
	/*
	 * 如果应用未使用 SDK，
	 * 请传递与 app_version 相同的值
	 * */
	@Column(name="sdk_version")
	private String sdkVersion;
	/*
	 * 必须 首次打开时间的 UNIX 时间戳
	 * （以秒为单位，精确到微秒）
	 * */
	@Column(name="timestamp")
	private Float timestamp;
	
	public String getRdid() {
		return rdid;
	}

	public void setRdid(String rdid) {
		this.rdid = rdid;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public Integer getLat() {
		return lat;
	}

	public void setLat(Integer lat) {
		this.lat = lat;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public Float getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Float timestamp) {
		this.timestamp = timestamp;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPushResult() {
		return pushResult;
	}

	public void setPushResult(Integer pushResult) {
		this.pushResult = pushResult;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * 记录创建时间
	 * */
	@Column(name="create_time")
	private Date createTime;
	
	/*	
	 * 调用google接口返回结果,
	 * 0或者1 允许空
	 * */
	@Column(name="push_result")
	private Integer pushResult;
	
	
	
}
