/**
 * 
 */
package com.ronglian.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ronglian.entity.Collection;
import com.ronglian.entity.NewsInfo;
import com.ronglian.entity.NewsPicture;
import com.ronglian.entity.User;
import com.ronglian.service.CollectionService;
import com.ronglian.service.NewsInfoService;
import com.ronglian.service.NewsPictureService;
import com.ronglian.service.UserService;
import com.ronglian.utils.RongLianResult;
import com.ronglian.utils.model.request.RongLianRequest;

/**
 * @author liyang
 * @createTime 2017年12月29日
 */
@RestController
@RequestMapping("/api")
public class CollectionController {

	@Autowired
	private CollectionService collectionService;
	@Autowired
	private UserService userService;
	@Autowired
	private NewsPictureService pictureService;
//	@Autowired
//	private NewsInfoService  newsInfoService;
	/**
	 * 用户收藏接口
	 */
	@RequestMapping(value = "/1.0/usercollection", method = RequestMethod.POST)
	public RongLianResult addCollection(@RequestBody RongLianRequest<Collection> collectionBody) {
		Collection collection = null;
		String accessToken = null;
		String userId = null;
		if (collectionBody != null) {
			collection = collectionBody.getData();
			accessToken = collectionBody.getAccessToken();
		}
		if (collection != null) {
			userId = collection.getUserId();
		}
		if (StringUtils.isNotBlank(accessToken)) {
			RongLianResult result = this.userService.getUserInfo(accessToken);
			if (result.getData() == null) {
				return RongLianResult.build(106, "accessToken is error");
			}
			User user = (User) result.getData();
			String userId2 = user.getUserId();
			if (!userId2.equals(userId)) {
				return RongLianResult.build(200, "maybe param userId is error");
			}
		}
		if (StringUtils.isBlank(accessToken) && StringUtils.isNotBlank(userId)) {
			return RongLianResult.build(200, "you have not logined ,so userId should be null ");
		}
		List<NewsPicture> list = this.pictureService.findPicture(collection.getNewsId());
		if(list != null && list.size() > 0){
			collection.setImgUrl(list.get(0).getImagePath());
		}
		try {
			return collectionService.insertUserCollection(collection);
		} catch (Exception e) {
			return RongLianResult.build(500, "save failed");
		}
	}

//	@RequestMapping(value = "/1.0/collectionList", method = RequestMethod.GET)
//	public RongLianResult getCollection(String deviceId, String userId, String accessToken) {
	/**
	 * 获取用户收藏列表接口
	 * */
	@RequestMapping(value = "/1.0/collectionList", method = RequestMethod.GET)
	public RongLianResult getCollection(String deviceId, String userId, String accessToken,
			@RequestParam(value="pageNo",defaultValue="1",required=false)Integer pageNo,
			@RequestParam(value="pageSize",defaultValue="10",required=false)Integer pageSize) {
		if (StringUtils.isNotBlank(accessToken)) {
			RongLianResult result = this.userService.getUserInfo(accessToken);
			if (result.getData() == null) {
				return RongLianResult.build(106, "accessToken is error");
			}
			User user = (User) result.getData();
			String userId2 = user.getUserId();
			if (!userId2.equals(userId)) {
				return RongLianResult.build(200, "maybe param userId is error");
			}
		}
		if (StringUtils.isBlank(accessToken) && StringUtils.isNotBlank(userId)) {
			return RongLianResult.build(200, "you have not loginned ,so userId should be null ");
		}
		try {
//			return this.collectionService.getUserCollection(userId, deviceId);
			return this.collectionService.getUserCollection(userId, deviceId,pageNo,pageSize);
		} catch (Exception e) {
			return RongLianResult.build(500, "server error");
		}
	}

	/**
	 * 取消用户收藏接口
	 * */
	@RequestMapping(value = "/1.0/cancleCollection", method = RequestMethod.POST)
	public RongLianResult getCollection(@RequestBody RongLianRequest<Map> requestBody) {
		Map requestMap = null;
		String collectionId = null;
		String accessToken = null;
		String userId = null;
		if (requestBody == null) {
			return RongLianResult.build(200, "The requestBody can not be null");
		}
		requestMap = requestBody.getData();
		accessToken = requestBody.getAccessToken();
        if(requestMap == null){
        	return RongLianResult.build(200, "The data of requestBody can not be null");
        }
		collectionId = (String) requestMap.get("collectionId");
		if(collectionId == null){
			return RongLianResult.build(200, "The param collectionId of data can not be null");
		}
		userId = (String) requestMap.get("userId");
		if (StringUtils.isNotBlank(accessToken)) {
			RongLianResult result = this.userService.getUserInfo(accessToken);
			if (result.getData() == null) {
				return RongLianResult.build(106, "accessToken is error");
			}
			User user = (User) result.getData();
			String userId2 = user.getUserId();
			if (!userId2.equals(userId)) {
				return RongLianResult.build(200, "maybe param userId is error");
			}
		}
		if (StringUtils.isBlank(accessToken) && StringUtils.isNotBlank(userId)) {
			return RongLianResult.build(200, "you have not logined ,so userId should be null ");
		}
		try {
			return this.collectionService.delCollectionById(collectionId);
		} catch (Exception e) {
			return RongLianResult.build(500, "The server maybe error or you have deleted");
		}
	}

}
