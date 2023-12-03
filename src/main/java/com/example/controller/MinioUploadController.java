package com.example.controller;

//import cn.dev33.satoken.stpAjaxJson.StpUtil;
import com.example.entity.Account;
import com.example.util.AjaxJson;
import com.example.util.MinioUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @GetMapping("/userimg")
    public Object upload(MultipartFile file, Account account) {
        minioUtilS.upload_old(file, userImgPath, String.format("%s.jpeg", account.getAccountId()));
        return AjaxJson.getSuccess();
    }

    @ApiOperation(value = "上传文件", notes = "仅测试使用", tags = "测试类")
    @GetMapping("/up")
    public Object upload(MultipartFile[] file) {
        minioUtilS.upload(file);
        return AjaxJson.getSuccess();
    }

    @ApiOperation(value = "压缩并上传文件", notes = "仅测试使用", tags = "测试类")
    @PostMapping("/uploadAndCompressFile")
    public Object uploadAndCompressFile(MultipartFile file) {
        minioUtilS.upload(file, userImgPath, "压缩并上传文件的测试.jpeg");
        return AjaxJson.getSuccess();
    }
}

