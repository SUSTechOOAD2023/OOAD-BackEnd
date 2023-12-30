package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.CourseClass;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.CourseClassMapper;
import com.example.service.CourseClassService;
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
public class CourseClassServiceImpl extends ServiceImpl<CourseClassMapper, CourseClass> implements CourseClassService {
    @Autowired
    CourseClassMapper mapper;
    @Override
    public boolean isClassExist(String className){
        QueryWrapper<CourseClass> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("class_name",className);
        return mapper.selectCount(queryWrapper)>0;
    }

    @Override
    public List<CourseClass> selectList(CourseClass courseClass){
        QueryWrapper<CourseClass> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(courseClass.getClassId()!=null, CourseClass::getClassId,courseClass.getClassId())
                .eq(courseClass.getClassName()!=null, CourseClass::getClassName,courseClass.getClassName())
                .eq(courseClass.getCourseId()!=null, CourseClass::getCourseId,courseClass.getCourseId());
        return mapper.selectList(queryWrapper);
    }

    //更新courseClass，返回courseClass的id
//    @Override
//    public int updateCourseClass(CourseClass courseClass){
//        mapper.insert(courseClass);
//        return courseClass.getClassId();
//    }
    @Override
    public int addCourse(String courseTitle, String courseName){
        CourseClass courseClass=new CourseClass();
        courseClass.setCourseTitle(courseTitle);
        courseClass.setCourseName(courseName);
        mapper.insert(courseClass);
        return courseClass.getClassId();
    }


    @Override
    public int update(CourseClass courseClass){
        QueryWrapper<CourseClass> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(courseClass.getClassId()!=null, CourseClass::getClassId,courseClass.getClassId())
                .eq(courseClass.getCourseName()!=null, CourseClass::getCourseName,courseClass.getCourseName())
                .eq(courseClass.getCourseId()!=null, CourseClass::getCourseId,courseClass.getCourseId());
        return mapper.update(courseClass,queryWrapper);
    }


    @Override
    public int delete(CourseClass courseClass){
        QueryWrapper<CourseClass> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(courseClass.getClassId()!=null, CourseClass::getClassId,courseClass.getClassId())
                .eq(courseClass.getCourseName()!=null, CourseClass::getCourseName,courseClass.getCourseName())
                .eq(courseClass.getCourseId()!=null, CourseClass::getCourseId,courseClass.getCourseId());
        return mapper.delete(queryWrapper);
    }
}
