package com.ronglian.utils.model.request;

import java.io.Serializable;

public class ImageInfo implements Serializable {
	/**
	 * 
	 */
	private String pictureId;
	private String picPath;
	private String picTitle;
	private String picDesc;
	public String getPictureId() {
		return pictureId;
	}
	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getPicTitle() {
		return picTitle;
	}
	public void setPicTitle(String picTitle) {
		this.picTitle = picTitle;
	}
	public String getPicDesc() {
		return picDesc;
	}
	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc;
	}
	public ImageInfo() {
		super();
	}
	public ImageInfo(String pictureId, String picPath, String picTitle,
			String picDesc) {
		super();
		this.pictureId = pictureId;
		this.picPath = picPath;
		this.picTitle = picTitle;
		this.picDesc = picDesc;
	}
}