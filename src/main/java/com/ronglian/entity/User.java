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
	    private int userId;

	    @Column(nullable = false, name="password") 
	    // 这里说一下，我使用指定数据库列的时�?�，使用小写会不起作用，修改为大写便正常了�?�不知道为何，如果遇到一样问题的可以尝试下�??
	    private String passWord;

	    @Column(nullable = false, name="nickname")
	    private String nickName;

	    @Column(nullable = false,name="email")
	    private String email;

	    @Column(nullable = false, name="openid")
	    private String openId;
	    
	    @Column(nullable = false, name="tw_openid")
	    private String twOpenid;
	    
	    @Column(nullable = false, name="wb_openid")
	    private String wbOpenid;
	    
	    @Column(nullable = false, name="user_photo")
	    private String userPhoto;
	    
	    @Column(nullable = false, name="create_time")
	    private Date createTime;
	    
	    @Column(nullable = false, name="modify_time")
	    private Date modifyTime;

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
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

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getOpenId() {
			return openId;
		}

		public void setOpenId(String openId) {
			this.openId = openId;
		}

		public String getTwOpenid() {
			return twOpenid;
		}

		public void setTwOpenid(String twOpenid) {
			this.twOpenid = twOpenid;
		}

		public String getWbOpenid() {
			return wbOpenid;
		}

		public void setWbOpenid(String wbOpenid) {
			this.wbOpenid = wbOpenid;
		}

		public String getUserPhoto() {
			return userPhoto;
		}

		public void setUserPhoto(String userPhoto) {
			this.userPhoto = userPhoto;
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
	   
	    
}
