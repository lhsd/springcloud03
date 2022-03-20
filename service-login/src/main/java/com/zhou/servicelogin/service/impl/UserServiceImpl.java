package com.zhou.servicelogin.service.impl;

import com.zhou.servicelogin.bean.User;
import com.zhou.servicelogin.dao.UserMapper;
import com.zhou.servicelogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public int checkUser(User user) {
        return userMapper.checkUser(user);
    }


}
