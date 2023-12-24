package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Teacher;
import com.example.mapper.TeacherMapper;
import com.example.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired TeacherMapper mapper;


    @Override
    public boolean isTeacherExist(int teacherID) {
        QueryWrapper<Teacher> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("teacher_id",teacherID);
        return mapper.selectCount(queryWrapper)>0;
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        return false;
    }


    @Override
    public boolean deleteTeacher(Teacher teacher) {
        return false;
    }

    @Override
    public Teacher selectTeacher(int teacherID) {
        QueryWrapper<Teacher> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("teacher_id",teacherID);
        if(mapper.selectCount(queryWrapper)>0){
            return mapper.selectOne(queryWrapper);
        }else {
            return null;
        }
    }




}
