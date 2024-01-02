package com.example.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Teacher;
import com.example.mapper.TeacherMapper;
import com.example.service.TeacherService;
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
    public List<Teacher> selectList(Teacher teacher) {
        QueryWrapper<Teacher> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(teacher.getTeacherId()!=null,Teacher::getTeacherId,teacher.getTeacherId())
                .eq(teacher.getAccountId()!=null,Teacher::getAccountId,teacher.getAccountId())
                .eq(teacher.getTeacherGender()!=null,Teacher::getTeacherGender,teacher.getTeacherGender())
                .eq(teacher.getTeacherName()!=null,Teacher::getTeacherName,teacher.getTeacherName())
                .eq(teacher.getTeacherDepartment()!=null,Teacher::getTeacherDepartment,teacher.getTeacherDepartment())
                .eq(teacher.getTeacherInformation()!=null,Teacher::getTeacherInformation,teacher.getTeacherInformation())
                ;
        return mapper.selectList(queryWrapper);
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
