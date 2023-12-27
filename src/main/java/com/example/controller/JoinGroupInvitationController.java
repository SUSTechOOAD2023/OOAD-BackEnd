package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.JoinGroupInvitation;
import com.example.service.JoinGroupInvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/joinGroupInvitation")
public class JoinGroupInvitationController {
    @Autowired
    JoinGroupInvitationService service;
    @PostMapping("/list")
    public String list(@RequestBody JoinGroupInvitation joinGroupInvitation) {
        return JSON.toJSONString(service.selectList(joinGroupInvitation));
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList(new JoinGroupInvitation()));
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody JoinGroupInvitation joinGroupInvitation){
        System.out.println(joinGroupInvitation.toString());
        return service.saveOrUpdate(joinGroupInvitation);
    }
    @RequestMapping("/delete")
    public int delete(@RequestBody JoinGroupInvitation joinGroupInvitation){
        return service.delete(joinGroupInvitation);
    }
}

