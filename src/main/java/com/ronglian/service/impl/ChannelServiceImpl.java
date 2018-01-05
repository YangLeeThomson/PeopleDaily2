/**
 * 
 */
package com.ronglian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronglian.dao.ChannelDao;
import com.ronglian.entity.Channel;
import com.ronglian.service.ChannelService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017Äê12ÔÂ27ÈÕ
 */
@Service
public class ChannelServiceImpl implements ChannelService {

	/* (non-Javadoc)
	 * @see com.ronglian.service.ChannelService#addChannel(com.ronglian.entity.Channel)
	 */
	@Autowired
	private ChannelDao channelDao;
	@Override
	public RongLianResult addChannel(Channel channel) {
		// TODO Auto-generated method stub
		Channel result = null;
		result = channelDao.save(channel);
		if(result != null){
			return RongLianResult.build(0, "ok");
		}else{
			return RongLianResult.build(500, "saved error!");
		}
	}
	/* (non-Javadoc)
	 * @see com.ronglian.service.ChannelService#getChannelList()
	 */

	@Override
	public RongLianResult getChannelList() {
		// TODO Auto-generated method stub
//		Object obj = this.channelDao.findAll();		
//		List<Channel> list = (List<Channel>) obj;
		List<Channel> list = this.channelDao.getList();
		if(list != null && list.size() > 0){
			return RongLianResult.ok(list);
		}else{
			return RongLianResult.build(500, "find error");
		}
	}

}
