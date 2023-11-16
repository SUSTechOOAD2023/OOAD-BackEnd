package com.example.HomeworkTest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class HomeworkTest {

    @Test
    void testInsert() throws Exception {
        URL url = new URL("http://localhost:9090/homework/new");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        OutputStream output = conn.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
        writer.write("{\"homeworkContent\":\"aaa\",\"classId\":1,\"homeworkId\":1,\"homeworkType\":\"type1\"}");
        writer.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response = "",line;
        while ((line = reader.readLine())!=null)
            response += line;
        System.out.println(response);
        //assert response.equals("[{\"homeworkContent\":\"aaa\",\"classId\":1,\"homeworkId\":1,\"homeworkType\":\"type1\"}]");
        reader.close();
        conn.disconnect();
    }
    @Test
    void testFindAll() throws Exception {
        URL url = new URL("http://localhost:9090/homework/all");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response = "",line;
        while ((line = reader.readLine())!=null)
            response += line;
        assert response.equals("[{\"homeworkContent\":\"aaa\",\"homeworkId\":1,\"homeworkType\":\"type1\"}]");
        reader.close();
        conn.disconnect();
    }
}