package com.example.service;

import com.example.entity.GradeBook;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Homework;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
public interface GradeBookService extends IService<GradeBook> {
    List<GradeBook> selectList(GradeBook gradeBook);
    int delete(GradeBook gradeBook);
}
