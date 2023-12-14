package com.example;
import com.alibaba.fastjson2.JSON;
import com.example.httpService;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TeacherTest {
    @Test
    void testInsert() throws Exception {
        URL url = new URL(httpService.baseurl+"/account/new");
        Map<String, Object> map = JSON.parseObject("{ \n" +
                "  \"accountId\": 12110916,\n" +
                "  \"accountName\": \"lhy_teacher\",\n" +
                "  \"accountPassword\": \"12110916\",\n" +
                "  \"accountType\": \"teacher\",\n" +
                "  \"cookie\": 0,\n" +
                "  \"email\": \"12110916@mail.sustech.edu.cn\",\n" +
                "  \"studentId\": 0,\n" +
                "  \"teacherId\": 12110916\n" +
                "}");

    }
}
