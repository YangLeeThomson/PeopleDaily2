package com.ronglian.entity;

import java.io.Serializable;
import javax.persistence.*;
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
	@GeneratedValue(strategy=GenerationType.TABLE)
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
	private int commentNum;
	
	@Column(name="apprise_up_num")
	private Integer appriseUpNum;

	@Column(name="apprise_down_num")
	private Integer appriseDownNum;
	
	public Integer getAppriseUpNum() {
		return appriseUpNum;
	}

	public void setAppriseUpNum(Integer appriseUpNum) {
		this.appriseUpNum = appriseUpNum;
	}

	public Integer getAppriseDownNum() {
		return appriseDownNum;
	}

	public void setAppriseDownNum(Integer appriseDownNum) {
		this.appriseDownNum = appriseDownNum;
	}

	@Column(name="content_id")
	private String contentId;

	@Column(name="content_type")
	private int contentType;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Temporal(TemporalType.DATE)
	@Column(name="edit_expire")
	private Date editExpire;

	@Column(name="image_list")
	private int imageList;

	@Column(name="is_edit_recom")
	private byte isEditRecom;

	@Column(name="is_to_top")
	private byte isToTop;

	@Column(name="is_topic")
	private int isTopic;

	@Column(name="is_topnews")
	private int isTopnews;

	@Column(name="is_topnews_totop")
	private byte isTopnewsTotop;

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
	private int newsOriginal;

	@Column(name="news_sort")
	private int newsSort;

	@Column(name="news_source")
	private String newsSource;

	@Column(name="news_source_url")
	private String newsSourceUrl;

	@Column(name="news_status")
	private int newsStatus;

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
	private int topnewsSort;

	@Temporal(TemporalType.DATE)
	@Column(name="topnews_top_expire")
	private Date topnewsTopExpire;

	//bi-directional many-to-many association to NewsTopic
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

	public int getCommentNum() {
		return this.commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public String getContentId() {
		return this.contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public int getContentType() {
		return this.contentType;
	}

	public void setContentType(int contentType) {
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

	public int getImageList() {
		return this.imageList;
	}

	public void setImageList(int imageList) {
		this.imageList = imageList;
	}

	public byte getIsEditRecom() {
		return this.isEditRecom;
	}

	public void setIsEditRecom(byte isEditRecom) {
		this.isEditRecom = isEditRecom;
	}

	public byte getIsToTop() {
		return this.isToTop;
	}

	public void setIsToTop(byte isToTop) {
		this.isToTop = isToTop;
	}

	public int getIsTopic() {
		return this.isTopic;
	}

	public void setIsTopic(int isTopic) {
		this.isTopic = isTopic;
	}

	public int getIsTopnews() {
		return this.isTopnews;
	}

	public void setIsTopnews(int isTopnews) {
		this.isTopnews = isTopnews;
	}

	public byte getIsTopnewsTotop() {
		return this.isTopnewsTotop;
	}

	public void setIsTopnewsTotop(byte isTopnewsTotop) {
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

	public int getNewsOriginal() {
		return this.newsOriginal;
	}

	public void setNewsOriginal(int newsOriginal) {
		this.newsOriginal = newsOriginal;
	}

	public int getNewsSort() {
		return this.newsSort;
	}

	public void setNewsSort(int newsSort) {
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

	public int getNewsStatus() {
		return this.newsStatus;
	}

	public void setNewsStatus(int newsStatus) {
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

	public int getTopnewsSort() {
		return this.topnewsSort;
	}

	public void setTopnewsSort(int topnewsSort) {
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

}