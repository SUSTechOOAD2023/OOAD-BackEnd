package com.example.controller;

import com.example.entity.Account;
import com.example.util.AjaxJson;
import com.example.util.MinioUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequestMapping("/upload")
public class MinioUploadController {
    @Autowired
    private MinioUtils minioUtilS;
    @Value("${minio.userPath}")
    private String userImgPath;

    @ApiOperation(value = "上传图片", notes = "仅测试使用", tags = "测试类")
    @PostMapping("/userimg")
    public AjaxJson upload(@RequestBody MultipartFile file, @RequestParam int accountID) {
        minioUtilS.upload(file, userImgPath, String.format("%s.jpeg", accountID));
        return AjaxJson.getSuccess();
    }

//    @ApiOperation(value = "上传文件", notes = "仅测试使用", tags = "测试类")
//    @PostMapping("/file")
//    public Object upload(MultipartFile[] file) {
//        minioUtilS.upload(file);
//        return AjaxJson.getSuccess();
//    }
//
//    @ApiOperation(value = "压缩并上传文件", notes = "仅测试使用", tags = "测试类")
//    @PostMapping("/uploadAndCompressFile")
//    public Object uploadAndCompressFile(MultipartFile file) {
//        minioUtilS.upload(file, userImgPath, "压缩并上传文件的测试.jpeg");
//        return AjaxJson.getSuccess();
//    }
}

