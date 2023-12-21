package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.example.entity.GradeBook;
import com.example.entity.Homework;
import com.example.entity.Submission;
import com.example.mapper.HomeworkMapper;
import com.example.service.GradeBookService;
import com.example.service.HomeworkService;
import com.example.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @Autowired
    GradeBookService gradeBookService;
    @Autowired
    HomeworkService homeworkService;
    @PostMapping("/review")
    public boolean review(@RequestBody Submission submission){
        boolean ret = service.saveOrUpdate(submission);
        Homework homework = new Homework();
        homework.setHomeworkId(submission.getHomeworkId());
        List<Homework> result = homeworkService.selectList(homework);
        homework = result.get(0);
        GradeBook gradeBook = new GradeBook();
        gradeBook.setClassId(result.get(0).getClassId());
        gradeBook.setStudentId(submission.getStudentId());
        List<GradeBook> result2 = gradeBookService.selectList(gradeBook);
        gradeBook = result2.get(0);
        Map<String, Double> mapGradebook = JSON.parseObject(gradeBook.getGradebookContent(), new TypeReference<Map<String, Double>>() {
        });
        mapGradebook.put(homework.getHomeworkTitle(), submission.getSubmissionScore());
        gradeBook.setGradebookContent(JSON.toJSONString(mapGradebook));
        ret = ret && gradeBookService.saveOrUpdate(gradeBook);
        return ret;
    }
}

