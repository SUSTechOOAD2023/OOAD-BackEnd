package com.example.service;

import com.example.httpService;

import java.net.URL;
import java.util.Map;

public class TeacherService {
    public void insert(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/teacher/new");
        httpService.sendPostRequest(url, map);
    }
    public Map<String, Object> list(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/teacher/list");
        return httpService.sendPostRequestAndCheck(url, map);
    }
    public Map<String, Object> all() throws Exception {
        URL url = new URL(httpService.baseurl+"/teacher/all");
        return httpService.sendGetRequestAndCheck(url);
    }
    public void delete(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/teacher/delete");
        httpService.sendPostRequest(url, map);
    }
}
