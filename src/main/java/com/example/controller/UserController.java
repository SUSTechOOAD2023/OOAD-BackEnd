package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sending
 * @since 2023-10-13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;
    @RequestMapping("/list")
    public String full_list() {
        return JSON.toJSONString(service.selectList());
    }

    @PostMapping("/new")
    public boolean add_user(User user) {
        User user2 = user;
        user2.setUserPassword(user.getUserPassword().hashCode()+"");
        return service.saveOrUpdate(user2);
    }

    @PostMapping("/signin")
    public String login(User user) {
        if (!service.isUserNameExists(user.getUserName())) {
            return "The username doesn't exist!";
        } else if (!service.isCorrect(user.getUserName(), user.getUserPassword())) {
            return "Wrong username or password!";
        } else {
            return "success!";
        }
    }

    @PostMapping("/userName")
    public String name(User user) {
        if (!service.isUserNameExists(user.getUserName())) {
            return "用户名不存在";
        } else {
            return "success!";
        }
    }

}

