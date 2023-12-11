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

    boolean isClassExist(String className);

    List<CourseClass> selectList(CourseClass courseClass);

    int delete(CourseClass courseClass);
}
