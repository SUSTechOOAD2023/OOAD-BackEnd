package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.example.entity.CourseClass;
import com.example.entity.MessageBoard;
import com.example.entity.Student;
import com.example.service.MessageBoardService;
import com.example.service.StudentService;
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
 * @since 2024-01-03
 */
@RestController
@RequestMapping("/messageBoard")
public class MessageBoardController {
    @Autowired
    MessageBoardService messageBoardService;
    @Autowired
    StudentService studentService;
    @PostMapping("/list")
    public String list(@RequestBody MessageBoard messageBoard) {
        List<MessageBoard> listMessageBoard = messageBoardService.selectList(messageBoard);
        List<Map<String, Object> > ret = new ArrayList<>();
        for (MessageBoard messageBoard1 : listMessageBoard){
            Map<String, Object> map = new HashMap<>();
            Student student = studentService.selectStudent(messageBoard1.getStudentId());
            map.put("messageBoardId", messageBoard1.getMessageId());
            map.put("studentId", messageBoard1.getStudentId());
            map.put("studentName", student.getStudentName());
            map.put("message", messageBoard1.getMessage());
            map.put("messageTime", messageBoard1.getMessageTime());
            ret.add(map);
        }
        ret.sort(Comparator.comparing(o -> o.get("messageTime").toString()));
        return JSON.toJSONString(ret);
    }
    @RequestMapping("/all")
    public String all() {
        List<MessageBoard> listMessageBoard = messageBoardService.selectList(new MessageBoard());
        List<Map<String, Object> > ret = new ArrayList<>();
        for (MessageBoard messageBoard1 : listMessageBoard){
            Map<String, Object> map = new HashMap<>();
            Student student = studentService.selectStudent(messageBoard1.getStudentId());
            map.put("messageBoardId", messageBoard1.getMessageId());
            map.put("studentId", messageBoard1.getStudentId());
            map.put("studentName", student.getStudentName());
            map.put("message", messageBoard1.getMessage());
            map.put("messageTime", messageBoard1.getMessageTime());
            ret.add(map);
        }
        ret.sort(Comparator.comparing(o -> o.get("messageTime").toString()));
        return JSON.toJSONString(ret);
    }
    @PostMapping("/new")
    public boolean insert(@RequestBody Map<String, Object> map){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = localDateTime.format(formatter);
        MessageBoard messageBoard = new MessageBoard();
        messageBoard.setStudentId((int)map.get("studentId"));
        messageBoard.setMessage(map.get("message").toString());
        messageBoard.setMessageTime(currentTime);
        boolean ret = messageBoardService.saveOrUpdate(messageBoard);
        return ret;
    }
}

