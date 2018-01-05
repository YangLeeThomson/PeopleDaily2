/**
 * 
 */
package com.ronglian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.UserChannelConfigDao;
import com.ronglian.entity.UserChannelConfig;
import com.ronglian.service.UserChannelConfigService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ29ÈÕ
 */
@Service
public class UserChannelConfigServiceImpl implements UserChannelConfigService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.UserChannelConfigService#addUserChannelConfigService(java.util.List)
	 */
	@Autowired
	private UserChannelConfigDao userChannelConfigDao;
	@Override
	public RongLianResult addUserChannelConfig(List<UserChannelConfig> list) {
		// TODO Auto-generated method stub
		List<UserChannelConfig> result = (List<UserChannelConfig>) this.userChannelConfigDao.save(list);
		return RongLianResult.ok();
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.UserChannelConfigService#getUserChannelConfig(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public RongLianResult getUserChannelConfig(String deviceId, String tokenId, String userId) {
		// TODO Auto-generated method stub
		List<UserChannelConfig> result = this.userChannelConfigDao.selectUserChannelConfig(deviceId,userId);
		return RongLianResult.ok(result);
	}

}
