package com.ronglian.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author liyang
 * @createTime 2017年12月22日
 */
@Entity
@Table(name = "news_user")
public class User implements Serializable{

	    @Id
	    @Column(name = "user_id")    
	    private String userId;

	    @Column(name="password") 
	    // 这里说一下，我使用指定数据库列的时�?�，使用小写会不起作用，修改为大写便正常了�?�不知道为何，如果遇到一样问题的可以尝试下�??
	    private String passWord;

	    @Column(name="nickname")
	    private String nickName;

	    @Column(name="email")
	    private String email;

	    @Column(name="openid")
	    private String openid;
	    
	    @Column(name="tw_openid")
	    private String twOpenid;
	    
	    @Column(name="face_openid")
	    private String faceOpenid;
	    
	    @Column(name="photo_url")
	    private String photoUrl;
	    
	    @Column(name="create_time")
	    private Date createTime;
	    
	    @Column(name="modify_time")
	    private Date modifyTime;
	    
	    @Column(name="sex")
	    private int sex;
	    
	    @Column(name="country")
	    private String country;
	    
	    @Column(name="province")
	    private String province;
	    
	    @Column(name="city")
	    private String city;


		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getPassWord() {
			return passWord;
		}

		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}

		public String getNickName() {
			return nickName;
		}

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}

		public String getFaceOpenid() {
			return faceOpenid;
		}

		public void setFaceOpenid(String faceOpenid) {
			this.faceOpenid = faceOpenid;
		}

		public String getPhotoUrl() {
			return photoUrl;
		}

		public void setPhotoUrl(String photoUrl) {
			this.photoUrl = photoUrl;
		}

		public int getSex() {
			return sex;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	

		public String getTwOpenid() {
			return twOpenid;
		}

		public void setTwOpenid(String twOpenid) {
			this.twOpenid = twOpenid;
		}


		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Date getModifyTime() {
			return modifyTime;
		}

		public void setModifyTime(Date modifyTime) {
			this.modifyTime = modifyTime;
		}

		//非关联数据库的属性
		private int type;

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}
		
		
}
