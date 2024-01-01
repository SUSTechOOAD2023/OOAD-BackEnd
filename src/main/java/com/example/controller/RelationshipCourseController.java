package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.example.entity.*;
import com.example.service.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
 * @since 2023-12-31
 */
@RestController
@RequestMapping("/relationshipCourse")
public class RelationshipCourseController {
    @Autowired
    RelationshipCourseService relationshipCourseService;

    @PostMapping("/add")
    public int addRelationshipCourse(@RequestBody RelationshipCourse relationshipCourse){
//        relationshipCourse.setTeacherId(10);
//        relationshipCourse.setRelationshipId(10);
        System.out.println(relationshipCourseService.saveOrUpdate(relationshipCourse));
        return relationshipCourse.getRelationshipId();
    }

    @PostMapping("/delete")
    public boolean deleteRelationshipCourse(@RequestParam int relationshipId){
        if (!relationshipCourseService.isRelationshipCourseExist(relationshipId)){
            return false;
        }
        return relationshipCourseService.deleteRelationshipCourse(relationshipId);
    }

    @PostMapping("/select")
    public RelationshipCourse selectRelationshipCourse(@RequestParam int relationshipId){
        if (!relationshipCourseService.isRelationshipCourseExist(relationshipId)){
            return null;
        }
        return relationshipCourseService.selectRelationshipCourse(relationshipId);
    }

    @PostMapping("/addStudentList")
    public boolean insertStudentList(@RequestBody Map<String, Object> map){
        List<Integer> listId = JSONArray.parseArray(map.get("idList").toString(), Integer.class);
        int courseId = (int) map.get("courseId");
        for (Integer studentId: listId){
            RelationshipCourse relationshipCourse = new RelationshipCourse();
            relationshipCourse.setStudentId(studentId);
            relationshipCourse.setCourseId(courseId);
            relationshipCourseService.saveOrUpdate(relationshipCourse);
        }
        return true;
    }
    @PostMapping("/addTeacherList")
    public boolean insertTeacherList(@RequestBody Map<String, Object> map){
        List<Integer> listId = JSONArray.parseArray(map.get("idList").toString(), Integer.class);
        int courseId = (int) map.get("courseId");
        for (Integer teacherId: listId){
            RelationshipCourse relationshipCourse = new RelationshipCourse();
            relationshipCourse.setTeacherId(teacherId);
            relationshipCourse.setCourseId(courseId);
            relationshipCourseService.saveOrUpdate(relationshipCourse);
        }
        return true;
    }
    @PostMapping("/addSAList")
    public boolean insertSAList(@RequestBody Map<String, Object> map){
        List<Integer> listId = JSONArray.parseArray(map.get("idList").toString(), Integer.class);
        int courseId = (int) map.get("courseId");
        for (Integer saId: listId){
            RelationshipCourse relationshipCourse = new RelationshipCourse();
            relationshipCourse.setSaId(saId);
            relationshipCourse.setCourseId(courseId);
            relationshipCourseService.saveOrUpdate(relationshipCourse);
        }
        return true;
    }
    @Autowired
    StudentService studentService;
    @PostMapping("/selectStudent")
    public String selectStudent(@RequestBody Map<String, Object> map) {
        RelationshipCourse relationshipCourse = new RelationshipCourse();
        if (map.get("courseId") != null) {
            relationshipCourse.setCourseId((int) map.get("courseId"));
        }
        List<RelationshipCourse> lis = relationshipCourseService.selectList(relationshipCourse);
        List<Map<String, Object> > ret = new ArrayList<>();
        for (RelationshipCourse relationshipCourse1:lis){
            Integer studentId = relationshipCourse1.getStudentId();
            if (studentId==null) continue;
            Student student = studentService.selectStudent(studentId);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("studentId", studentId);
            map1.put("studentName", student.getStudentName());
            ret.add(map1);
        }
        return JSON.toJSONString(ret);
    }
    @Autowired
    TeacherService teacherService;
    @PostMapping("/selectTeacher")
    public String selectTeacher(@RequestBody Map<String, Object> map) {
        RelationshipCourse relationshipCourse = new RelationshipCourse();
        if (map.get("courseId") != null) {
            relationshipCourse.setCourseId((int) map.get("courseId"));
        }
        List<RelationshipCourse> lis = relationshipCourseService.selectList(relationshipCourse);
        List<Map<String, Object> > ret = new ArrayList<>();
        for (RelationshipCourse relationshipCourse1:lis){
            Integer teacherId = relationshipCourse1.getTeacherId();
            if (teacherId==null) continue;
            Teacher teacher = teacherService.selectTeacher(teacherId);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("studentId", teacherId);
            map1.put("studentName", teacher.getTeacherName());
            ret.add(map1);
        }
        return JSON.toJSONString(ret);
    }

