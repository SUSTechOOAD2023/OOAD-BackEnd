package com.example.service;

import com.example.entity.Class;
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
public interface ClassService extends IService<Class> {

    boolean isClassExist(String className);

    List<Class> selectList();
}
