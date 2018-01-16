package com.ronglian.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the system_log database table.
 * 
 */
@Entity
@Table(name="system_log")
@NamedQuery(name="SystemLog.findAll", query="SELECT s FROM SystemLog s")
public class SystemLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="log_id")
	private String logId;

	@Column(name="app_id")
	private String appId;

	@Column(name="create_time")
	private Date createTime;

	@Column(name="is_success")
	private int isSuccess;

	@Column(name="service_name")
	private String serviceName;

	public SystemLog() {
	}

	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsSuccess() {
		return this.isSuccess;
	}

	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}