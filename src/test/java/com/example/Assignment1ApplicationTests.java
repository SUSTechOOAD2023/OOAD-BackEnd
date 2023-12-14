package com.example;
import com.alibaba.fastjson2.JSON;
import com.example.services.AccountService;
import com.example.services.NoticeService;
import org.junit.jupiter.api.Test;

import java.util.Map;
//import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class Assignment1ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testNotice() throws Exception {
        Map<String, Object> mapTeacher = JSON.parseObject("{ \n" +
                "  \"accountId\": 12110916,\n" +
                "  \"accountName\": \"lhy_teacher\",\n" +
                "  \"accountPassword\": \"12110916\",\n" +
                "  \"accountType\": \"teacher\",\n" +
                "  \"cookie\": 0,\n" +
                "  \"email\": \"12110916@mail.sustech.edu.cn\",\n" +
                "  \"studentId\": 0,\n" +
                "  \"teacherId\": 12110916\n" +
                "}");
        AccountService.insert(mapTeacher);
        Map<String, Object> mapNotice = JSON.parseObject("{ \n" +
                //"  \"noticeId\": 0,\n" +
                "  \"teacherId\": \"12110916\",\n" +
                "  \"classId\": \"1\",\n" +
                "  \"noticeContent\": \"orz lsd!\",\n" +
                "}");
        NoticeService.insert(mapNotice);
        Map<String, Object> mapAnswer = JSON.parseObject("{ \n" +
                "  \"noticeId\": 1,\n" +
                "  \"teacherId\": \"12110916\",\n" +
                "  \"classId\": \"1\",\n" +
                "  \"noticeContent\": \"orz lsd!\",\n" +
                "}");
        assert NoticeService.all().equals(mapAnswer);
    }
}