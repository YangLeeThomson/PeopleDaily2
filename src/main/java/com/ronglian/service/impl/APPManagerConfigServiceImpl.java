package com.ronglian.service.impl;
import java.io.UnsupportedEncodingException;
import java.util.Map;
/**
 * @author liyang
 * @createTime 2017��12��22��
 */
import java.util.UUID;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSONObject;
import com.ronglian.dao.APPManagerConfigDao;
import com.ronglian.entity.APPManagerConfig;
import com.ronglian.jedis.JedisDao;
import com.ronglian.service.APPManagerConfigService;
import com.ronglian.utils.GetRequestJsonUtils;
import com.ronglian.utils.JsonUtils;
import com.ronglian.utils.RongLianConstant;
@Service("tokenService")
public class APPManagerConfigServiceImpl implements APPManagerConfigService{

	@Autowired
	private APPManagerConfigDao appManagerConfigDao;

	@Autowired
	private JedisDao jedisDao;
	
	/*
	 * 获取令牌tokenId
	 * */
	@Override
	public String getTokenByAppId(String appId,String timeStamp,String sign) {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		String sign2 = null;
		String secretKey = null;
		String tokenId = null;
		APPManagerConfig cfg = null;
		
		cfg = appManagerConfigDao.selectAPPManagerConfigByAppId(appId);
		if(cfg != null){
			secretKey = cfg.getSecretKey();
			sign2 = builder.append(timeStamp).append(appId).append(secretKey).toString();
			try {
				sign2 = DigestUtils.md5DigestAsHex(sign2.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(sign2.equals(sign)){
				tokenId = UUID.randomUUID().toString().replaceAll("-", "");
				cfg.setAppKey("null");
				cfg.setSecretKey("null");
				this.jedisDao.set(tokenId, JsonUtils.objectToJson(cfg));
				this.jedisDao.expire(tokenId, RongLianConstant.REDIS_KEY_EXPIRE);
				return tokenId;
			}
		}
		return null;
	}
	public boolean getTokenBytokenId(String tokenId){
		String result = this.jedisDao.get(tokenId);
		if(result != null){
			this.jedisDao.expire(tokenId, RongLianConstant.REDIS_KEY_EXPIRE);
			return false;
		}else{
			return true;
		}
	}
	
	public String getSecretKeyByToken(String tokenId){
		String result = this.jedisDao.get(tokenId);
		if(result != null){
			this.jedisDao.expire(tokenId, RongLianConstant.REDIS_KEY_EXPIRE);
			Map<String, Object> params = GetRequestJsonUtils.parseObject(result);
			String appId =  (String)params.get("appId");
			APPManagerConfig cfg = appManagerConfigDao.selectAPPManagerConfigByAppId(appId);
			return cfg.getSecretKey();
		}else{
			return null;
		}
	}
}
