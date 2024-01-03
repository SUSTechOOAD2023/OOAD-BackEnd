package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.interfaces.Join;
import com.example.entity.*;
import com.example.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
    CourseClassService courseClassService;
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
    @Autowired
    JoinGroupInvitationService joinGroupInvitationService;
    @Autowired
    StudentService studentService;
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
                map.put("eventTime", notice1.getReleaseTime());
                map.put("eventType", "Course notice released");
                Map<String, Object> map1 = new HashMap<>();
                CourseClass courseClass = new CourseClass();
                courseClass = courseClassService.selectList(courseClass).get(0);
                map1.put("noticeId", notice1.getNoticeId());
                map1.put("classId", notice1.getClassId());
                map1.put("classShortName", courseClass.getCourseName());
                map1.put("noticeTitle", notice1.getNoticeTitle());
                map1.put("noticeContent", notice1.getNoticeContent());
                map1.put("releaseTime", notice1.getReleaseTime());
                map.put("eventContent", map1);
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
                map.put("eventTime", homework1.getHomeworkReleaseTime());
                map.put("eventType", "Individual homework released");
                Map<String, Object> map1 = new HashMap<>();
                CourseClass courseClass = new CourseClass();
                courseClass.setClassId(homework1.getClassId());
                courseClass = courseClassService.selectList(courseClass).get(0);
                map1.put("homeworkId", homework1.getHomeworkId());
                map1.put("classId", homework1.getClassId());
                map1.put("courseName", courseClass.getCourseName());
                map1.put("homeworkTitle", homework1.getHomeworkTitle());
                map1.put("homeworkContent", homework1.getHomeworkContent());
                map1.put("homeworkType", homework1.getHomeworkType());
                map1.put("homeworkDdl", homework1.getHomeworkDdl());
                map1.put("homeworkReleaseTime", homework1.getHomeworkReleaseTime());
                map1.put("maxScore", homework1.getMaxScore());
                map.put("eventContent", map1);
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
                map.put("eventTime", homework1.getHomeworkReleaseTime());
                map.put("eventType", "Group homework released");
                Map<String, Object> map1 = new HashMap<>();
                CourseClass courseClass = new CourseClass();
                courseClass.setClassId(homework1.getClassId());
                courseClass = courseClassService.selectList(courseClass).get(0);
                map1.put("homeworkId", homework1.getHomeworkId());
                map1.put("classId", homework1.getClassId());
                map1.put("courseName", courseClass.getCourseName());
                map1.put("homeworkTitle", homework1.getHomeworkTitle());
                map1.put("homeworkContent", homework1.getHomeworkContent());
                map1.put("homeworkType", homework1.getHomeworkType());
                map1.put("homeworkDdl", homework1.getHomeworkDdl());
                map1.put("homeworkReleaseTime", homework1.getHomeworkReleaseTime());
                map1.put("maxScore", homework1.getMaxScore());
                map.put("eventContent", map1);
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
                map.put("eventTime", submission1.getReviewTime());
                map.put("eventType", "Individual homework reviewed");
                Map<String, Object> map1 = new HashMap<>();
                Student student = studentService.selectStudent(submission1.getStudentId());
                Homework homework = new Homework();
                homework.setHomeworkId(submission1.getHomeworkId());
                homework = homeworkService.selectList(homework).get(0);
                map1.put("submissionId", submission1.getSubmissionId());
                map1.put("groupId", submission1.getGroupId());
                map1.put("studentId", submission1.getStudentId());
                map1.put("studentName", student.getStudentName());
                map1.put("homeworkId", submission1.getHomeworkId());
                map1.put("homeworkName", homework.getHomeworkTitle());
                map1.put("submissionContent", submission1.getSubmissionContent());
                map1.put("submissionComment", submission1.getSubmissionComment());
                map1.put("submissionScore", submission1.getSubmissionScore());
                map1.put("submissionTime", submission1.getSubmissionTime());
                map1.put("reviewTime", submission1.getReviewTime());
                map.put("eventContent", map1);
                ret.add(map);
            }
        }
        relationshipStudentClassGroup = new RelationshipStudentClassGroup();
        relationshipStudentClassGroup.setStudentId(studentId);
        listRelationshipStudentClassGroup = relationshipStudentClassGroupService.selectList(relationshipStudentClassGroup);
        for (RelationshipStudentClassGroup relationshipStudentClassGroup1:listRelationshipStudentClassGroup){
            Integer groupId = relationshipStudentClassGroup1.getGroupId();
            submission.setGroupId(groupId);
            listSubmission = submissionService.selectList(submission);
            for (Submission submission1 : listSubmission){
                if (submission1.getReviewTime()!=null){
                    Map<String, Object> map = new HashMap<>();
                    map.put("eventTime", submission1.getReviewTime());
                    map.put("eventType", "Group homework reviewed");
                    Map<String, Object> map1 = new HashMap<>();
                    Student student = studentService.selectStudent(submission1.getStudentId());
                    Homework homework = new Homework();
                    homework.setHomeworkId(submission1.getHomeworkId());
                    homework = homeworkService.selectList(homework).get(0);
                    map1.put("submissionId", submission1.getSubmissionId());
                    map1.put("groupId", submission1.getGroupId());
                    map1.put("studentId", submission1.getStudentId());
                    map1.put("studentName", student.getStudentName());
                    map1.put("homeworkId", submission1.getHomeworkId());
                    map1.put("homeworkName", homework.getHomeworkTitle());
                    map1.put("submissionContent", submission1.getSubmissionContent());
                    map1.put("submissionComment", submission1.getSubmissionComment());
                    map1.put("submissionScore", submission1.getSubmissionScore());
                    map1.put("submissionTime", submission1.getSubmissionTime());
                    map1.put("reviewTime", submission1.getReviewTime());
                    map.put("eventContent", map1);
                    ret.add(map);
                }
            }
        }
        // Invitation receive
        List<JoinGroupInvitation> joinGroupInvitationList=joinGroupInvitationService.searchReceive(studentId);
        for (JoinGroupInvitation joinGroupInvitation:joinGroupInvitationList){
            Map<String, Object> map = new HashMap<>();
            String sendTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(joinGroupInvitation.getSendTime());
            map.put("eventTime", sendTime);
            map.put("eventType", "Received invitation");
            Map<String, Object> map1 = new HashMap<>();
            Group group = groupService.selectList(joinGroupInvitation.getGroupId());
            Student student = studentService.selectStudent(joinGroupInvitation.getSendStudentId());
            map1.put("joinGroupInvitationId", joinGroupInvitation.getJoinGroupInvitationId());
            map1.put("groupId", joinGroupInvitation.getGroupId());
            map1.put("groupName", group.getGroupName());
            map1.put("sendStudentId", joinGroupInvitation.getSendStudentId());
            map1.put("sendStudentName", student.getStudentName());
            map1.put("receiveStudentId", joinGroupInvitation.getReceiveStudentId());
            map1.put("sendTime", joinGroupInvitation.getSendTime());
            map1.put("isAccepted", joinGroupInvitation.getIsAccepted());
            map1.put("acceptTime", joinGroupInvitation.getAcceptTime());
            map.put("eventContent", map1);
            ret.add(map);
        }

        joinGroupInvitationList=joinGroupInvitationService.searchSend(studentId);

        for (JoinGroupInvitation joinGroupInvitation:joinGroupInvitationList){
            Map<String, Object> map = new HashMap<>();
            System.out.println(joinGroupInvitation.getAcceptTime().toString());
            String acceptTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(joinGroupInvitation.getAcceptTime());
            map.put("eventTime", acceptTime);
            map.put("eventType", "Invitation accepted");
            Map<String, Object> map1 = new HashMap<>();
            Group group = groupService.selectList(joinGroupInvitation.getGroupId());
            Student student = studentService.selectStudent(joinGroupInvitation.getReceiveStudentId());
            map1.put("joinGroupInvitationId", joinGroupInvitation.getJoinGroupInvitationId());
            map1.put("groupId", joinGroupInvitation.getGroupId());
            map1.put("groupName", group.getGroupName());
            map1.put("sendStudentId", joinGroupInvitation.getSendStudentId());
            map1.put("receiveStudentId", joinGroupInvitation.getReceiveStudentId());
            map1.put("receiveStudentName", student.getStudentName());
            map1.put("sendTime", joinGroupInvitation.getSendTime());
            map1.put("isAccepted", joinGroupInvitation.getIsAccepted());
            map1.put("acceptTime", joinGroupInvitation.getAcceptTime());
            map.put("eventContent", map1);
            ret.add(map);
        }

        // Invitation accepted
        ret.sort(Comparator.comparing(o -> (o.get("eventTime").toString())));
        return JSON.toJSONString(ret);
    }


}

