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
    @Value("${minio.filePath}")
    private String filePath;

    @ApiOperation(value = "下载图片", notes = "仅测试使用", tags = "测试类")
    @GetMapping("/userimg")
    public AjaxJson download_img(@RequestParam int accountID){
        return AjaxJson.getSuccessData(minioUtilS.download_img(String.format("%s.jpeg", accountID),userImgPath));
    }

    @ApiOperation(value = "下载文件", notes = "仅测试使用", tags = "测试类")
    @GetMapping("/file")
    public AjaxJson download_file(@RequestParam int accountID,@RequestParam String fileName){
        return AjaxJson.getSuccessData(minioUtilS.download_file(String.format("%s", accountID)+"/"+fileName,filePath));
    }


}

