package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.GradeBook;
import com.example.service.GradeBookService;
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
@RequestMapping("/gradeBook")
public class GradeBookController {
    @Autowired
    GradeBookService service;
    @PostMapping("/list")
    public String list(@RequestBody GradeBook gradeBook) {
        return JSON.toJSONString(service.selectList(gradeBook));
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList(new GradeBook()));
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody GradeBook gradeBook){
        //System.out.println(gradeBook.toString());
        return service.saveOrUpdate(gradeBook);
    }
    @RequestMapping("/delete")
    public int delete(@RequestBody GradeBook gradeBook){
        return service.delete(gradeBook);
    }
}

