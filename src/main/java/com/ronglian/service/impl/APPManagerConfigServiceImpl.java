package com.ronglian.service.impl;
import java.io.UnsupportedEncodingException;
/**
 * @author liyang
 * @createTime 2017年12月22日
 */
import java.util.UUID;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.ronglian.dao.APPManagerConfigDao;
import com.ronglian.entity.APPManagerConfig;
import com.ronglian.jedis.JedisDao;
import com.ronglian.service.APPManagerConfigService;
import com.ronglian.utils.JsonUtils;
import com.ronglian.utils.RongLianConstant;
@Service("tokenService")
public class APPManagerConfigServiceImpl implements APPManagerConfigService{

	@Autowired
	private APPManagerConfigDao appManagerConfigDao;

	@Autowired
	private JedisDao jedisDao;
	
	/*
	 * 根据appID来，获取令牌tokenId的服务
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
		//检验appId账号是否合法
		if(cfg != null){
			secretKey = cfg.getSecretKey();
			sign2 = builder.append(timeStamp).append(appId).append(secretKey).toString();
			try {
				sign2 = DigestUtils.md5DigestAsHex(sign2.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//检验签名是否合法
			if(sign2.equals(sign)){
				//签名合法生成tokenId，将cfg内容放入redis缓存，成功并返回tokenId
				tokenId = UUID.randomUUID().toString().replaceAll("-", "");
				//将cfg信息,清空appkey和secretkey信息；
				cfg.setAppKey("null");
				cfg.setSecretKey("null");
				//将cfg信息放入redis缓存中
				this.jedisDao.set(tokenId, JsonUtils.objectToJson(cfg));
				//设置当前缓存的信息的失效时间
				this.jedisDao.expire(tokenId, RongLianConstant.REDIS_KEY_EXPIRE);
				
				return tokenId;
			}
		}
		return null;
	}
	public boolean getTokenBytokenId(String tokenId){
		String result = this.jedisDao.get(tokenId);
		if(result != null){
			//重新设置失效时间
			this.jedisDao.expire(tokenId, RongLianConstant.REDIS_KEY_EXPIRE);
			return false;
		}else{
			return true;
		}
	}
}
