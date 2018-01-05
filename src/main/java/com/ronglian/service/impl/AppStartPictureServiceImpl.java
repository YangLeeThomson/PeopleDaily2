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
 * @createTime 2017Äê12ÔÂ28ÈÕ
 */
@Service
public class AppStartPictureServiceImpl implements AppStartPictureService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.AppStartPictureService#addAppStartPicture(com.ronglian.entity.AppStartPicture)
	 */
	@Autowired
	private AppStartPictureDao appStartPictureDao;
	@Override
	public RongLianResult addAppStartPicture(AppStartPicture appStartPicture) {
		// TODO Auto-generated method stub
		AppStartPicture result = null;
		result = this.appStartPictureDao.save(appStartPicture);
		if(result != null){
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(500, "error");
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.AppStartPictureService#getStartPicture()
	 */
	@Override
	public RongLianResult getStartPicture() {
		// TODO Auto-generated method stub
		List<AppStartPicture> list = (List<AppStartPicture>) this.appStartPictureDao.findAll();
		return RongLianResult.ok(list);
	}

}
