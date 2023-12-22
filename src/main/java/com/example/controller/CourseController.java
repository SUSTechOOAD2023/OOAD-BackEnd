package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.Course;
import com.example.service.CourseService;
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
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService service;
    @PostMapping("/list")
    public String list(@RequestBody Course course) {
        return JSON.toJSONString(service.selectList(course));
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList(new Course()));
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody Course course){
        System.out.printf("%d\n", course.getCourseId());
        return service.saveOrUpdate(course);
    }
    @RequestMapping("/delete")
    public int delete(@RequestBody Course course){
        return service.delete(course);
    }
}

