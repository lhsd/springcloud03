package com.zhou.servicelogin.service;

import com.zhou.servicelogin.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public int checkUser(User user);
}
