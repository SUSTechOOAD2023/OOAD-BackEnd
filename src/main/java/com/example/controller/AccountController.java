package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.Account;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.service.AccountService;
import com.example.service.StudentService;
import com.example.service.TeacherService;
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
    @Autowired
    TeacherService service1;
    @Autowired
    StudentService service2;
    @RequestMapping("/list")
    public String full_list() {
        return JSON.toJSONString(service.selectList());
    }
    //
    @PostMapping("/new")
    public boolean add_account(Account account) {
        System.out.println(account.toString());
//        Account account2 = account;
//        System.out.println(account.toString());
//        account2.setAccountPassword(account.getAccountPassword().hashCode()+"");
//        service.saveOrUpdate(account2);
//        int id=account2.getAccountId();
//        //每当创建一个account，对应创建一个teacher或student对象
//        if(account2.getAccountType().equals("teacher")){
//            Teacher teacher=new Teacher();
//            teacher.setAccountId(id);
//            service1.saveOrUpdate(teacher);
//            int ID=teacher.getTeacherId();
//            account2.setTeacherId(ID);
//            service.saveOrUpdate(account2);
//        }else if(account2.getAccountType().equals("student")){
//            Student student=new Student();
//            service2.saveOrUpdate(student);
//            //TODO:在此处添加SA
//        }else {}
//        return service.saveOrUpdate(account2);
        return true;
    }

    @PostMapping("/signin")
    public String login(Account account) {
        if (!service.isAccountExist(account.getAccountName())) {
            return "The account doesn't exist!";
        } else if (!service.isCorrect(account.getAccountName(), account.getAccountPassword())) {
            return "Wrong account name or password!";
        } else {
            return "success!";
        }
    }
    //
    @PostMapping("/accountName")
    public String name(Account account) {
        if (!service.isAccountExist(account.getAccountName())) {
            return "用户名不存在";
        } else {
            return "success!";
        }
    }

}