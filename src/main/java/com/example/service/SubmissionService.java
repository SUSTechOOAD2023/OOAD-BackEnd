package com.example.service;

import com.example.entity.Homework;
import com.example.entity.Submission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
public interface SubmissionService extends IService<Submission> {
    List<Submission> selectList(Submission submission);
    int delete(Submission submission);
}
