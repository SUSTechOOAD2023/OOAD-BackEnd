package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.entity.*;
import com.example.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService service1;
    @Autowired
    AccountService service2;


    @ApiOperation(value = "保存并更新某个学生的信息", tags = "学生类")
    @PostMapping("/update")
    public String updateStudent(@RequestBody Student student){
        if(!service1.isStudentExist(student.getStudentId())){
            return "学生不存在";
        }
        service1.saveOrUpdate(student);
        return "更新成功";
    }
    @ApiOperation(value = "返回某个学生的信息", tags = "学生类")
    @RequestMapping("/list")
    public String list(@RequestParam int studentId){
        if (!service1.isStudentExist(studentId)){
            return "学生不存在";
        }
        Student student=service1.selectStudent(studentId);
        Account account=service2.selectAccount(student.getAccountId());
        Account account1=new Account();
        account1=account;
        account1.setAccountPassword("");
        // 将对象转换为JSON对象
        JSONObject accountJson = JSONObject.parseObject(JSON.toJSONString(account1));
        JSONObject studentJson = JSONObject.parseObject(JSON.toJSONString(student));

        // 创建新的JSON对象并合并数据
        JSONObject mergedJson = new JSONObject();
        mergedJson.put("account", accountJson);
        mergedJson.put("student", studentJson);

        // 将合并后的JSON对象转换为字符串
        return mergedJson.toJSONString();
    }

    @ApiOperation(value = "返回某个学生的ID", tags = "学生类")
    @PostMapping("/getStudentID")
    public int getStudentID(@RequestParam int accountID){
        Account account=service2.selectAccount(accountID);
        if(account.getStudentId()!=null){
            return account.getStudentId();
        }else {
            return -1;
        }
    }

    @ApiOperation(value = "返回全部学生的信息", tags = "学生类")
    @GetMapping("/listAllStudent")
    public List<Student> listAllStudent(){
        Student student = new Student();
        student.setIsSa(0);
        return service1.selectList(student);
    }

    @ApiOperation(value = "返回全部SA的信息", tags = "学生类")
    @GetMapping("/listAllSA")
    public List<Student> listAllSA(){
        Student student = new Student();
        student.setIsSa(1);
        return service1.selectList(student);
    }


    @Autowired
    RelationshipStudentNoticeService relationshipStudentNoticeService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    RelationshipCourseService relationshipCourseService;
    @Autowired
    HomeworkService homeworkService;
    @Autowired
    RelationshipStudentClassGroupService relationshipStudentClassGroupService;
    @Autowired
    GroupService groupService;
    @Autowired
    SubmissionService submissionService;
    @PostMapping("/recentEvent")
    public String recentEvent(@RequestParam int studentId){
        List<Map<String, Object> > ret = new ArrayList<>();
        // Notice
        RelationshipStudentNotice relationshipStudentNotice = new RelationshipStudentNotice();
        relationshipStudentNotice.setStudentId(studentId);
        List<RelationshipStudentNotice> listRelationshipStudentNotice = relationshipStudentNoticeService.selectList(relationshipStudentNotice);
        for (RelationshipStudentNotice relationshipStudentNotice1:listRelationshipStudentNotice){
            Notice notice = new Notice();
            notice.setNoticeId(relationshipStudentNotice1.getNoticeId());
            List<Notice> listNotice = noticeService.selectList(notice);
            for (Notice notice1: listNotice) {
                Map<String, Object> map = new HashMap<>();
                map.put("time", notice1.getReleaseTime());
                map.put("content", notice1);
                ret.add(map);
            }
        }
        // Homework
        RelationshipCourse relationshipCourse = new RelationshipCourse();
        relationshipCourse.setStudentId(studentId);
        List<RelationshipCourse> listRelationshipCourse = relationshipCourseService.selectList(relationshipCourse);
        Map<Integer, Integer> map2 = new HashMap<>();
        for (RelationshipCourse relationshipCourse1:listRelationshipCourse){
            Integer courseId = relationshipCourse1.getCourseId();
            if (map2.get(courseId)!=null) continue;
            map2.put(courseId, 1);
            Homework homework = new Homework();
            homework.setClassId(courseId);
            List<Homework> listHomework = homeworkService.selectList(homework);
            for (Homework homework1 : listHomework){
                Map<String, Object> map = new HashMap<>();
                map.put("time", homework1.getHomeworkReleaseTime());
                map.put("content", homework1);
                ret.add(map);
            }
        }
        RelationshipStudentClassGroup relationshipStudentClassGroup = new RelationshipStudentClassGroup();
        relationshipStudentClassGroup.setStudentId(studentId);
        List<RelationshipStudentClassGroup> listRelationshipStudentClassGroup = relationshipStudentClassGroupService.selectList(relationshipStudentClassGroup);
        for (RelationshipStudentClassGroup relationshipStudentClassGroup1:listRelationshipStudentClassGroup){
            Integer groupId = relationshipStudentClassGroup1.getGroupId();
            Group group = groupService.selectList(groupId);
            Integer classId = group.getClassId();
            if (map2.get(classId)!=null) continue;
            map2.put(classId, 1);
            Homework homework = new Homework();
            homework.setClassId(classId);
            List<Homework> listHomework = homeworkService.selectList(homework);
            for (Homework homework1 : listHomework){
                Map<String, Object> map = new HashMap<>();
                map.put("time", homework1.getHomeworkReleaseTime());
                map.put("content", homework1);
                ret.add(map);
            }
        }
        // Review
        Submission submission = new Submission();
        submission.setStudentId(studentId);
        List<Submission> listSubmission = submissionService.selectList(submission);
        for (Submission submission1 : listSubmission){
            if (submission1.getReviewTime()!=null){
                Map<String, Object> map = new HashMap<>();
                map.put("time", submission1.getReviewTime());
                map.put("content", submission1);
                ret.add(map);
            }
        }
        // Invitation receive
        // Invitation accepted
        ret.sort(Comparator.comparing(o -> (o.get("time").toString())));
        return JSON.toJSONString(ret);
    }


}

