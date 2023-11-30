package com.example.service;

import com.example.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.CourseClass;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
public interface CourseService extends IService<Course> {
    List<Course> selectList(Course course);

    int delete(Course course);
}
