package com.example.service;

import com.example.httpService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class NoticeService {
    public void insert(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/notice/new");
        httpService.sendPostRequest(url, map);
    }
    public Map<String, Object> list(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/notice/list");
        return httpService.sendPostRequestAndCheck(url, map);
    }
    public Map<String, Object> all() throws Exception {
        URL url = new URL(httpService.baseurl+"/notice/all");
        return httpService.sendGetRequestAndCheck(url);
    }
    public void delete(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/notice/delete");
        httpService.sendPostRequest(url, map);
    }
}
