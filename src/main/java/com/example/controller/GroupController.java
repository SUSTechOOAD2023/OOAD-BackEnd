package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.Group;
import com.example.service.GroupService;
import com.example.service.StudentService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupService service;
    @Autowired
    StudentService studentService;
    @PostMapping("/list")
    public String list(@RequestParam int groupId) {
        return JSON.toJSONString(service.selectList(groupId));
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList());
    }
    @PostMapping("/new")
    public int insert(@RequestParam String groupName, @RequestParam int classId, @RequestParam Timestamp groupDeadline){
        Group group=new Group();
        group.setGroupName(groupName);
        group.setClassId(classId);
        group.setGroupDeadline(groupDeadline);
        service.saveOrUpdate(group);
        return group.getGroupId();
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int groupId){
        return service.delete(groupId);
    }

    @PostMapping("/update")
    public String update(@RequestBody Group group){
//        System.out.println(group.toString());
        if(!service.isGroupExist(group.getGroupId())){
            return "该群组不存在";
        }
        service.saveOrUpdate(group);
        return "更新成功";
    }

    @PostMapping("/selectGroup")
    public String selectGroup(@RequestParam int studentId,@RequestParam int classId) {
       return JSON.toJSONString(service.selectGroup(studentId,classId));
    }


    @ApiOperation(value = "按照一定条件返回群组属性", tags = "群组类")
    @PostMapping("/selectGroupStatus")
    public String selectGroupStatus(@RequestParam Integer classId, Integer visible,  Integer valid, Integer expired) {
       return JSON.toJSONString(service.selectGroupStatus(classId,visible,valid,expired));
    }


}

