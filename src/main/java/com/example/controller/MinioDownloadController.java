package com.example.controller;

import com.example.entity.Account;
import com.example.util.AjaxJson;
import com.example.util.MinioUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/download")
public class MinioDownloadController {
    @Autowired
    private MinioUtils minioUtilS;
    @Value("${minio.userPath}")
    private String userImgPath;

    @ApiOperation(value = "下载图片", notes = "仅测试使用", tags = "测试类")
    @GetMapping("/userimg")
    public AjaxJson download(@RequestParam int accountID){
        return AjaxJson.getSuccessData(minioUtilS.download(String.format("%s.jpeg", accountID),userImgPath));
    }

}

