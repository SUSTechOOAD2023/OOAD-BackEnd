package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Homework;
import com.example.entity.Submission;
import com.example.mapper.HomeworkMapper;
import com.example.mapper.SubmissionMapper;
import com.example.service.SubmissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
@Service
public class SubmissionServiceImpl extends ServiceImpl<SubmissionMapper, Submission> implements SubmissionService {
    @Autowired
    SubmissionMapper mapper;
    @Override
    public List<Submission> selectList(Submission submission){
        QueryWrapper<Submission> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(submission.getSubmissionId()!=null,Submission::getSubmissionId,submission.getSubmissionId())
                .eq(submission.getGroupNumber()!=null,Submission::getGroupNumber,submission.getGroupNumber())
                .eq(submission.getStudentId()!=null,Submission::getStudentId,submission.getStudentId())
                .eq(submission.getHomeworkId()!=null,Submission::getHomeworkId,submission.getHomeworkId())
                .eq(submission.getSubmissionContent()!=null,Submission::getSubmissionContent,submission.getSubmissionContent())
                .eq(submission.getSubmissionComment()!=null,Submission::getSubmissionComment,submission.getSubmissionComment())
                .eq(submission.getSubmissionScore()!=null,Submission::getSubmissionScore,submission.getSubmissionScore());
        return mapper.selectList(queryWrapper);
    }
    @Override
    public int delete(Submission submission){
        QueryWrapper<Submission> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(submission.getSubmissionId()!=null,Submission::getSubmissionId,submission.getSubmissionId())
                .eq(submission.getGroupNumber()!=null,Submission::getGroupNumber,submission.getGroupNumber())
                .eq(submission.getStudentId()!=null,Submission::getStudentId,submission.getStudentId())
                .eq(submission.getHomeworkId()!=null,Submission::getHomeworkId,submission.getHomeworkId())
                .eq(submission.getSubmissionContent()!=null,Submission::getSubmissionContent,submission.getSubmissionContent())
                .eq(submission.getSubmissionComment()!=null,Submission::getSubmissionComment,submission.getSubmissionComment())
                .eq(submission.getSubmissionScore()!=null,Submission::getSubmissionScore,submission.getSubmissionScore());
        return mapper.delete(queryWrapper);
    }
}
