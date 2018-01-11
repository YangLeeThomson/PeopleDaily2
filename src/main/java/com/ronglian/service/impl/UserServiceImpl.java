/**
 * 
 */
package com.ronglian.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronglian.dao.UserDao;
import com.ronglian.entity.User;
import com.ronglian.jedis.JedisDao;
import com.ronglian.service.UserService;
import com.ronglian.utils.JsonUtils;
import com.ronglian.utils.RongLianConstant;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
@Service
public class UserServiceImpl implements UserService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.UserService#login(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JedisDao jedisDao;
	
	@Override
	public RongLianResult login(User user) {
		//判断是否为空 user
		User loginUser = null;
		String accessToken = null;
		Map resultMap = new HashMap();
		
		if(user != null && user.getType() >= 0){
			//判断登录方式：type(0:weixin/1:twinter/2:faceBook)
			int type = user.getType();
			//微信weixin
			if(type == 0){
				 if(user.getOpenid() != null){
					 loginUser = userDao.getUserByOpenid(user.getOpenid());
				 }else{
					 RongLianResult.build(500, "缺失openid参数"); 
				 }
			}
			//twinter
			if(type == 1){
				 if(user.getTwOpenid() != null){
					 loginUser = userDao.getUserBytwOpenid(user.getTwOpenid()); 
				 }else{
					 RongLianResult.build(500, "缺失twOpenid参数");
				 }
			}
			//faceBook
			if(type == 2){
				if(user.getFaceOpenid() != null){
				 loginUser = userDao.getUserByfaceOpenid(user.getFaceOpenid());
				}else{
					RongLianResult.build(500, "缺失faceBook参数");
				}
			}
			//判断登录用户是否注册
			if(loginUser == null){
				//补全数据
				user.setUserId(UUID.randomUUID().toString());
				user.setCreateTime(new Date());
				user.setModifyTime(new Date());
				//如果没注册，先注册，再登陆
				loginUser = this.userDao.save(user);
			}
			accessToken = UUID.randomUUID().toString().replaceAll("-", "");
			resultMap.put("accessToken", accessToken);
			//将user登录信息放到redis缓存中
			this.jedisDao.set(accessToken, JsonUtils.objectToJson(loginUser));
			this.jedisDao.expire(accessToken,RongLianConstant.REDIS_ACCESS_TOKEN_EXPIRE );
			return RongLianResult.ok(resultMap);
		}else{
			return RongLianResult.build(500, "user信息为null或缺失type参数");
		}
	}

	/* (non-Javadoc)
	 * @see com.ronglian.service.UserService#getUserInfo(java.lang.String)
	 */
	@Override
	public RongLianResult getUserInfo(String accessToken) {
		// TODO Auto-generated method stub
		String json = this.jedisDao.get(accessToken);
		if(json != null){
			User user = JsonUtils.jsonToPojo(json, User.class);
			this.jedisDao.expire(accessToken, RongLianConstant.REDIS_ACCESS_TOKEN_EXPIRE);
			return RongLianResult.ok(user);
		}
		return RongLianResult.build(500, "请先登录");
	}

	/* (non-Javadoc)
	 * @see com.ronglian.service.UserService#quit(java.lang.String)
	 */
	@Override
	public RongLianResult quit(String accessToken) {
		// TODO Auto-generated method stub
		this.jedisDao.del(accessToken);
		return RongLianResult.ok();
	}

}
