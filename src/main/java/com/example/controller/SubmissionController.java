package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.example.entity.*;
import com.example.mapper.HomeworkMapper;
import com.example.service.*;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    @Autowired
    StudentService studentService;
    @PostMapping("/list")
    public String list(@RequestBody Submission submission) {
        List<Submission> listSubmission = service.selectList(submission);
        List<Map<String, Object>> ret = new ArrayList<>();
        for (Submission submission1: listSubmission){
            Map<String, Object> map = new HashMap<>();
            Student student = studentService.selectStudent(submission1.getStudentId());
            map.put("submissionId", submission1.getSubmissionId());
            map.put("groupId", submission1.getGroupId());
            map.put("studentId", submission1.getStudentId());
            map.put("studentName", student.getStudentName());
            map.put("homeworkId", submission1.getHomeworkId());
            map.put("submissionContent", submission1.getSubmissionContent());
            map.put("submissionComment", submission1.getSubmissionComment());
            map.put("submissionScore", submission1.getSubmissionScore());
            map.put("submissionTime", submission1.getSubmissionTime());
            ret.add(map);
        }
        return JSON.toJSONString(ret);
    }
    @PostMapping("/listLatest")
    public String listLatest(@RequestBody Submission submission) {
        List<Submission> listSubmission = service.selectList(submission);
        List<Map<String, Object>> ret = new ArrayList<>();
        Map<Integer, String> mapSubmissionTime = new HashMap<>();
        for (Submission submission1: listSubmission){
            Map<String, Object> map = new HashMap<>();
            Student student = studentService.selectStudent(submission1.getStudentId());
            map.put("submissionId", submission1.getSubmissionId());
            map.put("groupId", submission1.getGroupId());
            map.put("studentId", submission1.getStudentId());
            map.put("studentName", student.getStudentName());
            map.put("homeworkId", submission1.getHomeworkId());
            map.put("submissionContent", submission1.getSubmissionContent());
            map.put("submissionComment", submission1.getSubmissionComment());
            map.put("submissionScore", submission1.getSubmissionScore());
            map.put("submissionTime", submission1.getSubmissionTime());
            if (submission1.getStudentId()!=null) {
                if (mapSubmissionTime.get(submission1.getStudentId()) == null ||
                        mapSubmissionTime.get(submission1.getStudentId()).compareTo(submission1.getSubmissionTime()) < 0)
                    mapSubmissionTime.put(submission1.getStudentId(), submission1.getSubmissionTime());
            }
            else{
                if (mapSubmissionTime.get(submission1.getGroupId()) == null ||
                        mapSubmissionTime.get(submission1.getGroupId()).compareTo(submission1.getSubmissionTime()) < 0)
                    mapSubmissionTime.put(submission1.getGroupId(), submission1.getSubmissionTime());
            }
            ret.add(map);
        }
        List<Map<String, Object>> ret1 = new ArrayList<>();
        for (Map<String, Object> map1:ret){
            if (map1.get("studentId")!=null) {
                if (map1.get("submissionTime").toString().equals(mapSubmissionTime.get(Integer.parseInt(map1.get("studentId").toString()))))
                    ret1.add(map1);
            }
            else{
                if (map1.get("submissionTime").toString().equals(mapSubmissionTime.get(Integer.parseInt(map1.get("groupId").toString()))))
                    ret1.add(map1);
            }
        }
        return JSON.toJSONString(ret1);
    }
    @Autowired
    GroupService groupService;
    @PostMapping("/recentReview")
    public String recentReview(@RequestParam int studentId){
        Submission submission = new Submission();
        submission.setStudentId(studentId);
        List<Submission> listSubmission = service.selectList(submission);
        List<Submission> ret = new ArrayList<>();
        for (Submission submission1 : listSubmission){
            if (submission1.getReviewTime()!=null){
                ret.add(submission1);
            }
        }
        RelationshipStudentClassGroup relationshipStudentClassGroup = new RelationshipStudentClassGroup();
        relationshipStudentClassGroup.setStudentId(studentId);
        List<RelationshipStudentClassGroup> listRelationshipStudentClassGroup = relationshipStudentClassGroupService.selectList(relationshipStudentClassGroup);
        for (RelationshipStudentClassGroup relationshipStudentClassGroup1:listRelationshipStudentClassGroup){
            Integer groupId = relationshipStudentClassGroup1.getGroupId();
            submission.setGroupId(groupId);
            listSubmission = service.selectList(submission);
            for (Submission submission1 : listSubmission){
                if (submission1.getReviewTime()!=null){
                    ret.add(submission1);
                }
            }
        }
        ret.sort(Comparator.comparing(Submission::getReviewTime));
        return JSON.toJSONString(ret);
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
        Map<Integer, Map<String, Object>> ret = new HashMap<>();
        for (Submission submission1 : listSubmission){
            if (submission1.getSubmissionScore()==null) continue;
            Integer studentId = submission1.getStudentId();
            Map<String, Object> map1;
            if (ret.get(studentId)==null) {
                map1 = new HashMap<>();
                map1.put("score", 0.0);
                map1.put("comment", "");
            }
            else
                map1 = ret.get(studentId);
            if (Double.parseDouble(map1.get("score").toString()) < submission1.getSubmissionScore()) {
                map1.put("score", submission1.getSubmissionScore());
                map1.put("comment", submission1.getSubmissionComment());
            }
            ret.put(studentId, map1);
        }
        return JSON.toJSONString(ret.values());
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
    @Autowired
    RelationshipStudentClassGroupService relationshipStudentClassGroupService;
    @PostMapping("/review")
    public boolean review(@RequestBody Submission submission){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = localDateTime.format(formatter);
        submission.setReviewTime(currentTime);
        boolean ret = service.saveOrUpdate(submission);
        Homework homework = new Homework();
        homework.setHomeworkId(submission.getHomeworkId());
        List<Homework> result = homeworkService.selectList(homework);
        homework = result.get(0);
        if (submission.getStudentId()!=null) {
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
        }
        else{
            RelationshipStudentClassGroup relationshipStudentClassGroup = new RelationshipStudentClassGroup();
            relationshipStudentClassGroup.setGroupId(submission.getGroupId());
            List<RelationshipStudentClassGroup> listRelationshipStudentClassGroup = relationshipStudentClassGroupService.selectList(relationshipStudentClassGroup);
            for (RelationshipStudentClassGroup relationshipStudentClassGroup1 : listRelationshipStudentClassGroup){
                Integer studentId = relationshipStudentClassGroup1.getStudentId();
                GradeBook gradeBook = new GradeBook();
                gradeBook.setClassId(result.get(0).getClassId());
                gradeBook.setStudentId(studentId);
                List<GradeBook> result2 = gradeBookService.selectList(gradeBook);
                if (result2.isEmpty()) {
                    gradeBook.setGradebookContent("{}");
                    System.out.println("fuck"+gradeBook);
                    gradeBookService.saveOrUpdate(gradeBook);
                }
                else
                    gradeBook = result2.get(0);
                Map<String, Double> mapGradebook = JSON.parseObject(gradeBook.getGradebookContent(), new TypeReference<Map<String, Double>>() {
                });
                mapGradebook.put(homework.getHomeworkTitle(), submission.getSubmissionScore());
                gradeBook.setGradebookContent(JSON.toJSONString(mapGradebook));
                ret = ret && gradeBookService.saveOrUpdate(gradeBook);
            }
        }
        return ret;
    }
}

