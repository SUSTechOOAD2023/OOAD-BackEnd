package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.ConferenceRoom;
import com.example.entity.Homework;
import com.example.entity.User;
import com.example.mapper.HomeworkMapper;
import com.example.mapper.UserMapper;
import com.example.service.HomeworkService;
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
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework> implements HomeworkService {
    @Autowired
    HomeworkMapper mapper;
    @Override
    public List<Homework> selectList(Homework homework){
        QueryWrapper<Homework> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(homework.getHomeworkId()!=null,Homework::getHomeworkId,homework.getHomeworkId())
                .eq(homework.getHomeworkType()!=null,Homework::getHomeworkType,homework.getHomeworkType())
                .eq(homework.getHomeworkContent()!=null,Homework::getHomeworkContent,homework.getHomeworkContent())
                .eq(homework.getClassId()!=null,Homework::getClassId,homework.getClassId())
                .eq(homework.getHomeworkTitle()!=null,Homework::getHomeworkTitle,homework.getHomeworkTitle())
                .eq(homework.getHomeworkDdl()!=null,Homework::getHomeworkDdl,homework.getHomeworkDdl())
                .eq(homework.getAllowResubmit()!=null,Homework::getAllowResubmit,homework.getAllowResubmit())
                .eq(homework.getMaxScore()!=null,Homework::getMaxScore,homework.getMaxScore());
        return mapper.selectList(queryWrapper);
    }
    @Override
    public int delete(Homework homework){
        QueryWrapper<Homework> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(homework.getHomeworkId()!=null,Homework::getHomeworkId,homework.getHomeworkId())
                .eq(homework.getHomeworkType()!=null,Homework::getHomeworkType,homework.getHomeworkType())
                .eq(homework.getHomeworkContent()!=null,Homework::getHomeworkContent,homework.getHomeworkContent())
                .eq(homework.getClassId()!=null,Homework::getClassId,homework.getClassId())
                .eq(homework.getHomeworkTitle()!=null,Homework::getHomeworkTitle,homework.getHomeworkTitle())
                .eq(homework.getHomeworkDdl()!=null,Homework::getHomeworkDdl,homework.getHomeworkDdl())
                .eq(homework.getAllowResubmit()!=null,Homework::getAllowResubmit,homework.getAllowResubmit())
                .eq(homework.getMaxScore()!=null,Homework::getMaxScore,homework.getMaxScore());
        return mapper.delete(queryWrapper);
    }
}
