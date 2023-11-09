package com.example.service.impl;

import com.example.entity.Homework;
import com.example.mapper.HomeworkMapper;
import com.example.service.HomeworkService;
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
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework> implements HomeworkService {
    @Autowired
    HomeworkMapper mapper;
//
//    @Override
//    public List<Homework>

}
