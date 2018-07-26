/**   
 * Copyright © 2018 北京荣之联科技股份有限公司 All rights reserved.
 * 
 * @Package: com.ronglian.entity 
 * @author: YeohLee   
 * @date: 2018年7月26日 下午3:33:17 
 */
package com.ronglian.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

 /** 
 * @ClassName: DeviceFromadwords 
 * @Description: TODO
 * @author: YeohLee
 * @date: 2018年7月26日 下午3:33:17  
 */
@Entity
@Table(name="device_fromadwords")
@NamedQuery(name="DeviceFromadwords.findAll", query="SELECT n FROM DeviceFromadwords n")
public class DeviceFromadwords implements Serializable{

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
	@Column(name="ad_event_id")
	private String ad_event_id;
	@Column(name="conversion_metric")
	private String conversion_metric;
	@Column(name="interaction_type")
	private String interaction_type;
	@Column(name="campaign_type")
	private String campaign_type;
	@Column(name="campaign_id")
	private String campaign_id;
	@Column(name="campaign_name")
	private String campaign_name;
	@Column(name="ad_type")
	private String ad_type;
	@Column(name="external_customer_id")
	private String external_customer_id;
	@Column(name="location")
	private String location;
	@Column(name="network_type")
	private String network_type;
	@Column(name="network_subtype")
	private String network_subtype;
	@Column(name="video_id")
	private String video_id;
	@Column(name="keyword")
	private String keyword;
	@Column(name="match_type")
	private String match_type;
	@Column(name="placement")
	private String placement;
	@Column(name="ad_group_id")
	private String ad_group_id;
	@Column(name="creative_id")
	private String creative_id;
	@Column(name="timestamp")
	private Float timestamp;
	public String getRdid() {
		return rdid;
	}
	public void setRdid(String rdid) {
		this.rdid = rdid;
	}
	public String getAd_event_id() {
		return ad_event_id;
	}
	public void setAd_event_id(String ad_event_id) {
		this.ad_event_id = ad_event_id;
	}
	public String getConversion_metric() {
		return conversion_metric;
	}
	public void setConversion_metric(String conversion_metric) {
		this.conversion_metric = conversion_metric;
	}
	public String getInteraction_type() {
		return interaction_type;
	}
	public void setInteraction_type(String interaction_type) {
		this.interaction_type = interaction_type;
	}
	public String getCampaign_type() {
		return campaign_type;
	}
	public void setCampaign_type(String campaign_type) {
		this.campaign_type = campaign_type;
	}
	public String getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}
	public String getCampaign_name() {
		return campaign_name;
	}
	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}
	public String getAd_type() {
		return ad_type;
	}
	public void setAd_type(String ad_type) {
		this.ad_type = ad_type;
	}
	public String getExternal_customer_id() {
		return external_customer_id;
	}
	public void setExternal_customer_id(String external_customer_id) {
		this.external_customer_id = external_customer_id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getNetwork_type() {
		return network_type;
	}
	public void setNetwork_type(String network_type) {
		this.network_type = network_type;
	}
	public String getNetwork_subtype() {
		return network_subtype;
	}
	public void setNetwork_subtype(String network_subtype) {
		this.network_subtype = network_subtype;
	}
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getMatch_type() {
		return match_type;
	}
	public void setMatch_type(String match_type) {
		this.match_type = match_type;
	}
	public String getPlacement() {
		return placement;
	}
	public void setPlacement(String placement) {
		this.placement = placement;
	}
	public String getAd_group_id() {
		return ad_group_id;
	}
	public void setAd_group_id(String ad_group_id) {
		this.ad_group_id = ad_group_id;
	}
	public String getCreative_id() {
		return creative_id;
	}
	public void setCreative_id(String creative_id) {
		this.creative_id = creative_id;
	}
	public Float getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Float timestamp) {
		this.timestamp = timestamp;
	}
}
