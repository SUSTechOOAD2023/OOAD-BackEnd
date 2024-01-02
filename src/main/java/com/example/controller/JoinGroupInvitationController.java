package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.JoinGroupInvitation;
import com.example.service.JoinGroupInvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/delete")
    public int delete(@RequestBody JoinGroupInvitation joinGroupInvitation){
        return service.delete(joinGroupInvitation);
    }

    @PostMapping("/add")
    public int addInvitation(@RequestParam int receiveStudentId,@RequestParam int sendStudentId,@RequestParam int groupId){
        return service.addInvitation(receiveStudentId,sendStudentId,groupId);
    }


    @PostMapping("/searchSend")
    public String searchSend(@RequestParam int sendStudentId){
        return JSON.toJSONString(service.searchSend(sendStudentId));
    }

    @PostMapping("/searchReceive")
    public String searchReceive(@RequestParam int receiveStudentId){
        return JSON.toJSONString(service.searchReceive(receiveStudentId));
    }


    @PostMapping("/accept")
    public String accept(@RequestParam int joinGroupInvitationId){
        return service.acceptInvitation(joinGroupInvitationId);
    }




}

