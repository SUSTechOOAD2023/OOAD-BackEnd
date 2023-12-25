package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.Group;
import com.example.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/list")
    public String list(@RequestParam int groupId) {
        return JSON.toJSONString(service.selectList(groupId));
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList());
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody Group group){
        System.out.println(group.toString());
        service.addGroup(group.getGroupName(),group.getClassId());
        return true;
    }
    @PostMapping("/delete")
    public String delete(@RequestParam int groupId){
        return service.delete(groupId);
    }

    @PostMapping("/update")
    public String update(@RequestParam int groupId,@RequestBody Group group){
        if(!service.isGroupExist(groupId)){
            return "该群组不存在";
        }
        service.saveOrUpdate(group);
        return "更新成功";
    }


}

