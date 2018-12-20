package com.coder520.jxc.service;

import com.coder520.jxc.entity.User;
import org.springframework.data.domain.Sort;

import java.util.List;


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

    /**
     * 根据条件分页查询用户信息
     * @param user
     * @param page
     * @param pageSize
     * @param direction
     * @param properties
     * @return
     */
    public List<User> list(User user, Integer page, Integer pageSize, Sort.Direction direction,String...properties);

    /**
     * 获取总记录数
     * @param user
     * @return
     */
    public Long getCount(User user);
}
