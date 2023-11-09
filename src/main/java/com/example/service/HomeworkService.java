package com.example.service;

import com.example.entity.Homework;
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
public interface HomeworkService extends IService<Homework> {
    List<Homework> selectList(Homework homework);
    int delete(Homework homework);
}
