package com.ronglian.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ronglian.entity.User;

/**
 * @author liyang
 * @createTime 2017年12月22日
 */
public interface UserDao extends CrudRepository<User, String>{

   
    @Query("select u from User u ")
    List<User> getList();
    
    @Query("select u from User u where u.openid = :openid")
    User getUserByOpenid(@Param("openid") String openid);
    
    @Query("select u from User u where u.twOpenid = :twOpenid")
    User getUserBytwOpenid(@Param("twOpenid") String twOpenid);
    
    @Query("select u from User u where u.faceOpenid = :faceOpenid")
    User getUserByfaceOpenid(@Param("faceOpenid") String faceOpenid);

   
    
}
