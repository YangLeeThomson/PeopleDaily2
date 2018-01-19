package com.ronglian.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_channel_config database table.
 * 
 */
@Entity
@Table(name="user_channel_config")
@NamedQuery(name="UserChannelConfig.findAll", query="SELECT u FROM UserChannelConfig u")
public class UserChannelConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="config_id")
	private Integer configId;

	@Column(name="channel_unique_id")
	private String channelUniqueId;
	
	@Column(name="device_id")
	private String deviceId;

	@Column(name="user_id")
	private String userId;

	@Column(name="self_sort")
	private int selfSort;

	public UserChannelConfig() {
	}



	public Integer getConfigId() {
		return configId;
	}
	public void setConfigId(Integer configId) {
		this.configId = configId;
	}


	public String getChannelUniqueId() {
		return channelUniqueId;
	}



	public void setChannelUniqueId(String channelUniqueId) {
		this.channelUniqueId = channelUniqueId;
	}



	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getSelfSort() {
		return this.selfSort;
	}

	public void setSelfSort(int selfSort) {
		this.selfSort = selfSort;
	}

}