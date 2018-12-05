package com.coder520.jxc.service;

import com.coder520.jxc.entity.User;


/**
 * 用户Service接口
 * @author liugang
 * @create 2018/12/5 23:07
 **/
public interface UserService {

    /**
     * 根据用户名查找实体
     * @param userName 用户名
     * @return User实体
     */
    public User findByUserName(String userName);
}
