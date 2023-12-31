package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.*;
import com.example.service.*;
import com.example.util.AjaxJson;
import com.example.util.MinioUtils;
import com.example.util.MyMailService;
import io.swagger.annotations.ApiOperation;
import com.example.util.AjaxJson;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * <p>
 * 前端控制器
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
    @Autowired
    MyMailService mailService;
    @Autowired
    VerifyCodeService verifyCodeService;
    @Autowired
    AdminService adminService;
    @RequestMapping("/listAll")
    public String full_list() {
        return JSON.toJSONString(service.selectList());
    }

    @ApiOperation(value = "返回某个账号除去密码以外的信息", tags = "账号类")
    @RequestMapping("/list")
    public String list(HttpSession session) {
        if(session.getAttribute("account")==null){
            return "未登录";
        }
        Account account=(Account) session.getAttribute("account");
        Account account1=account;
        account1.setAccountPassword("");
        return JSON.toJSONString(account1);
    }

    //
    @PostMapping("/new")
    public String add_account(@RequestBody Account account, @RequestParam String code, @RequestParam String identity) {
        if (!service3.isInviteCodeExist(code)) {
            return "邀请码不存在!";
        }
        if (service3.isUsed(code)) {
            return "邀请码已被使用!";
        }
        if (!service3.isCorrect(code, identity)) {
            return "邀请码与身份不匹配!";
        }

        if (service.isAccountExist(identity, account.getAccountName())) {
            return "该身份下的账号名已存在!";
        }
        if (service.isEmailExist(identity, account.getEmail())) {
            return "该身份下的邮箱已被注册!";
        }

        InviteCode inviteCode = service3.findID(code);
        inviteCode.setIsUsed(1);
        service3.saveOrUpdate(inviteCode);
        System.out.println(account.toString());
        account.setAccountType(identity);
        Account account2 = account;
        account2.setAccountPassword(account.getAccountPassword().hashCode() + "");
        service.saveOrUpdate(account2);
        int id = account2.getAccountId();
        //每当创建一个account，对应创建一个teacher或student对象
        if (account2.getAccountType().equals("teacher")) {
            Teacher teacher = new Teacher();
            teacher.setAccountId(id);
            service1.saveOrUpdate(teacher);
            int ID = teacher.getTeacherId();
            account2.setTeacherId(ID);
            service.saveOrUpdate(account2);
        } else if (account2.getAccountType().equals("student")) {
            Student student = new Student();
            student.setAccountId(id);
            service2.saveOrUpdate(student);
            int ID = student.getStudentId();
            account2.setStudentId(ID);
            service.saveOrUpdate(account2);
            //TODO:在此处添加SA
        } else if(account2.getAccountType().equals("SA")){
            Student student = new Student();
            student.setAccountId(id);
            student.setIsSa(1);
            service2.saveOrUpdate(student);
            int ID = student.getStudentId();
            account2.setStudentId(ID);
            service.saveOrUpdate(account2);
        }else if (account2.getAccountType().equals("admin")){
            Admin admin=new Admin();
            admin.setAccountId(id);
            adminService.saveOrUpdate(admin);
            int ID=admin.getAdminId();
            account2.setAdminId(ID);
            service.saveOrUpdate(account2);
        }
//        return service.saveOrUpdate(account2);
        return "Success!";
    }


    @PostMapping("/emailSignIn")
    public String emailLogin(@RequestBody Account account, @RequestParam String identity, HttpSession session) {
        if (account.getCookie() != null) {
            return "success!";
        }
        if (!service.isEmailExist(identity, account.getEmail())) {
            return "The email doesn't exist!";
        } else if (!service.emailIsCorrect(account.getEmail(), account.getAccountPassword())) {
            return "Wrong email or password!";
        } else {
            session.setAttribute("account", service.selectEmailAccount(identity, account.getEmail()));
            return "success!";
        }
    }


    @PostMapping("/signin")
    public String login(@RequestBody Account account, @RequestParam String identity, HttpSession session) {
        if (account.getCookie() != null) {
            return "success!";
        }
        if (!service.isAccountExist(identity, account.getAccountName())) {
            return "The account doesn't exist!";
        } else if (!service.isCorrect(account.getAccountName(), account.getAccountPassword())) {
            return "Wrong account name or password!";
        } else {
            //TODO:在此处维护会话
            session.setAttribute("account", service.selectAccount(identity, account.getAccountName()));
            return "success!";
        }
    }

    @PostMapping("/modifyPassword")
    public String modifyPassword(@RequestParam String oldPassword,@RequestParam String newPassword, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return "Not logged in!";
        }
        if(account.getAccountPassword().equals(oldPassword.hashCode()+"")){
            account.setAccountPassword(newPassword.hashCode()+"");
            service.saveOrUpdate(account);
            return "Password is modified successfully!";
        }else {
            return "Wrong password!";
        }
    }
