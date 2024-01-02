package com.example.service;

import com.example.entity.Teacher;
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
public interface TeacherService extends IService<Teacher> {

        boolean isTeacherExist(int teacherID);

        boolean addTeacher(Teacher teacher);

        boolean deleteTeacher(Teacher teacher);



    List<Teacher> selectList(Teacher teacher);

    Teacher selectTeacher(int teacherID);
}
