package com.ronglian.job;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ronglian.dao.NewsInfoDao;
import com.ronglian.jedis.JedisDao;

@Component
@Async
public class PeopleDailyTask {

	@Autowired
	private JedisDao jedisDao;
	@Autowired
	private NewsInfoDao newsInfoDao;
    //每天23点59分59秒时执行
	@Scheduled(cron = "59 59 23 * * ?")
//	@Scheduled(cron = "30 30 19 * * ?")
	public void updateAccessNum(){
//		 System.out.println("current time : "+new Date());
//		 System.out.println(jedisDao);
//		 System.out.println(newsInfoDao);
		Set<String> keys =  this.jedisDao.getKeys("accessNum*");
		Iterator<String> it = keys.iterator();
		 //持久化redis数据到mysql
//		long start = System.currentTimeMillis();
        while(it.hasNext()){  
            String keyStr = it.next();
            String AccessNum = this.jedisDao.get(keyStr);
            String newsId = keyStr.substring(9);
            this.jedisDao.del("newsContent"+newsId);
            this.jedisDao.del(keyStr);
            this.newsInfoDao.updateReadNumByAccessNum(newsId, Integer.parseInt(AccessNum));
        }  
//        long end = System.currentTimeMillis();
//        System.out.println("Job的阅读技术task所用时间:"+ (end - start)/60 +"分钟");
	}
//    @Scheduled(cron = "0/5 * * * * *")
//    public void scheduled(){
//    	System.out.println("=====>>>>>使用cron  {}"+System.currentTimeMillis()+"当前线程："+Thread.currentThread());
//    }
//    @Scheduled(fixedRate = 5000)
//    public void scheduled1() {
//    	System.out.println("=====>>>>>使用fixedRate{}"+System.currentTimeMillis()+"当前线程："+Thread.currentThread());
//    }
//    @Scheduled(fixedDelay = 5000)
//    public void scheduled2() {
//    	System.out.println("=====>>>>>fixedDelay{}"+System.currentTimeMillis()+"当前线程："+Thread.currentThread());
//    }
}
