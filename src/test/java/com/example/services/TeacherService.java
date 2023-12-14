package com.example.services;

import com.example.httpService;

import java.net.URL;
import java.util.Map;

public class TeacherService {
    public static void insert(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/teacher/new");
        httpService.sendPostRequest(url, map);
    }
    public static Map<String, Object> list(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/teacher/list");
        return httpService.sendPostRequestAndCheck(url, map);
    }
    public static Map<String, Object> all() throws Exception {
        URL url = new URL(httpService.baseurl+"/teacher/all");
        return httpService.sendGetRequestAndCheck(url);
    }
    public static void delete(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/teacher/delete");
        httpService.sendPostRequest(url, map);
    }
}
