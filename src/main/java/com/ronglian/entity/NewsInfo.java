package com.ronglian.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the news_info database table.
 * 
 */
@Entity
@Table(name="news_info")
@NamedQuery(name="NewsInfo.findAll", query="SELECT n FROM NewsInfo n")
public class NewsInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	/*@GeneratedValue(generator="system_uuid")  
    @GenericGenerator(name="system_uuid",strategy="uuid")  */
	@Column(name="news_id")
	private String newsId;

	@Column(name="can_comment")
	private String canComment;

	@Column(name="channel_id")
	private String channelId;
	
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	@Column(name="channel_name")
	private String channelName;

	@Column(name="comment_num")
	private Integer commentNum;

	@Column(name="content_id")
	private String contentId;

	@Column(name="content_type")
	private Integer contentType;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Temporal(TemporalType.DATE)
	@Column(name="edit_expire")
	private Date editExpire;

	@Column(name="image_list")
	private Integer imageList;

	@Column(name="is_edit_recom")
	private Byte isEditRecom;

	@Column(name="is_to_top")
	private Byte isToTop;

	@Column(name="is_topic")
	private Integer isTopic;

	@Column(name="is_topnews")
	private Integer isTopnews;

	@Column(name="is_topnews_totop")
	private Byte isTopnewsTotop;

	private String keywords;

	@Temporal(TemporalType.DATE)
	@Column(name="modify_time")
	private Date modifyTime;

	@Column(name="news_authors")
	private String newsAuthors;

	@Lob
	@Column(name="news_content")
	private String newsContent;

	@Column(name="news_organization")
	private String newsOrganization;

	@Column(name="news_original")
	private Integer newsOriginal;

	@Column(name="news_sort")
	private Integer newsSort;

	@Column(name="news_source")
	private String newsSource;

	@Column(name="news_source_url")
	private String newsSourceUrl;

	@Column(name="news_status")
	private Integer newsStatus;

	@Column(name="news_summary")
	private String newsSummary;

	@Column(name="news_tags")
	private String newsTags;

	@Column(name="news_title")
	private String newsTitle;

	@Temporal(TemporalType.DATE)
	@Column(name="publish_time")
	private Date publishTime;

	@Temporal(TemporalType.DATE)
	@Column(name="top_expire")
	private Date topExpire;

	@Column(name="topnews_sort")
	private Integer topnewsSort;

	@Temporal(TemporalType.DATE)
	@Column(name="topnews_top_expire")
	private Date topnewsTopExpire;
	
	@ManyToMany
	@JoinTable(
		name="topic_news"
		, joinColumns={
			@JoinColumn(name="news_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="topic_id")
			}
		)
	private List<NewsTopic> newsTopics;

/*20180111Ìí¼ÓµÄ×Ö¶Î*/
	
	@Column(name="data_status")
	private Integer dataStatus;
	@Column(name="show_type")
	private Integer showType;
	@Column(name="full_column_img_url")
	private String fullColumnImgUrl;
	@Column(name="has_video")
	private Byte hasVideo;
	@Column(name="is_live")
	private Byte isLive;
	@Column(name="is_live_replay")
	private Byte isLiveReplay;
	
	public NewsInfo() {
	}

	public String getNewsId() {
		return this.newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getCanComment() {
		return this.canComment;
	}

	public void setCanComment(String canComment) {
		this.canComment = canComment;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getCommentNum() {
		return this.commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public String getContentId() {
		return this.contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public Integer getContentType() {
		return this.contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEditExpire() {
		return this.editExpire;
	}

	public void setEditExpire(Date editExpire) {
		this.editExpire = editExpire;
	}

	public Integer getImageList() {
		return this.imageList;
	}

	public void setImageList(Integer imageList) {
		this.imageList = imageList;
	}

	public Byte getIsEditRecom() {
		return this.isEditRecom;
	}

	public void setIsEditRecom(Byte isEditRecom) {
		this.isEditRecom = isEditRecom;
	}

	public Byte getIsToTop() {
		return this.isToTop;
	}

	public void setIsToTop(Byte isToTop) {
		this.isToTop = isToTop;
	}

	public Integer getIsTopic() {
		return this.isTopic;
	}

	public void setIsTopic(Integer isTopic) {
		this.isTopic = isTopic;
	}

	public Integer getIsTopnews() {
		return this.isTopnews;
	}

	public void setIsTopnews(Integer isTopnews) {
		this.isTopnews = isTopnews;
	}

	public Byte getIsTopnewsTotop() {
		return this.isTopnewsTotop;
	}

	public void setIsTopnewsTotop(Byte isTopnewsTotop) {
		this.isTopnewsTotop = isTopnewsTotop;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getNewsAuthors() {
		return this.newsAuthors;
	}

	public void setNewsAuthors(String newsAuthors) {
		this.newsAuthors = newsAuthors;
	}

	public String getNewsContent() {
		return this.newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getNewsOrganization() {
		return this.newsOrganization;
	}

	public void setNewsOrganization(String newsOrganization) {
		this.newsOrganization = newsOrganization;
	}

	public Integer getNewsOriginal() {
		return this.newsOriginal;
	}

	public void setNewsOriginal(Integer newsOriginal) {
		this.newsOriginal = newsOriginal;
	}

	public Integer getNewsSort() {
		return this.newsSort;
	}

	public void setNewsSort(Integer newsSort) {
		this.newsSort = newsSort;
	}

	public String getNewsSource() {
		return this.newsSource;
	}

	public void setNewsSource(String newsSource) {
		this.newsSource = newsSource;
	}

	public String getNewsSourceUrl() {
		return this.newsSourceUrl;
	}

	public void setNewsSourceUrl(String newsSourceUrl) {
		this.newsSourceUrl = newsSourceUrl;
	}

	public Integer getNewsStatus() {
		return this.newsStatus;
	}

	public void setNewsStatus(Integer newsStatus) {
		this.newsStatus = newsStatus;
	}

	public String getNewsSummary() {
		return this.newsSummary;
	}

	public void setNewsSummary(String newsSummary) {
		this.newsSummary = newsSummary;
	}

	public String getNewsTags() {
		return this.newsTags;
	}

	public void setNewsTags(String newsTags) {
		this.newsTags = newsTags;
	}

	public String getNewsTitle() {
		return this.newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getTopExpire() {
		return this.topExpire;
	}

	public void setTopExpire(Date topExpire) {
		this.topExpire = topExpire;
	}

	public Integer getTopnewsSort() {
		return this.topnewsSort;
	}

	public void setTopnewsSort(Integer topnewsSort) {
		this.topnewsSort = topnewsSort;
	}

	public Date getTopnewsTopExpire() {
		return this.topnewsTopExpire;
	}

	public void setTopnewsTopExpire(Date topnewsTopExpire) {
		this.topnewsTopExpire = topnewsTopExpire;
	}

	public List<NewsTopic> getNewsTopics() {
		return this.newsTopics;
	}

	public void setNewsTopics(List<NewsTopic> newsTopics) {
		this.newsTopics = newsTopics;
	}

	
	
	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
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

	public NewsInfo(String newsId, String canComment, String channelId,
			String channelName, Integer commentNum, String contentId,
			Integer contentType, Date createTime, Date editExpire,
			Integer imageList, Byte isEditRecom, Byte isToTop, Integer isTopic,
			Integer isTopnews, Byte isTopnewsTotop, String keywords,
			Date modifyTime, String newsAuthors, String newsContent,
			String newsOrganization, Integer newsOriginal, Integer newsSort,
			String newsSource, String newsSourceUrl, Integer newsStatus,
			String newsSummary, String newsTags, String newsTitle,
			Date publishTime, Date topExpire, Integer topnewsSort,
			Date topnewsTopExpire, List<NewsTopic> newsTopics) {
		super();
		this.newsId = newsId;
		this.canComment = canComment;
		this.channelId = channelId;
		this.channelName = channelName;
		this.commentNum = commentNum;
		this.contentId = contentId;
		this.contentType = contentType;
		this.createTime = createTime;
		this.editExpire = editExpire;
		this.imageList = imageList;
		this.isEditRecom = isEditRecom;
		this.isToTop = isToTop;
		this.isTopic = isTopic;
		this.isTopnews = isTopnews;
		this.isTopnewsTotop = isTopnewsTotop;
		this.keywords = keywords;
		this.modifyTime = modifyTime;
		this.newsAuthors = newsAuthors;
		this.newsContent = newsContent;
		this.newsOrganization = newsOrganization;
		this.newsOriginal = newsOriginal;
		this.newsSort = newsSort;
		this.newsSource = newsSource;
		this.newsSourceUrl = newsSourceUrl;
		this.newsStatus = newsStatus;
		this.newsSummary = newsSummary;
		this.newsTags = newsTags;
		this.newsTitle = newsTitle;
		this.publishTime = publishTime;
		this.topExpire = topExpire;
		this.topnewsSort = topnewsSort;
		this.topnewsTopExpire = topnewsTopExpire;
		this.newsTopics = newsTopics;
	}

	public NewsInfo(String newsId, String canComment, String channelId,
			String channelName, Integer commentNum, String contentId,
			Integer contentType, Date createTime, Date editExpire,
			Integer imageList, Byte isEditRecom, Byte isToTop, Integer isTopic,
			Integer isTopnews, Byte isTopnewsTotop, String keywords,
			Date modifyTime, String newsAuthors, String newsContent,
			String newsOrganization, Integer newsOriginal, Integer newsSort,
			String newsSource, String newsSourceUrl, Integer newsStatus,
			String newsSummary, String newsTags, String newsTitle,
			Date publishTime, Date topExpire, Integer topnewsSort,
			Date topnewsTopExpire, List<NewsTopic> newsTopics,
			Integer dataStatus, Integer showType, String fullColumnImgUrl,
			Byte hasVideo, Byte isLive, Byte isLiveReplay) {
		super();
		this.newsId = newsId;
		this.canComment = canComment;
		this.channelId = channelId;
		this.channelName = channelName;
		this.commentNum = commentNum;
		this.contentId = contentId;
		this.contentType = contentType;
		this.createTime = createTime;
		this.editExpire = editExpire;
		this.imageList = imageList;
		this.isEditRecom = isEditRecom;
		this.isToTop = isToTop;
		this.isTopic = isTopic;
		this.isTopnews = isTopnews;
		this.isTopnewsTotop = isTopnewsTotop;
		this.keywords = keywords;
		this.modifyTime = modifyTime;
		this.newsAuthors = newsAuthors;
		this.newsContent = newsContent;
		this.newsOrganization = newsOrganization;
		this.newsOriginal = newsOriginal;
		this.newsSort = newsSort;
		this.newsSource = newsSource;
		this.newsSourceUrl = newsSourceUrl;
		this.newsStatus = newsStatus;
		this.newsSummary = newsSummary;
		this.newsTags = newsTags;
		this.newsTitle = newsTitle;
		this.publishTime = publishTime;
		this.topExpire = topExpire;
		this.topnewsSort = topnewsSort;
		this.topnewsTopExpire = topnewsTopExpire;
		this.newsTopics = newsTopics;
		this.dataStatus = dataStatus;
		this.showType = showType;
		this.fullColumnImgUrl = fullColumnImgUrl;
		this.hasVideo = hasVideo;
		this.isLive = isLive;
		this.isLiveReplay = isLiveReplay;
	}

	
	
}