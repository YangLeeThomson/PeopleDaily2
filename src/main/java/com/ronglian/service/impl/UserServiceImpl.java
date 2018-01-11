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
 * @createTime 2017��12��29��
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
		//�ж��Ƿ�Ϊ�� user
		User loginUser = null;
		String accessToken = null;
		Map resultMap = new HashMap();
		
		if(user != null && user.getType() >= 0){
			//�жϵ�¼��ʽ��type(0:weixin/1:twinter/2:faceBook)
			int type = user.getType();
			//΢��weixin
			if(type == 0){
				 if(user.getOpenid() != null){
					 loginUser = userDao.getUserByOpenid(user.getOpenid());
				 }else{
					 RongLianResult.build(500, "ȱʧopenid����"); 
				 }
			}
			//twinter
			if(type == 1){
				 if(user.getTwOpenid() != null){
					 loginUser = userDao.getUserBytwOpenid(user.getTwOpenid()); 
				 }else{
					 RongLianResult.build(500, "ȱʧtwOpenid����");
				 }
			}
			//faceBook
			if(type == 2){
				if(user.getFaceOpenid() != null){
				 loginUser = userDao.getUserByfaceOpenid(user.getFaceOpenid());
				}else{
					RongLianResult.build(500, "ȱʧfaceBook����");
				}
			}
			//�жϵ�¼�û��Ƿ�ע��
			if(loginUser == null){
				//��ȫ����
				user.setUserId(UUID.randomUUID().toString());
				user.setCreateTime(new Date());
				user.setModifyTime(new Date());
				//���ûע�ᣬ��ע�ᣬ�ٵ�½
				loginUser = this.userDao.save(user);
			}
			accessToken = UUID.randomUUID().toString().replaceAll("-", "");
			resultMap.put("accessToken", accessToken);
			//��user��¼��Ϣ�ŵ�redis������
			this.jedisDao.set(accessToken, JsonUtils.objectToJson(loginUser));
			this.jedisDao.expire(accessToken,RongLianConstant.REDIS_ACCESS_TOKEN_EXPIRE );
			return RongLianResult.ok(resultMap);
		}else{
			return RongLianResult.build(500, "user��ϢΪnull��ȱʧtype����");
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
		return RongLianResult.build(500, "���ȵ�¼");
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
