package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Class;
import com.example.mapper.ClassMapper;
import com.example.service.ClassService;
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
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {
    @Autowired
    ClassMapper mapper;
    @Override
    public boolean isClassExist(String className){
        QueryWrapper<Class> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("class_name",className);
        return mapper.selectCount(queryWrapper)>0;
    }

    @Override
    public List<Class> selectList(){
        QueryWrapper<Class> queryWrapper=new QueryWrapper<>();
        return mapper.selectList(queryWrapper);
    }





}
