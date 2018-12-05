package com.coder520.jxc.service.impl;

import com.coder520.jxc.entity.User;
import com.coder520.jxc.repository.UserRepository;
import com.coder520.jxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户Service的实现类
 * @author liugang
 * @create 2018/12/5 23:13
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
