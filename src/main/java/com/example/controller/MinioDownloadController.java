package com.example.controller;

import com.example.entity.Account;
import com.example.util.AjaxJson;
import com.example.util.MinioUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Value("${minio.bucketName}")
    private String bucketName;

    @ApiOperation(value = "下载图片")
    @GetMapping("/userimg")
    public AjaxJson download_img(@RequestParam int accountID) {
        return AjaxJson.getSuccessData(minioUtilS.download_img(String.format("%s.jpeg", accountID), userImgPath));
    }

    @ApiOperation(value = "下载文件")
    @GetMapping("/file")
    public AjaxJson download_file(@RequestParam String fileName) {
//        return AjaxJson.getSuccessData(minioUtilS.download_file(String.format("%s", accountID) + "/" + fileName, filePath));
        return AjaxJson.getSuccessData(minioUtilS.download_file( fileName, filePath));

    }


    @ApiOperation(value = "删除文件")
    @GetMapping("/delete")
    public AjaxJson delete_file(@RequestParam String fileName) {
//        return AjaxJson.getSuccessData(minioUtilS.removeFile(filePath, String.format("%s", accountID) + "/" + fileName));
        return AjaxJson.getSuccessData(minioUtilS.removeFile(filePath, fileName));
    }


    @ApiOperation(value = "查看文件")
    @GetMapping("/view")
    public ResponseEntity<String> viewFile(@RequestParam String fileName) {
        try {
//            fileName = filePath + "/" + String.format("%s", accountID) + "/" + fileName;
            fileName = filePath + "/" + fileName;
            String fileUrl = minioUtilS.getFileUrl(fileName);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("获取文件URL失败");
        }
    }

    //返回某个文件夹下的所有文件名
    @ApiOperation(value = "查看文件名")
    @GetMapping("/viewFileNames")
    public AjaxJson viewFileNames(@RequestParam String fileName) {
        return AjaxJson.getSuccessData(minioUtilS.listFilesWithPrefix(bucketName,fileName));
    }
}






