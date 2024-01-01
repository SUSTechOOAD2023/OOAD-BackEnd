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
        List<String> listId = JSONArray.parseArray(map.get("id").toString(), String.class);
        int courseId = Integer.parseInt(map.get("courseId").toString());
        for (String studentId: listId){
            RelationshipCourse relationshipCourse = new RelationshipCourse();
            relationshipCourse.setStudentId(Integer.parseInt(studentId));
            relationshipCourse.setCourseId(courseId);
            relationshipCourseService.saveOrUpdate(relationshipCourse);
        }
        return true;
    }
    @PostMapping("/addTeacherList")
    public boolean insertTeacherList(@RequestBody Map<String, Object> map){
        List<String> listId = JSONArray.parseArray(map.get("id").toString(), String.class);
        int courseId = Integer.parseInt(map.get("courseId").toString());
        for (String teacherId: listId){
            RelationshipCourse relationshipCourse = new RelationshipCourse();
            relationshipCourse.setTeacherId(Integer.parseInt(teacherId));
            relationshipCourse.setCourseId(courseId);
            relationshipCourseService.saveOrUpdate(relationshipCourse);
        }
        return true;
    }
    @PostMapping("/addSAList")
    public boolean insertSAList(@RequestBody Map<String, Object> map){
        List<String> listId = JSONArray.parseArray(map.get("id").toString(), String.class);
        int courseId = Integer.parseInt(map.get("courseId").toString());
        for (String saId: listId){
            RelationshipCourse relationshipCourse = new RelationshipCourse();
            relationshipCourse.setSaId(Integer.parseInt(saId));
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
            relationshipCourse.setCourseId(Integer.parseInt(map.get("courseId").toString()));
        }
        List<RelationshipCourse> lis = relationshipCourseService.selectList(relationshipCourse);
        Map<Integer, Map<String, Object> > ret = new HashMap<>();
        for (RelationshipCourse relationshipCourse1:lis){
            Integer studentId = relationshipCourse1.getStudentId();
            if (studentId==null) continue;
            Student student = studentService.selectStudent(studentId);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("studentId", studentId);
            map1.put("studentName", student.getStudentName());
            ret.put(studentId, map1);
        }
        return JSON.toJSONString(ret.values());
    }
    @Autowired
    TeacherService teacherService;
    @PostMapping("/selectTeacher")
    public String selectTeacher(@RequestBody Map<String, Object> map) {
        RelationshipCourse relationshipCourse = new RelationshipCourse();
        if (map.get("courseId") != null) {
            relationshipCourse.setCourseId(Integer.parseInt(map.get("courseId").toString()));
        }
        List<RelationshipCourse> lis = relationshipCourseService.selectList(relationshipCourse);
        Map<Integer, Map<String, Object> > ret = new HashMap<>();
        for (RelationshipCourse relationshipCourse1:lis){
            Integer teacherId = relationshipCourse1.getTeacherId();
            if (teacherId==null) continue;
            Teacher teacher = teacherService.selectTeacher(teacherId);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("studentId", teacherId);
            map1.put("studentName", teacher.getTeacherName());
            ret.put(teacherId, map1);
        }
        return JSON.toJSONString(ret.values());
    }

    @PostMapping("/selectSA")
    public String selectSA(@RequestBody Map<String, Object> map) {
        RelationshipCourse relationshipCourse = new RelationshipCourse();
        if (map.get("courseId") != null) {
            relationshipCourse.setCourseId(Integer.parseInt(map.get("courseId").toString()));
        }
        List<RelationshipCourse> lis = relationshipCourseService.selectList(relationshipCourse);
        Map<Integer, Map<String, Object> > ret = new HashMap<>();
        for (RelationshipCourse relationshipCourse1:lis){
            Integer studentId = relationshipCourse1.getSaId();
            if (studentId==null) continue;
            Student student = studentService.selectStudent(studentId);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("SAId", studentId);
            map1.put("SAName", student.getStudentName());
            ret.put(studentId, map1);
        }
        return JSON.toJSONString(ret.values());
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
            relationshipCourse.setStudentId(Integer.parseInt(map.get("studentId").toString()));
        }
        if (map.get("teacherId") != null) {
            relationshipCourse.setTeacherId(Integer.parseInt(map.get("teacehrId").toString()));
        }
        if (map.get("SAId") != null) {
            relationshipCourse.setSaId(Integer.parseInt(map.get("SAId").toString()));
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
            map1.put("courseShortName", courseClass.getCourseName());
            map1.put("courseName", courseClass.getCourseTitle());
            map1.put("teacherId", new ArrayList<Integer>());
            map1.put("teacherName", new ArrayList<String>());
            map1.put("SAId", new ArrayList<Integer>());
            map1.put("SAName", new ArrayList<String>());
            RelationshipCourse relationshipCourse2 = new RelationshipCourse();
            relationshipCourse2.setCourseId(courseId);
            List<RelationshipCourse> lis1 = relationshipCourseService.selectList(relationshipCourse2);
            for (RelationshipCourse relationshipCourse3:lis1){
                if (relationshipCourse3.getTeacherId()!=null) {
                    Teacher teacher = teacherService.selectTeacher(relationshipCourse3.getTeacherId());
                    ((List<Integer>)map1.get("teacherId")).add(relationshipCourse3.getTeacherId());
                    ((List<String>)map1.get("teacherName")).add(teacher.getTeacherName());
                }
                if (relationshipCourse3.getSaId()!=null) {
                    Student student = studentService.selectStudent(relationshipCourse3.getSaId());
                    ((List<Integer>)map1.get("SAId")).add(relationshipCourse3.getSaId());
                    ((List<String>)map1.get("SAName")).add(student.getStudentName());
                }
            }
            if (map.get("studentId") != null) {
                RelationshipStudentClassGroup relationshipStudentClassGroup = new RelationshipStudentClassGroup();
                relationshipStudentClassGroup.setStudentId(Integer.parseInt(map.get("studentId").toString()));
                List<RelationshipStudentClassGroup> lis2 = relationshipStudentClassGroupService.selectList(relationshipStudentClassGroup);
                for (RelationshipStudentClassGroup relationshipStudentClassGroup1: lis2){
                    Integer groupId = relationshipStudentClassGroup1.getGroupId();
                    if (groupService.selectList(groupId).getClassId().equals(courseId)){
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

        @PostMapping("/list")
        public String list(@RequestParam int courseId){
            return relationshipCourseService.selectStudentList(courseId);
        }

//        @PostMapping("/list")


}

