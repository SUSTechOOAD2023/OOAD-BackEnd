package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.ConferenceRoom;
import com.example.entity.Notice;
import com.example.entity.RelationshipStudentNotice;
import com.example.service.NoticeService;
import com.example.service.RelationshipStudentNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
@RestController
@RequestMapping("/relationshipStudentNotice")
public class RelationshipStudentNoticeController {
    @Autowired
    RelationshipStudentNoticeService service;
    @Autowired
    NoticeService noticeService;
    @RequestMapping("/recent")
    public String listRecent(@RequestParam int studentId){
        RelationshipStudentNotice relationshipStudentNotice = new RelationshipStudentNotice();
        relationshipStudentNotice.setStudentId(studentId);
        List<RelationshipStudentNotice> listRelationshipStudentNotice = service.selectList(relationshipStudentNotice);
        List<Notice> ret = new ArrayList<>();
        for (RelationshipStudentNotice relationshipStudentNotice1: listRelationshipStudentNotice){
            Notice notice = new Notice();
            notice.setNoticeId(relationshipStudentNotice1.getNoticeId());
            ret.addAll(noticeService.selectList(notice));
        }
        ret.sort(Comparator.comparing(Notice::getReleaseTime));
        return JSON.toJSONString(ret);
    }
}

