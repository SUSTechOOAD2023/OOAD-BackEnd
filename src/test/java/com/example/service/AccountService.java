package com.example.service;

import com.example.httpService;

import java.net.URL;
import java.util.Map;

public class AccountService {
    public void insert(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/account/new");
        httpService.sendPostRequest(url, map);
    }
    public Map<String, Object> list(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/account/list");
        return httpService.sendPostRequestAndCheck(url, map);
    }
    public Map<String, Object> all() throws Exception {
        URL url = new URL(httpService.baseurl+"/account/all");
        return httpService.sendGetRequestAndCheck(url);
    }
    public void delete(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/account/delete");
        httpService.sendPostRequest(url, map);
    }
}
