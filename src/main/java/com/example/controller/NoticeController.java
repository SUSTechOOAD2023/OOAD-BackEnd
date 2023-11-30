package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.Notice;
import com.example.service.NoticeService;
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
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    NoticeService service;
    @PostMapping("/list")
    public String list(@RequestBody Notice notice) {
        return JSON.toJSONString(service.selectList(notice));
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList(new Notice()));
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody Notice notice){
        System.out.println(notice.toString());
        return service.saveOrUpdate(notice);
    }
    @RequestMapping("/delete")
    public int delete(@RequestBody Notice notice){
        return service.delete(notice);
    }
}

