package com.example.service;

import com.example.entity.CourseClass;
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
public interface CourseClassService extends IService<CourseClass> {

    boolean isCourseExist(String className);

    List<CourseClass> selectList(CourseClass courseClass);

    //更新courseClass，返回courseClass的id
//    @Override
//    public int updateCourseClass(CourseClass courseClass){
//        mapper.insert(courseClass);
//        return courseClass.getClassId();
//    }
    int addCourse(String courseTitle, String courseName);

//    int update(CourseClass courseClass);

    int delete(CourseClass courseClass);
}