@ApiOperation(value = "发送邮箱验证码", tags = "账号类")
    @PostMapping("/sendMail")
    //通过发送邮箱验证码的方式修改密码
//    TODO:在此处添加邮箱验证码的逻辑
    public void sendMail(@RequestParam String email,@RequestParam String identity) {
        if (!service.isEmailExist(identity, email)) {
            System.out.println("The email doesn't exist!");
        }
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length=8;
        StringBuilder captcha = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            captcha.append(characters.charAt(random.nextInt(characters.length())));
        }
        String verifyCode = captcha.toString();
        //将验证码存入数据库
        VerifyCode verifyCode1=new VerifyCode();
        verifyCode1.setEmail(email);
        verifyCode1.setIdentity(identity);
        verifyCode1.setVerifyCode(verifyCode);
        verifyCodeService.saveOrUpdate(verifyCode1);
        mailService.sendResetMail(verifyCode, email);
    }

@ApiOperation(value = "通过邮箱验证码修改密码", tags = "账号类")
    @PostMapping("/forgetPassword")
    //通过发送邮箱验证码的方式修改密码
//    TODO:在此处添加邮箱验证码的逻辑
    public String forgetPassword(@RequestParam String email,@RequestParam String identity, @RequestParam String verifyCode,@RequestParam String newPassword) {
        Account account=service.selectEmailAccount(identity,email);
        if (account==null){
            return "The email doesn't exist!";
        }
        if(!verifyCodeService.isVerifyCodeExist(email,identity,verifyCode)){
            return "Wrong verify code!";
        }
        account.setAccountPassword(newPassword.hashCode()+"");
        service.saveOrUpdate(account);
        verifyCodeService.deleteVerifyCode(email,identity,verifyCode);
        return "Password is reset successfully!";
    }


    @GetMapping("/getID")
    public int getID(HttpSession session) {
        Account account=(Account) session.getAttribute("account");
        if(account==null){
            return -1;
        }
        return account.getAccountId();
    }

    @GetMapping("/checkLogin")
    public String checkLogin(HttpSession session) {
        // 检查会话中是否存在已登录的用户信息
        Account account = (Account) session.getAttribute("account");
        System.out.println(account);
        if (account != null) {
            return "存在";
        } else {
            return "不存在";
        }
    }

    @GetMapping("/getIdentity")
    public String getIdentity(HttpSession session) {
        // 检查会话中是否存在已登录的用户信息
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            return account.getAccountType();
        } else {
            return "不存在";
        }
    }




//    @ApiOperation(value = "下载用户图片，id为空时下载当前用户头像", tags = "用户类")
//    @GetMapping("/userimg/download")
//    public AjaxJson download(String userId) {
//        if (userId == null) {
//            userId = (String) StpUtil.getLoginId();
//        }
//        return AjaxJson.getSuccessData(minioUtilS.download(String.format("%s.jpeg", userId), userImgPath));
//    }
//
//    @ApiOperation(value = "上传用户图片", tags = "用户类")
//    @PostMapping("/userimg/upload")
//    public AjaxJson upload(MultipartFile file) {
//        minioUtilS.upload(file, userImgPath, String.format("%s.jpeg", StpUtil.getLoginId()));
//        return AjaxJson.getSuccess();
//    }




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
//    @PostMapping("/accountName")
//    public String name(Account account) {
//        if (!service.isAccountExist(account.getAccountName())) {
//            return "用户名不存在";
//        } else {
//            return "success!";
//        }
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 使当前会话失效，实现用户登出
        session.invalidate();
        return "登出成功";
    }

}