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
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="channel_id")
	private int channelId;

	@Column(name="channel_name")
	private String channelName;

	@Column(name="channel_sort")
	private int channelSort;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="is_show")
	private int isShow;

	@Temporal(TemporalType.DATE)
	@Column(name="modiy_time")
	private Date modiyTime;

	public Channel() {
	}

	public int getChannelId() {
		return this.channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getChannelSort() {
		return this.channelSort;
	}

	public void setChannelSort(int channelSort) {
		this.channelSort = channelSort;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsShow() {
		return this.isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public Date getModiyTime() {
		return this.modiyTime;
	}

	public void setModiyTime(Date modiyTime) {
		this.modiyTime = modiyTime;
	}

}