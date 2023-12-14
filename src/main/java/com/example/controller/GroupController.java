package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.Group;
import com.example.service.GroupService;
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
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupService service;
    @PostMapping("/list")
    public String list(@RequestBody Group group) {
        return JSON.toJSONString(service.selectList(group));
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList(new Group()));
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody Group group){
        System.out.println(group.toString());
        return service.saveOrUpdate(group);
    }
    @RequestMapping("/delete")
    public int delete(@RequestBody Group gradeBook){
        return service.delete(group);
    }
}

