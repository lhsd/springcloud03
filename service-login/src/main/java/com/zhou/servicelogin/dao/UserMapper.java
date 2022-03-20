package com.zhou.servicelogin.dao;

import com.zhou.servicelogin.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    //查询所有用户
    public List<User> findAll();
    //验证登录
    public int checkUser(User user);

}
