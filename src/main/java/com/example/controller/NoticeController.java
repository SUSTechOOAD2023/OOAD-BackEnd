package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.example.entity.Notice;
import com.example.entity.RelationshipStudentNotice;
import com.example.service.NoticeService;
import com.example.service.RelationshipStudentNoticeService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
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
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    NoticeService service;
    @Autowired
    RelationshipStudentNoticeService relationshipService;
    @PostMapping("/list")
    public String list(@RequestBody Notice notice) {
        return JSON.toJSONString(service.selectList(notice));
    }
    @PostMapping("/studentSearch")
    public String studentSearch(@RequestParam int classId, @RequestParam int studentId) {
        List<Integer> listNoticeId = relationshipService.listStudentId(studentId);
        Notice notice = new Notice();
        notice.setClassId(classId);
        List<Notice> listNotice = service.selectList(notice);
        List<Notice> ret = new ArrayList<>();
        for (Notice notice1 : listNotice){
            if (listNoticeId.contains(notice1.getNoticeId()))
                ret.add(notice1);
        }
        return JSON.toJSONString(ret);
    }
    @PostMapping("/noticeSearch")
    public String noticeSearch(@RequestParam int noticeId) {
        List<Integer> listStudent = relationshipService.listNoticeId(noticeId);
        return JSON.toJSONString(listStudent);
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList(new Notice()));
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody Map<String, Object> map){
        Notice notice = new Notice();
        notice.setTeacherId((int)map.get("teacherId"));
        notice.setClassId((int)map.get("classId"));
        notice.setNoticeTitle(map.get("noticeTitle").toString());
        notice.setNoticeContent(map.get("noticeContent").toString());
        boolean ret = service.saveOrUpdate(notice);
        List<Integer> listStudentId= JSONArray.parseArray(map.get("listStudentId").toString(), Integer.class);
        int noticeId = notice.getNoticeId();
        for (int studentId : listStudentId) {
            ret &= relationshipService.insert(studentId, noticeId);
        }
        return ret;
    }
    @PostMapping("/modify")
    public boolean modify(@RequestBody Map<String, Object> map){
        Notice notice = new Notice();
        notice.setNoticeId((int)map.get("noticeId"));
        relationshipService.deleteNotice(notice.getNoticeId());
        notice.setTeacherId((int)map.get("teacherId"));
        notice.setClassId((int)map.get("classId"));
        notice.setNoticeTitle(map.get("noticeTitle").toString());
        notice.setNoticeContent(map.get("noticeContent").toString());
        boolean ret = service.saveOrUpdate(notice);
        List<Integer> listStudentId= JSONArray.parseArray(map.get("listStudentId").toString(), Integer.class);
        int noticeId = notice.getNoticeId();
        for (int studentId : listStudentId) {
            ret &= relationshipService.insert(studentId, noticeId);
        }
        return ret;
    }
    @RequestMapping("/delete")
    public int delete(@RequestParam int noticeId){
        relationshipService.deleteNotice(noticeId);
        Notice notice = new Notice();
        notice.setNoticeId(noticeId);
        return service.delete(notice);
    }
}

