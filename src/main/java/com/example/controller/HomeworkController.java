package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.ConferenceRoom;
import com.example.entity.Homework;
import com.example.entity.RelationshipCourse;
import com.example.service.ConferenceRoomService;
import com.example.service.HomeworkService;
import com.example.service.RelationshipCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @PostMapping("/listDDL")
    public String listDDL(@RequestParam Integer studentId){
        RelationshipCourse relationshipCourse = new RelationshipCourse();
        relationshipCourse.setStudentId(studentId);
        List<RelationshipCourse> listRelationshipCourse = relationshipCourseService.selectList(relationshipCourse);
        Map<Integer, Integer> map2 = new HashMap<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        for (RelationshipCourse relationshipCourse1:listRelationshipCourse){
            Integer courseId = relationshipCourse1.getCourseId();
            if (map2.get(courseId)==null) continue;
            Homework homework = new Homework();
            homework.setClassId(courseId);
            List<Homework> listHomework = service.selectList(homework);
            for (Homework homework1 : listHomework){
                if (homework1.getHomeworkDdl())
            }
        }
        //homework.set
        return null;
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList(new Homework()));
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody Homework homework) {
        return service.saveOrUpdate(homework);
    }
    @RequestMapping("/delete")
    public int delete(@RequestBody Homework homework){
        return service.delete(homework);
    }
}

