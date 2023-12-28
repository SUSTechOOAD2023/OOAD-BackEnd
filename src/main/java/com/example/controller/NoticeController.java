package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.example.entity.Notice;
import com.example.service.NoticeService;
import com.example.service.RelationshipStudentNoticeService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList(new Notice()));
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody Map<String, Object> map){
        Notice notice = new Notice();
        notice.setNoticeId((int)map.get("noticeId"));
        notice.setTeacherId((int)map.get("teacherId"));
        notice.setClassId((int)map.get("classId"));
        notice.setNoticeTitle(map.get("noticeTitle").toString());
        notice.setNoticeContent(map.get("noticeContent").toString());
        boolean ret = service.saveOrUpdate(notice);
        List<Integer> listStudentId= JSONArray.parseArray(map.get("listStudentId").toString(), Integer.class);
        Iterator<Integer> it = listStudentId.iterator();
        int noticeId = notice.getNoticeId();
        while (it.hasNext()){
            int studentId = it.next();
            ret &= relationshipService.insert(studentId, noticeId);
        }
        return ret;
    }
    @RequestMapping("/delete")
    public int delete(@RequestBody Notice notice){
        return service.delete(notice);
    }
}

