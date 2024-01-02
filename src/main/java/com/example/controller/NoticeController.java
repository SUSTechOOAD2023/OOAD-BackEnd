package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.example.entity.CourseClass;
import com.example.entity.Notice;
import com.example.entity.RelationshipStudentNotice;
import com.example.service.CourseClassService;
import com.example.service.NoticeService;
import com.example.service.RelationshipStudentNoticeService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Autowired
    CourseClassService courseClassService;
    @PostMapping("/list")
    public String list(@RequestBody Notice notice) {
        List<Notice> lis = service.selectList(notice);
        lis.sort(Comparator.comparing(Notice::getReleaseTime));
        List<Map<String, Object> > ret = new ArrayList<>();
        for (Notice notice1:lis){
            Map<String, Object> map = new HashMap<>();
            CourseClass courseClass = new CourseClass();
            courseClass.setClassId(notice1.getClassId());
            courseClass = courseClassService.selectList(courseClass).get(0);
            map.put("noticeId", notice1.getNoticeId());
            map.put("classId", notice1.getClassId());
            map.put("classShortName", courseClass.getCourseName());
            map.put("noticeTitle", notice1.getNoticeTitle());
            map.put("noticeContent", notice1.getNoticeContent());
            map.put("releaseTime", notice1.getReleaseTime());
            ret.add(map);
        }
        return JSON.toJSONString(ret);
    }
    @PostMapping("/Search")
    public String search(@RequestParam int classId) {
        Notice notice = new Notice();
        notice.setClassId(classId);
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
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = localDateTime.format(formatter);
        Notice notice = new Notice();
        notice.setClassId((int)map.get("classId"));
        notice.setNoticeTitle(map.get("noticeTitle").toString());
        notice.setNoticeContent(map.get("noticeContent").toString());
        notice.setReleaseTime(currentTime);
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

