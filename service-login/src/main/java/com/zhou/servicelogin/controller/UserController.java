package com.zhou.servicelogin.controller;

import com.ctc.wstx.util.StringUtil;
import com.zhou.servicelogin.bean.User;
import com.zhou.servicelogin.redis.RedisUtils;
import com.zhou.servicelogin.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller

public class UserController {
    @Autowired
    private UserService   userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/test1")
    public String test1(){
        List<User>list=userService.findAll();
        System.out.println(list.get(1));
        return "index";
    }
    /*
    校验用户名密码，如果成功返回同行令牌
     */
    @RequestMapping("/login")
    public String checkLogin(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int userLog = userService.checkUser(user);
        String flag="";
        if (userLog==0||user.equals("")){
            flag=username+System.currentTimeMillis();//返回当前时间
            redisTemplate.opsForValue().set(flag,"1",(long)(3*60), TimeUnit.SECONDS);//新增一个string类型的值
            //set方法有四个字段，flag为key，1为value，第三个为过期时间，最后一个是秒工具类
        }
        return flag;
    }
    /**
     * 跳转登录页面
     */
    @RequestMapping("/loginPage")
    private ModelAndView loginPage(String url) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("url", url);
        return modelAndView;
    }
    /*
    页面登录
     */
    @RequestMapping("/loginCheck")
    private String loginCheck(HttpServletResponse response,String username,String password,String url){
        String check = checkLogin(username,password);
        if (!StringUtils.isEmpty(check)){
            try {
                Cookie cookie = new Cookie("accessToken",check);
                cookie.setMaxAge(60*3);//cookie实效时间
                cookie.setPath("/");
                response.addCookie(cookie);
                url = "success";
                response.sendRedirect(url);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        return  "登录失败";
    }
}
