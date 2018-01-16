/**
 * 
 */
package com.ronglian.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronglian.dao.ChannelDao;
import com.ronglian.entity.Channel;
import com.ronglian.service.ChannelService;
import com.ronglian.utils.RongLianResult;

/**
 * @author liyang
 * @createTime 2017年12月27日
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
		List<Channel> list = this.channelDao.getList();
		if(list != null && list.size() > 0){
			return RongLianResult.ok(list);
		}else{
			return RongLianResult.build(500, "find null");
		}
	}

	@Override
	public RongLianResult addChannelMap(Map requestMap) {
		if(requestMap.get("id")==null||requestMap.get("name")==null||requestMap.get("sort")==null
				||requestMap.get("dataStatus")==null||requestMap.get("uniqueID")==null){
			return RongLianResult.build(500, "缺少数据");
		}
		Channel channel=new Channel((int)requestMap.get("id"), requestMap.get("name").toString(),
				(int)requestMap.get("sort"),requestMap.get("uniqueID").toString(), (int)requestMap.get("dataStatus"));
		Date createTime = new Date();
		Date modiyTime = new Date();
		channel.setCreateTime(createTime);
		channel.setModiyTime(modiyTime);
		Channel result = null;
		result = channelDao.save(channel);
		if(result != null){
			return RongLianResult.build(0, "ok");
		}else{
			return RongLianResult.build(500, "saved error!");
		}
	}
}