package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.GradeBook;
import com.example.mapper.GradeBookMapper;
import com.example.service.GradeBookService;
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
public class GradeBookServiceImpl extends ServiceImpl<GradeBookMapper, GradeBook> implements GradeBookService {
    @Autowired
    GradeBookMapper mapper;
    @Override
    public List<GradeBook> selectList(GradeBook gradeBook){
        QueryWrapper<GradeBook> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(gradeBook.getGradebookId()!=null,GradeBook::getGradebookId,gradeBook.getGradebookId())
                .eq(gradeBook.getStudentId()!=null,GradeBook::getStudentId,gradeBook.getStudentId())
                .eq(gradeBook.getClassId()!=null,GradeBook::getClassId,gradeBook.getClassId())
                .eq(gradeBook.getGradebookContent()!=null,GradeBook::getGradebookContent,gradeBook.getGradebookContent());
        return mapper.selectList(queryWrapper);
    }
    @Override
    public int delete(GradeBook gradeBook){
        QueryWrapper<GradeBook> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(gradeBook.getGradebookId()!=null,GradeBook::getGradebookId,gradeBook.getGradebookId())
                .eq(gradeBook.getStudentId()!=null,GradeBook::getStudentId,gradeBook.getStudentId())
                .eq(gradeBook.getClassId()!=null,GradeBook::getClassId,gradeBook.getClassId())
                .eq(gradeBook.getGradebookContent()!=null,GradeBook::getGradebookContent,gradeBook.getGradebookContent());
        return mapper.delete(queryWrapper);
    }
}
