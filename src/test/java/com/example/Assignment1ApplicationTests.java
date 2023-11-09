package com.example;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Assignment1ApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void testFindAll() throws Exception {
        URL url = new URL("localhost:9090/homework/all");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/text");
    }
}