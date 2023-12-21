package com.example.services;

import com.example.httpService;

import java.net.URL;
import java.util.Map;

public class AccountService {
    public static void insert(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/account/new?code=");
        httpService.sendPostRequest(url, map);
    }
    public static Map<String, Object> list(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/account/list");
        return httpService.sendPostRequestAndCheck(url, map);
    }
    public static Map<String, Object> all() throws Exception {
        URL url = new URL(httpService.baseurl+"/account/all");
        return httpService.sendGetRequestAndCheck(url);
    }
    public static void delete(Map<String, Object> map) throws Exception {
        URL url = new URL(httpService.baseurl+"/account/delete");
        httpService.sendPostRequest(url, map);
    }
}
