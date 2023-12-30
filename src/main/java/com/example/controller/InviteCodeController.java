package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.InviteCode;
import com.example.service.InviteCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String check_inviteCode(@RequestBody InviteCode inviteCode){
        if(!service.isInviteCodeExist(inviteCode.getCode())){
            return "邀请码不存在";
        }
        if(service.isUsed(inviteCode.getCode())){
            return "邀请码已被使用";
        }
        if(!service.isCorrect(inviteCode.getCode(),inviteCode.getIdentity())){
            return "邀请码与身份不匹配";
        }
        return "success";
    }


    @PostMapping("/get")
    //返回k个未被使用的邀请码
    public String get_inviteCode(@RequestParam int k,@RequestParam String identity){
        return JSON.toJSONString(service.getInviteCode(k,identity));
    }

}

