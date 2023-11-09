package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.Account;
import com.example.service.AccountService;
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
 * @since 2023-10-24
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService service;
    @RequestMapping("/list")
    public String full_list() {
        return JSON.toJSONString(service.selectList());
    }
//
//    @PostMapping("/new")
//    public boolean add_account(Account account) {
//        Account account2 = account;
//        account2.setAccountPassword(account.getAccountPassword().hashCode()+"");
//        return service.saveOrUpdate(account2);
//    }
//
//    @PostMapping("/signin")
//    public String login(Account account) {
//        if (!service.isAccountNameExists(account.getAccountName())) {
//            return "The Accountname doesn't exist!";
//        } else if (!service.isCorrect(account.getAccountName(), account.getAccountPassword())) {
//            return "Wrong accountname or password!";
//        } else {
//            return "success!";
//        }
//    }
//
//    @PostMapping("/accountName")
//    public String name(Account account) {
//        if (!service.isAccountNameExists(account.getAccountName())) {
//            return "用户名不存在";
//        } else {
//            return "success!";
//        }
//    }

}

