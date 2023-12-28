package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.RelationshipStudentNotice;
import com.example.service.RelationshipStudentNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
@RestController
@RequestMapping("/relationshipStudentNotice")
public class RelationshipStudentNoticeController {
    @Autowired
    RelationshipStudentNoticeService service;

}

