package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.*;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
@RequestMapping("/homework")
public class HomeworkController {
    @Autowired
    HomeworkService service;
    @PostMapping("/list")
    public String list(@RequestBody Homework homework) {
        return JSON.toJSONString(service.selectList(homework));
    }
    @Autowired
    RelationshipCourseService relationshipCourseService;
    @Autowired
    RelationshipStudentClassGroupService relationshipStudentClassGroupService;
    @Autowired
    GroupService groupService;
    @PostMapping("/listDDL")
    public String listDDL(@RequestParam Integer studentId){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = localDateTime.format(formatter);
        System.out.println(currentTime);
        RelationshipCourse relationshipCourse = new RelationshipCourse();
        relationshipCourse.setStudentId(studentId);
        List<RelationshipCourse> listRelationshipCourse = relationshipCourseService.selectList(relationshipCourse);
        Map<Integer, Integer> map2 = new HashMap<>();
        List<Homework> ret = new ArrayList<>();
        for (RelationshipCourse relationshipCourse1:listRelationshipCourse){
            Integer courseId = relationshipCourse1.getCourseId();
            if (map2.get(courseId)!=null) continue;
            map2.put(courseId, 1);
            Homework homework = new Homework();
            homework.setClassId(courseId);
            List<Homework> listHomework = service.selectList(homework);
            for (Homework homework1 : listHomework){
                if (homework1.getHomeworkDdl().compareTo(currentTime)>0){
                    ret.add(homework1);
                }
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
            List<Homework> listHomework = service.selectList(homework);
            for (Homework homework1 : listHomework){
                if (homework1.getHomeworkDdl().compareTo(currentTime)>0){
                    ret.add(homework1);
                }
            }
        }
        return JSON.toJSONString(ret);
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList(new Homework()));
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody Homework homework) {
        homework.setHomeworkReleaseTime(Timestamp.valueOf(LocalDateTime.now()));
        return service.saveOrUpdate(homework);
    }
    @RequestMapping("/delete")
    public int delete(@RequestBody Homework homework){
        return service.delete(homework);
    }
}

