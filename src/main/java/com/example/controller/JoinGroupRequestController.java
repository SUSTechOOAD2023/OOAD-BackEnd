package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.JoinGroupRequest;
import com.example.service.JoinGroupRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
@RestController
@RequestMapping("/joinGroupRequest")
public class JoinGroupRequestController {
    @Autowired
    JoinGroupRequestService service;
    @PostMapping("/list")
    public String list(@RequestBody JoinGroupRequest joinGroupRequest) {
        return JSON.toJSONString(service.selectList(joinGroupRequest));
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList(new JoinGroupRequest()));
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody JoinGroupRequest joinGroupRequest){
        System.out.println(joinGroupRequest.toString());
        joinGroupRequest.setRequestTime(Timestamp.valueOf(LocalDateTime.now()));
        return service.saveOrUpdate(joinGroupRequest);
    }
    @RequestMapping("/delete")
    public int delete(@RequestBody JoinGroupRequest joinGroupRequest){
        return service.delete(joinGroupRequest);
    }
}

