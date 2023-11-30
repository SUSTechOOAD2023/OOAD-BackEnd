package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.CourseClass;
import com.example.service.CourseClassService;
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
@RequestMapping("/class")
public class CourseClassController {
    @Autowired
    CourseClassService service;
    @PostMapping("/list")
    public String list(@RequestBody CourseClass courseClass) {
        return JSON.toJSONString(service.selectList(courseClass));
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList(new CourseClass()));
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody CourseClass courseClass){
        return service.saveOrUpdate(courseClass);
    }
    @RequestMapping("/delete")
    public int delete(@RequestBody CourseClass courseClass){
        return service.delete(courseClass);
    }

}

