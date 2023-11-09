package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.ConferenceRoom;
import com.example.service.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sending
 * @since 2023-10-14
 */
@RestController
@RequestMapping("/conferenceRoom")
public class ConferenceRoomController {
    @Autowired
    ConferenceRoomService service;

    @PostMapping("/list")
    public String list(ConferenceRoom room) {
        return JSON.toJSONString(service.selectList(room));
    }
    @RequestMapping("/all")
    public String all() {
        return list(new ConferenceRoom());
    }
//
//    @RequestMapping("/generate")
//    public void generateData() {
//        service.generateData();
//    }


    @PostMapping("/new")
    public boolean add_room(ConferenceRoom room){
        return service.saveOrUpdate(room);
    }
    @RequestMapping("/delete")
    public int delete(ConferenceRoom room){
        return service.deleteRoom(room);
    }

}

