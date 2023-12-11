package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Course;
import com.example.entity.CourseClass;
import com.example.mapper.CourseMapper;
import com.example.service.CourseService;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    CourseMapper mapper;

    @Override
    public List<Course> selectList(Course course){
        QueryWrapper<Course> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(course.getCourseId()!=null, Course::getCourseId,course.getCourseId())
                .eq(course.getCourseName()!=null, Course::getCourseName,course.getCourseName())
                .eq(course.getClass()!=null, Course::getClass,course.getClass());
        return mapper.selectList(queryWrapper);
    }

    @Override
    public int delete(Course course){
        QueryWrapper<Course> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(course.getCourseId()!=null, Course::getCourseId,course.getCourseId())
                .eq(course.getCourseName()!=null, Course::getCourseName,course.getCourseName())
                .eq(course.getClass()!=null, Course::getClass,course.getClass());
        return mapper.delete(queryWrapper);
    }
}
