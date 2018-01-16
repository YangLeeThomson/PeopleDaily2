package com.ronglian.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the news_picture database table.
 * 
 */
@Entity
@Table(name="news_picture")
@NamedQuery(name="NewsPicture.findAll", query="SELECT n FROM NewsPicture n")
public class NewsPicture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	/*@GeneratedValue(generator="system_uuid")  
    @GenericGenerator(name="system_uuid",strategy="uuid")  */
	@Column(name="picture_id")
	private String pictureId;

	@Column(name="image_path")
	private String imagePath;

	@Column(name="is_cover")
	private Integer isCover;

	@Column(name="news_id")
	private String newsId;

	@Lob
	@Column(name="picture_desc")
	private String pictureDesc;

	@Column(name="picture_sort")
	private Integer pictureSort;
	
	@Column(name="picture_title")
	private String pictureTitle;

	public NewsPicture() {
	}

	public String getPictureId() {
		return this.pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getIsCover() {
		return this.isCover;
	}

	public void setIsCover(Integer isCover) {
		this.isCover = isCover;
	}

	public String getNewsId() {
		return this.newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getPictureDesc() {
		return this.pictureDesc;
	}

	public void setPictureDesc(String pictureDesc) {
		this.pictureDesc = pictureDesc;
	}

	public Integer getPictureSort() {
		return this.pictureSort;
	}

	public void setPictureSort(Integer pictureSort) {
		this.pictureSort = pictureSort;
	}

	public String getPictureTitle() {
		return pictureTitle;
	}

	public void setPictureTitle(String pictureTitle) {
		this.pictureTitle = pictureTitle;
	}

	public NewsPicture(String newsId,String pictureId, String imagePath, String pictureDesc,String pictureTitle) {
		super();
		this.newsId=newsId;
		this.pictureId = pictureId;
		this.imagePath = imagePath;
		this.pictureDesc = pictureDesc;
		this.pictureTitle = pictureTitle;
	}

	public NewsPicture(String newsId,String pictureId, String imagePath, Integer pictureSort) {
		super();
		this.newsId=newsId;
		this.pictureId = pictureId;
		this.imagePath = imagePath;
		this.pictureSort = pictureSort;
	}
	
	
}