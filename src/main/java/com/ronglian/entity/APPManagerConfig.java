package com.ronglian.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author liyang
 * @createTime 2017年12月22日
 */
@Entity
@Table(name="app_manager_config")
public class APPManagerConfig implements Serializable {
	@Id
	@Column(name="config_id")
	private String configId;
	@Column(name="app_id",nullable=false) 
	private String appId;
	@Column(name="app_key",nullable=false)
	private String appKey;
	@Column(name="secret_key",nullable=false)
	private String secretKey;
	@Column(name="is_enable",nullable=false)
	private int isEnable;
	@Column(name="app_desc",nullable=false)
	private String appDesc;
	@Column(name="create_time",nullable=false)
	private Date createTime;
	@Column(name="modify_time",nullable=false)
	private Date modifyTime;
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public int getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
}
