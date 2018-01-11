package com.ronglian.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the news_channel database table.
 * 
 */
@Entity
@Table(name="news_channel")
@NamedQuery(name="Channel.findAll", query="SELECT n FROM Channel n")
public class Channel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="channel_id")
	private Integer channelId;

	@Column(name="channel_name")
	private String channelName;

	@Column(name="channel_sort")
	private Integer channelSort;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="is_show")
	private Integer isShow;

	@Temporal(TemporalType.DATE)
	@Column(name="modiy_time")
	private Date modiyTime;
	
	@Column(name="unique_id")
	private String uniqueID;
	
	@Column(name="data_status")
	private Integer dataStatus;

	public Channel() {
	}

	public Integer getChannelId() {
		return this.channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getChannelSort() {
		return this.channelSort;
	}

	public void setChannelSort(Integer channelSort) {
		this.channelSort = channelSort;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsShow() {
		return this.isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Date getModiyTime() {
		return this.modiyTime;
	}

	public void setModiyTime(Date modiyTime) {
		this.modiyTime = modiyTime;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Channel(Integer channelId, String channelName, Integer channelSort,
			String uniqueID, Integer dataStatus) {
		super();
		this.channelId = channelId;
		this.channelName = channelName;
		this.channelSort = channelSort;
		this.uniqueID = uniqueID;
		this.dataStatus = dataStatus;
	}
	
}