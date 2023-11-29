package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.Submission;
import com.example.service.SubmissionService;
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
@RequestMapping("/submission")
public class SubmissionController {
    @Autowired
    SubmissionService service;
    @PostMapping("/list")
    public String list(@RequestBody Submission submission) {
        return JSON.toJSONString(service.selectList(submission));
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList(new Submission()));
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody Submission submission){
        return service.saveOrUpdate(submission);
    }
    @RequestMapping("/delete")
    public int delete(@RequestBody Submission submission){
        return service.delete(submission);
    }

    @PostMapping("/review")
    public boolean review(@RequestBody Submission submission){
        boolean ret = service.saveOrUpdate(submission);
        // todo: add an entry in grade book
        return ret;
    }
}

