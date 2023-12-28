package com.example.controller;


import com.example.entity.Group;
import com.example.entity.RelationshipStudentClassGroup;
import com.example.service.GroupService;
import com.example.service.RelationshipStudentClassGroupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    RelationshipStudentClassGroupService service;
    GroupService groupService;

@ApiOperation(value = "添加学生到群组", notes = "添加学生到群组")
    @PostMapping("/addStudentToGroup")
    public boolean addStudentToGroup(@RequestParam int studentId,  @RequestParam int groupId){
        RelationshipStudentClassGroup relationshipStudentClassGroup=new RelationshipStudentClassGroup();
        relationshipStudentClassGroup.setStudentId(studentId);
        relationshipStudentClassGroup.setGroupId(groupId);
        Group group=groupService.selectList(groupId);
        group.setGroupSize(group.getGroupSize()+1);
        return service.saveOrUpdate(relationshipStudentClassGroup);
    }

    @PostMapping("/deleteStudentFromGroup")
    public int deleteStudentFromGroup(@RequestParam int studentId,  @RequestParam int groupId){
        Group group=groupService.selectList(groupId);
        group.setGroupSize(group.getGroupSize()-1);
        return service.deleteStudentFromGroup(studentId,groupId);
    }




}

