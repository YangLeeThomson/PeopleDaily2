/**
 * 
 */
package com.ronglian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.AppStartPictureDao;
import com.ronglian.entity.AppStartPicture;
import com.ronglian.service.AppStartPictureService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��28��
 */
@Service
public class AppStartPictureServiceImpl implements AppStartPictureService {
	@Autowired
	private AppStartPictureDao appStartPictureDao;
	@Override
	public RongLianResult addAppStartPicture(AppStartPicture appStartPicture) {
		AppStartPicture result = null;
		result = this.appStartPictureDao.save(appStartPicture);
		if(result != null){
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(500, "error");
		}
	}
	@Override
	public RongLianResult getStartPicture() {
		List<AppStartPicture> list = (List<AppStartPicture>) this.appStartPictureDao.findAll();
		if(list != null && list.size() > 0){
			return RongLianResult.ok(list);
		}else{
			return RongLianResult.build(500, "����ͼ�����ڣ�ϵͳδ¼������ͼ");
		}
		
	}

}