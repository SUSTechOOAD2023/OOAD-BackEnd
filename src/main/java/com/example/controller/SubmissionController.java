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
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.lang.Math.*;

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
    @PostMapping("/listScore")
    public String listScore(@RequestParam int homeworkId) {
        Submission submission = new Submission();
        submission.setHomeworkId(homeworkId);
        List<Submission> listSubmission = service.selectList(submission);
        Map<Integer, Map<String, Object>> retMapStudent = new HashMap<>(), retMapGroup = new HashMap<>();

        for (Submission submission1 : listSubmission){
            Map<String, Object> map = new HashMap<>();
            System.out.println(submission1.toString());
            if (submission1.getSubmissionScore() != null) {
                map.put("score", submission1.getSubmissionScore());
                map.put("comment", submission1.getSubmissionComment());
                if (submission1.getStudentId() != null) {
                    retMapStudent.put(submission1.getStudentId(), map);
                } else {
                    retMapGroup.put(submission1.getGroupId(), map);
                }
            }
        }
        List<Map<String, Object>> lis = new ArrayList<>();
        for (Map.Entry<Integer, Map<String, Object>> mapEntry : retMapStudent.entrySet()){
            Map<String, Object> map1 = new HashMap<>();
            map1.put("studentId", mapEntry.getKey());
            map1.put("score", mapEntry.getValue().get("score"));
            map1.put("comment", mapEntry.getValue().get("comment"));
            lis.add(map1);
        }
        return JSON.toJSONString(lis);
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
    @PostMapping("/queryMaxScore")
    public String queryMaxScore(@RequestBody Submission submission) {
        List<Submission> listSubmission = service.selectList(submission);
        double ret = 0.0;
        String comment = "";
        for (Submission submission1 : listSubmission){
            if (ret < submission1.getSubmissionScore()) {
                ret = submission1.getSubmissionScore();
                comment = submission1.getSubmissionComment();
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("score", ret);
        map.put("comment", comment);
        return JSON.toJSONString(map);
    }

    @PostMapping("/queryLatestScore")
    public String queryLatestScore(@RequestBody Submission submission) {
        List<Submission> listSubmission = service.selectList(submission);
        double ret = 0.0;
        String tim = "0000/00/00 00:00:00";
        String comment = "";
        for (Submission submission1 : listSubmission){
            if (tim.compareTo(submission1.getSubmissionTime())<0) {
                ret = submission1.getSubmissionScore();
                tim = submission1.getSubmissionTime();
                comment = submission1.getSubmissionComment();
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("score", ret);
        map.put("comment", comment);
        return JSON.toJSONString(map);
    }

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

