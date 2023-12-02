package com.example.controller;


import com.example.service.InviteCodeService;
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
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/inviteCode")
public class InviteCodeController {
    @Autowired
    InviteCodeService service;
    @RequestMapping("/add")
    public String add_inviteCode(){
        service.generateAndSaveInviteCodes(20);
        return "success";
    }

    @PostMapping("/check")
    //给出邀请码，身份，SID，email以及密码，请求注册，返回邀请码不正确（包括与身份不匹配）/用户已存在/成功
    public String check_inviteCode(String inviteCode,String identity){
        if(!service.isInviteCodeExist(inviteCode)){
            return "邀请码不存在";
        }
        if(service.isUsed(inviteCode)){
            return "邀请码已被使用";
        }
        if(!service.isCorrect(inviteCode,identity)){
            return "邀请码与身份不匹配";
        }
        return "success";
    }

}

