package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.Group;
import com.example.entity.RelationshipStudentClassGroup;
import com.example.entity.Student;
import com.example.service.GroupService;
import com.example.service.RelationshipStudentClassGroupService;
import com.example.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
 * @since 2023-12-28
 */
@RestController
@RequestMapping("/relationshipStudentClassGroup")
public class RelationshipStudentClassGroupController {
    @Autowired
    RelationshipStudentClassGroupService service;
    @Autowired
    GroupService groupService;

@ApiOperation(value = "添加学生到群组", notes = "添加学生到群组")
    @PostMapping("/addStudentToGroup")
    public boolean addStudentToGroup(@RequestParam int studentId,  @RequestParam int groupId){
        if (service.checkRelation(studentId,groupId)){
            return false;
        }
        RelationshipStudentClassGroup relationshipStudentClassGroup=new RelationshipStudentClassGroup();
        relationshipStudentClassGroup.setStudentId(studentId);
        relationshipStudentClassGroup.setGroupId(groupId);
        Group group=groupService.selectList(groupId);
        group.setGroupSize(group.getGroupSize()+1);
        groupService.saveOrUpdate(group);
        return service.saveOrUpdate(relationshipStudentClassGroup);
    }

    @PostMapping("/deleteStudentFromGroup")
    public int deleteStudentFromGroup(@RequestParam int studentId,  @RequestParam int groupId){
        Group group=groupService.selectList(groupId);
        group.setGroupSize(group.getGroupSize()-1);
        return service.deleteStudentFromGroup(studentId,groupId);
    }
    @Autowired
    StudentService studentService;
    @PostMapping("/listStudent")
    public String listStudent(@RequestParam int groupId){
        RelationshipStudentClassGroup relationshipStudentClassGroup=new RelationshipStudentClassGroup();
        relationshipStudentClassGroup.setGroupId(groupId);
        List<RelationshipStudentClassGroup> listRelationshipStudentClassGroyp = service.selectList(relationshipStudentClassGroup);
        Map<Integer, Map<String, Object>> ret = new HashMap<>();
        for (RelationshipStudentClassGroup relationshipStudentClassGroup1 : listRelationshipStudentClassGroyp){
            Integer studentId = relationshipStudentClassGroup1.getStudentId();
            Student student = studentService.selectStudent(studentId);
            Map<String, Object> map = new HashMap<>();
            map.put("studentId", studentId);
            map.put("studentName", student.getStudentName());
            ret.put(studentId, map);
        }
        return JSON.toJSONString(ret.values());
    }


}

