package com.ronglian.entity;

import java.io.Serializable;

import javax.persistence.*;


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
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="picture_id")
	private String pictureId;

	@Column(name="image_path")
	private String imagePath;

	@Column(name="is_cover")
	private int isCover;

	@Column(name="news_id")
	private String newsId;

	@Lob
	@Column(name="picture_desc")
	private String pictureDesc;

	@Column(name="picture_sort")
	private int pictureSort;
	
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

	public int getIsCover() {
		return this.isCover;
	}

	public void setIsCover(int isCover) {
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

	public int getPictureSort() {
		return this.pictureSort;
	}

	public void setPictureSort(int pictureSort) {
		this.pictureSort = pictureSort;
	}

	public String getPictureTitle() {
		return pictureTitle;
	}

	public void setPictureTitle(String pictureTitle) {
		this.pictureTitle = pictureTitle;
	}

	public NewsPicture(String pictureId, String imagePath, String pictureDesc,String pictureTitle) {
		super();
		this.pictureId = pictureId;
		this.imagePath = imagePath;
		this.pictureDesc = pictureDesc;
		this.pictureTitle = pictureTitle;
	}
}