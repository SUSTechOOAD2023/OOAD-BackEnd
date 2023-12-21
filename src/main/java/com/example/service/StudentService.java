package com.example.service;

import com.example.entity.Student;
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
public interface StudentService extends IService<Student> {

    List<Student> selectList();


    boolean isStudentExist(int studentID);

    boolean addStudent(Student student);

    boolean deleteStudent(Student student);


    Student selectStudent(int studentID);
}
