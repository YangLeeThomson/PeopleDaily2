package com.ronglian.utils.model.request;

import com.ronglian.utils.RongLianUtils;

public class NewsInfoBody {

	private String newsId;
	private Integer newsSort;
	private String newsTags;
	private String newsTitle;
	private String channelUniqueId;
	private String channelName;
	private Integer showType;
	private String fullColumnImgUrl;
	private Byte hasVideo;
	private Byte isLive;
	private Byte isLiveReplay;
	private String appointCoverImage;
	private String liveUrl;
    private String liveReplayUrl;
    private String liveHostChatid;
    private String liveUsChatid;
	private String link;
	private Byte dataMode;
	private Integer videoDuration;
	private String topicUniqueId;
	private Integer isTopic ;
//	"publishTime", RongLianUtils.changeDateTime(news.getPublishTime())
	private String publishTime;
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public Integer getNewsSort() {
		return newsSort;
	}
	public void setNewsSort(Integer newsSort) {
		this.newsSort = newsSort;
	}
	public String getNewsTags() {
		return newsTags;
	}
	public void setNewsTags(String newsTags) {
		this.newsTags = newsTags;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getChannelUniqueId() {
		return channelUniqueId;
	}
	public void setChannelUniqueId(String channelUniqueId) {
		this.channelUniqueId = channelUniqueId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Integer getShowType() {
		return showType;
	}
	public void setShowType(Integer showType) {
		this.showType = showType;
	}
	public String getFullColumnImgUrl() {
		return fullColumnImgUrl;
	}
	public void setFullColumnImgUrl(String fullColumnImgUrl) {
		this.fullColumnImgUrl = fullColumnImgUrl;
	}
	public Byte getHasVideo() {
		return hasVideo;
	}
	public void setHasVideo(Byte hasVideo) {
		this.hasVideo = hasVideo;
	}
	public Byte getIsLive() {
		return isLive;
	}
	public void setIsLive(Byte isLive) {
		this.isLive = isLive;
	}
	public Byte getIsLiveReplay() {
		return isLiveReplay;
	}
	public void setIsLiveReplay(Byte isLiveReplay) {
		this.isLiveReplay = isLiveReplay;
	}
	public String getAppointCoverImage() {
		return appointCoverImage;
	}
	public void setAppointCoverImage(String appointCoverImage) {
		this.appointCoverImage = appointCoverImage;
	}
	public String getLiveUrl() {
		return liveUrl;
	}
	public void setLiveUrl(String liveUrl) {
		this.liveUrl = liveUrl;
	}
	public String getLiveReplayUrl() {
		return liveReplayUrl;
	}
	public void setLiveReplayUrl(String liveReplayUrl) {
		this.liveReplayUrl = liveReplayUrl;
	}
	public String getLiveHostChatid() {
		return liveHostChatid;
	}
	public void setLiveHostChatid(String liveHostChatid) {
		this.liveHostChatid = liveHostChatid;
	}
	public String getLiveUsChatid() {
		return liveUsChatid;
	}
	public void setLiveUsChatid(String liveUsChatid) {
		this.liveUsChatid = liveUsChatid;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Byte getDataMode() {
		return dataMode;
	}
	public void setDataMode(Byte dataMode) {
		this.dataMode = dataMode;
	}
	public Integer getVideoDuration() {
		return videoDuration;
	}
	public void setVideoDuration(Integer videoDuration) {
		this.videoDuration = videoDuration;
	}
	public String getTopicUniqueId() {
		return topicUniqueId;
	}
	public void setTopicUniqueId(String topicUniqueId) {
		this.topicUniqueId = topicUniqueId;
	}
	public Integer getIsTopic() {
		return isTopic;
	}
	public void setIsTopic(Integer isTopic) {
		this.isTopic = isTopic;
	}
	
}
