package com.example;

import com.alibaba.fastjson2.JSON;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class httpService {
    public static final String baseurl = "";

    public static void sendPostRequest(URL url, Map<String, Object> map) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        OutputStream output = conn.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
        writer.write(JSON.toJSONString(map));
        writer.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        for (String line; (line = reader.readLine())!=null; response.append(line));
        reader.close();
        conn.disconnect();
    }

    public static Map<String, Object> sendPostRequestAndCheck(URL url, Map<String, Object> map) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        OutputStream output = conn.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
        writer.write(JSON.toJSONString(map));
        writer.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        for (String line; (line = reader.readLine())!=null; response.append(line));
        reader.close();
        conn.disconnect();
        return JSON.parseObject(response.toString());
    }

    public static Map<String, Object> sendGetRequestAndCheck(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        for (String line; (line = reader.readLine())!=null; response.append(line));
        reader.close();
        conn.disconnect();
        return JSON.parseObject(response.toString());
    }
}
