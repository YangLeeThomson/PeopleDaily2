/**   
 * Copyright © 2018 北京荣之联科技股份有限公司 All rights reserved.
 * 
 * @Package: com.ronglian.service.impl 
 * @author: YeohLee   
 * @date: 2018年7月26日 下午4:24:46 
 */
package com.ronglian.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ronglian.dao.DeviceDao;
import com.ronglian.dao.DeviceFromadwordsDao;
import com.ronglian.entity.Device;
import com.ronglian.entity.DeviceFromadwords;
import com.ronglian.service.GoogleService;
import com.ronglian.utils.HttpClientUtil;
import com.ronglian.utils.RongLianConstant;
import com.ronglian.utils.RongLianResult;

 /** 
 * @ClassName: GoogleServiceImpl 
 * @Description: TODO
 * @author: YeohLee
 * @date: 2018年7月26日 下午4:24:46  
 */
@Service
public class GoogleServiceImpl implements GoogleService {

	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private DeviceFromadwordsDao deviceFromadwordsDao;
	/* 
	 * 推送数据给google
	 */
	@Override
	public RongLianResult pushToGoogle(Device device) {
		// 校验请求data数据
		if(judgeDeviceParams(device)){
			return RongLianResult.build(200, "请补齐数据！");
		}
		Device returnEntity ;
		returnEntity = this.deviceDao.findOne(device.getRdid());
		if(returnEntity != null ){
			return RongLianResult.build(0, "your deviceId had recorded");
		}
		//请求谷歌服务
		String requestGoogleUrl = preparedGoogleURL(RongLianConstant.GOOGLE_ADWORDS_URL, device);
		System.out.println("requestGoogleUrl==== "+requestGoogleUrl);
		String resultStr = HttpClientUtil.doPost(requestGoogleUrl, null);
		System.out.println("resultStr==== "+resultStr);
		//解析google返回数据
		ObjectMapper mapper = new ObjectMapper();
		Map mapRes;
		try {
			mapRes = mapper.readValue(resultStr, Map.class);
			boolean attributed = (boolean) mapRes.get("attributed");
			List<DeviceFromadwords> ad_events = null;
			if(attributed){
				device.setPush_result(1);
				ad_events = (List<DeviceFromadwords>) mapRes.get("ad_events");
				DeviceFromadwords deviceFrowords = ad_events.get(0);
				DeviceFromadwords returnWordsTntity = this.deviceFromadwordsDao.save(deviceFrowords);
				returnEntity = this.deviceDao.save(device);
				return RongLianResult.ok();
			}else{
				device.setPush_result(0);
				returnEntity = this.deviceDao.save(device);
				return RongLianResult.ok();
			}
			//非AdWords新增用户
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RongLianResult.build(200, "maybe saved failed or exception caused");
	}

	private boolean judgeDeviceParams(Device device){
		if(device == null){
			return true;
		}
		boolean flag;
		flag = StringUtils.isBlank(device.getRdid());
		if(flag){
			return true;
		}
		flag = flag || StringUtils.isBlank(device.getId_type());
		if(flag){
			return true;
		}
		flag = flag || StringUtils.isBlank(device.getApp_version());
		if(flag){
			return true;
		}
		flag = flag || StringUtils.isBlank(device.getOs_type());
		if(flag){
			return true;
		}
		flag = flag || StringUtils.isBlank(device.getOs_version());
		if(flag){
			return true;
		}
		flag = flag || StringUtils.isBlank(device.getSdk_version());
		if(flag){
			return true;
		}
		flag = flag || device.getLat() == null;
		if(flag){
			return true;
		}
		flag = flag || device.getTimestamp() == null;
		if(flag){
			return true;
		}
		return false;
	}
	private String preparedGoogleURL(String adwordUrl,Device device){
		String url = "";
		String dev_token = RongLianConstant.DEV_TOKEN;
		String link_id = null;
		//固定值： iOS  或者 Android
		if("iOS".equals(device.getOs_type())){
			link_id = RongLianConstant.iOS_KEY;
		}
		if("Android".equals(device.getOs_type())){
			link_id = RongLianConstant.Android_KEY;
		}
		String app_event_type = RongLianConstant.APP_EVENT_TYPE;
		String rdid = device.getRdid();
		String id_type = device.getId_type();
		int lat = device.getLat();
		String app_version = device.getApp_version();
		String os_version = device.getOs_version();
		String sdk_version = device.getSdk_version();
		float timestamp = device.getTimestamp();
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("dev_token", dev_token);
		map.put("link_id", link_id);
		map.put("app_event_type", app_event_type);
		map.put("rdid", rdid);
		map.put("id_type", id_type);
		map.put("lat", String.valueOf(lat));
		map.put("app_version", app_version);
		map.put("os_version", os_version);
		map.put("sdk_version", sdk_version);
		map.put("timestamp", String.valueOf(timestamp));
		
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			   Map.Entry<String, String> entry = it.next();
			   url = url + entry.getKey()+"="+entry.getValue()+"&";
		}
		//去掉最后一个&
		url = url.substring(0, url.length()-1);
		return adwordUrl+"?"+url;
	}
}
