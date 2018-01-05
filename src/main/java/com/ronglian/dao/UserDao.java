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
 * @createTime 2017��12��22��
 */
public interface UserDao extends CrudRepository<User, Integer>{

    @Transactional
    @Modifying
    @Query("update User u set u.userPhoto = :userPhoto where u.userId = :userId")
    int updateScoreById(@Param("userPhoto") float userPhoto, @Param("userId") int userId);

    @Query("select u from User u ")
    List<User> getList();

}
