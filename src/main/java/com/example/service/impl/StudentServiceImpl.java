package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    StudentMapper mapper;
    @Override
    public List<Student> selectList(){
        QueryWrapper<Student> queryWrapper=new QueryWrapper<>();
        return mapper.selectList(queryWrapper);
    }

    @Override
    public boolean isStudentExist(int studentID){
        QueryWrapper<Student> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",studentID);
        return mapper.selectCount(queryWrapper)>0;
    }

    @Override
    public boolean addStudent(Student student){
        return mapper.insert(student)>0;
    }

    @Override
    public boolean deleteStudent(Student student){
        QueryWrapper<Student> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",student.getStudentId());
        return mapper.delete(queryWrapper)>0;
    }


    @Override
    public Student selectStudent(int studentID){
        QueryWrapper<Student> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",studentID);
        if(mapper.selectCount(queryWrapper)>0){
            return mapper.selectOne(queryWrapper);
        }else {
            return null;
        }
    }







}
