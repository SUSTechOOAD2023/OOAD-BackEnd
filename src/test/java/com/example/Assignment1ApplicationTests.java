package com.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class Assignment1ApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void testFindAll() throws Exception {
        URL url = new URL("http://localhost:9090/homework/all");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/text");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response = "",line;
        while ((line = reader.readLine())!=null)
            response += line;
        assert response.equals("[{\"homeworkContent\":\"aaa\",\"homeworkId\":1,\"homeworkType\":\"type1\"}]");
        reader.close();
        conn.disconnect();
    }
}