    @PostMapping("/selectSA")
    public String selectSA(@RequestBody Map<String, Object> map) {
        RelationshipCourse relationshipCourse = new RelationshipCourse();
        if (map.get("courseId") != null) {
            relationshipCourse.setCourseId((int) map.get("courseId"));
        }
        List<RelationshipCourse> lis = relationshipCourseService.selectList(relationshipCourse);
        List<Map<String, Object> > ret = new ArrayList<>();
        for (RelationshipCourse relationshipCourse1:lis){
            Integer studentId = relationshipCourse1.getSaId();
            if (studentId==null) continue;
            Student student = studentService.selectStudent(studentId);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("SAId", studentId);
            map1.put("SAName", student.getStudentName());
            ret.add(map1);
        }
        return JSON.toJSONString(ret);
    }
    @Autowired
    CourseClassService courseClassService;
    @Autowired
    RelationshipStudentClassGroupService relationshipStudentClassGroupService;
    @Autowired
    GroupService groupService;
    @PostMapping("/selectCourse")
    public String selectCourse(@RequestBody Map<String, Object> map){
        RelationshipCourse relationshipCourse = new RelationshipCourse();
        if (map.get("studentId") != null) {
            relationshipCourse.setStudentId((int) map.get("studentId"));
        }
        if (map.get("teacherId") != null) {
            relationshipCourse.setTeacherId((int) map.get("teacherId"));
        }
        if (map.get("SAId") != null) {
            relationshipCourse.setSaId((int) map.get("SAId"));
        }
        List<RelationshipCourse> lis = relationshipCourseService.selectList(relationshipCourse);
        List<Map<String, Object> > ret = new ArrayList<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (RelationshipCourse relationshipCourse1:lis){
            Integer courseId = relationshipCourse1.getCourseId();
            if (map2.get(courseId)!=null) continue;
            map2.put(courseId, 1);
            CourseClass courseClass = new CourseClass();
            courseClass.setCourseId(courseId);
            courseClass = courseClassService.selectList(courseClass).get(0);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("courseId", courseId);
            map1.put("courseName", courseClass.getCourseName());
            RelationshipCourse relationshipCourse2 = new RelationshipCourse();
            relationshipCourse2.setCourseId(courseId);
            List<RelationshipCourse> lis1 = relationshipCourseService.selectList(relationshipCourse);
            for (RelationshipCourse relationshipCourse3:lis1){
                if (relationshipCourse3.getTeacherId()!=null) {
                    Teacher teacher = teacherService.selectTeacher(relationshipCourse3.getTeacherId());
                    map1.put("teacherId", relationshipCourse3.getTeacherId());
                    map1.put("teacherName", teacher.getTeacherName());
                }
                if (relationshipCourse3.getSaId()!=null) {
                    Student student = studentService.selectStudent(relationshipCourse3.getSaId());
                    map1.put("SAId", relationshipCourse3.getSaId());
                    map1.put("SAName", student.getStudentName());
                }
            }
            if (map.get("studentId") != null) {
                RelationshipStudentClassGroup relationshipStudentClassGroup = new RelationshipStudentClassGroup();
                relationshipStudentClassGroup.setStudentId((int)map.get("studentId"));
                List<RelationshipStudentClassGroup> lis2 = relationshipStudentClassGroupService.selectList(relationshipStudentClassGroup);
                for (RelationshipStudentClassGroup relationshipStudentClassGroup1: lis2){
                    Integer groupId = relationshipStudentClassGroup1.getGroupId();
                    if (groupService.selectList(groupId).getClassId()==courseId){
                        Group group = groupService.selectList(groupId);
                        map1.put("groupName", group.getGroupName());
                    }
                }
            }
            ret.add(map1);
        }
        return JSON.toJSONString(ret);
    }
//    @PostMapping("/list")
//    public String list(@RequestParam int courseId){
//        return relationshipCourseService.selectList(relationshipCourse).toString();
//    }

}

