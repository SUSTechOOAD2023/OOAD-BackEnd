package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.Account;
import com.example.entity.InviteCode;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.service.AccountService;
import com.example.service.InviteCodeService;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;

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
    @Autowired
    InviteCodeService service3;
    @RequestMapping("/list")
    public String full_list() {
        return JSON.toJSONString(service.selectList());
    }
    //
    @PostMapping("/new")
    public String add_account(@RequestBody Account account, @RequestBody InviteCode inviteCode) {
        if(service.isAccountExist(account.getAccountName())){
            return "SID已存在";
        }
        if(service.isEmailExist(account.getEmail())){
            return "邮箱已被注册";
        }
        if(!service3.isInviteCodeExist(inviteCode.getCode())){
            return "邀请码不存在";
        }
        if(service3.isUsed(inviteCode.getCode())){
            return "邀请码已被使用";
        }
        if(!service3.isCorrect(inviteCode.getCode(),inviteCode.getIdentity())){
            return "邀请码与身份不匹配";
        }
        System.out.println(account.toString());
        account.setAccountType(inviteCode.getIdentity());
        Account account2 = account;
        account2.setAccountPassword(account.getAccountPassword().hashCode()+"");
        service.saveOrUpdate(account2);
        int id=account2.getAccountId();
        //每当创建一个account，对应创建一个teacher或student对象
        if(account2.getAccountType().equals("teacher")){
            Teacher teacher=new Teacher();
            teacher.setAccountId(id);
            service1.saveOrUpdate(teacher);
            int ID=teacher.getTeacherId();
            account2.setTeacherId(ID);
            service.saveOrUpdate(account2);
        }else if(account2.getAccountType().equals("student")){
            Student student=new Student();
            student.setAccountId(id);
            service2.saveOrUpdate(student);
            int ID=student.getStudentId();
            account2.setStudentId(ID);
            service.saveOrUpdate(account2);
            //TODO:在此处添加SA
        }else {}
//        return service.saveOrUpdate(account2);
        return "success!";
    }




    @PostMapping("/emailSignIn")
    public String emailLogin(@RequestBody Account account) {
        if(account.getCookie()!=null){
            return "success!";
        }
        if (!service.isEmailExist(account.getEmail())) {
            return "The email doesn't exist!";
        } else if (!service.emailIsCorrect(account.getEmail(), account.getAccountPassword())) {
            return "Wrong email or password!";
        } else {
            return "success!";
        }
    }


    @PostMapping("/signin")
    public String login(@RequestBody Account account) {
        if(account.getCookie()!=null){
            return "success!";
        }
        if (!service.isAccountExist(account.getAccountName())) {
            return "The account doesn't exist!";
        } else if (!service.isCorrect(account.getAccountName(), account.getAccountPassword())) {
            return "Wrong account name or password!";
        } else {
            return "success!";
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody LoginCredentials credentials, HttpServletResponse response) {
//        // 登录逻辑（验证用户等）
//
//        // 创建并设置Cookie
//        Cookie cookie = new Cookie("userSession", "sessionValue"); // 使用合适的值
//        cookie.setHttpOnly(true); // 安全设置，防止客户端脚本访问此Cookie
//        cookie.setMaxAge(7 * 24 * 60 * 60); // 设置有效期，例如一周
//        cookie.setPath("/"); // 设置Cookie适用的路径
//        response.addCookie(cookie);
//
//        return ResponseEntity.ok().body("User logged in successfully.");
//    }

    //
    @PostMapping("/accountName")
    public String name(Account account) {
        if (!service.isAccountExist(account.getAccountName())) {
            return "用户名不存在";
        } else {
            return "success!";
        }
    }

    @PostMapping("/logout")
    public void logout(Account account) {
        account.setCookie(null);
        service.saveOrUpdate(account);
    }

}