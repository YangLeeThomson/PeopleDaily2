/**
 * 
 */
package com.ronglian.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronglian.dao.UserChannelConfigDao;
import com.ronglian.entity.UserChannelConfig;
import com.ronglian.service.UserChannelConfigService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017��12��29��
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
		if(list !=null && list.size() > 0){
			List<UserChannelConfig> result = (List<UserChannelConfig>) this.userChannelConfigDao.save(list);
			if(result == null){
				return RongLianResult.build(500, "save�û��Զ�����Ŀʧ�ܣ�");
			}
			return RongLianResult.ok();
		}else{
			return RongLianResult.build(500, "��������Ϊ�ջ���ȷ");
		}
		
		
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.UserChannelConfigService#getUserChannelConfig(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public RongLianResult getUserChannelConfig(String deviceId, String userId) {
		// TODO Auto-generated method stub
		List<UserChannelConfig> result = null;
		if(StringUtils.isNotBlank(deviceId)  ){
			if(StringUtils.isNotBlank(userId)){
				result = this.userChannelConfigDao.selectUserChannelConfigByUserId(userId);
				return RongLianResult.ok(result);
			}
			result = this.userChannelConfigDao.selectUserChannelConfigByDeviceId(deviceId);
			return RongLianResult.ok(result);
		}else{
			return RongLianResult.build(500, "�����������ȷ");
		}
	}

}
