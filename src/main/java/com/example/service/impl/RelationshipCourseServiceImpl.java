package com.example.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.*;
import com.example.mapper.RelationshipCourseMapper;
import com.example.service.RelationshipCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJLambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sending
 * @since 2023-12-31
 */
@Service
public class RelationshipCourseServiceImpl extends ServiceImpl<RelationshipCourseMapper, RelationshipCourse> implements RelationshipCourseService {
    @Autowired
    RelationshipCourseMapper mapper;

//    @Override
//    public int addRelationshipCourse(RelationshipCourse relationshipCours){
//        mapper.insert(relationshipCourse);
//        return relationshipCourse.getRelationshipId();
//    }

    @Override
    public boolean deleteRelationshipCourse(int relationshipID){
        return mapper.deleteById(relationshipID)>0;
    }

    @Override
    public boolean isRelationshipCourseExist(int relationshipID){
        return mapper.selectById(relationshipID)!=null;
    }

    @Override
    public RelationshipCourse selectRelationshipCourse(int relationshipID){
        return mapper.selectById(relationshipID);
    }

    @Override
    public String selectStudentList(int courseId){
        //如果没有这门课程，返回null
        if (mapper.selectById(courseId)==null){
            return null;
        }
        MPJLambdaWrapper<RelationshipCourse> wrapper = new MPJLambdaWrapper<RelationshipCourse>()
                .selectAll(RelationshipCourse.class)//查询group表全部字段
                .select(Student::getStudentName)//查询studentId字段
                .leftJoin(Student.class, Student::getStudentId, RelationshipCourse::getStudentId);
        wrapper.eq(RelationshipCourse::getCourseId, courseId);
        wrapper.isNotNull(RelationshipCourse::getStudentId);
        return JSON.toJSONString(mapper.selectList(wrapper));
    }


    @Override
    public String selectTeacherList(int courseId){
        //如果没有这门课程，返回null
        if (mapper.selectById(courseId)==null){
            return null;
        }
        MPJLambdaWrapper<RelationshipCourse> wrapper = new MPJLambdaWrapper<RelationshipCourse>()
                .selectAll(RelationshipCourse.class)//查询group表全部字段
                .select(Teacher::getTeacherName)//查询studentId字段
                .leftJoin(Teacher.class, Teacher::getTeacherId, RelationshipCourse::getTeacherId);
        wrapper.eq(RelationshipCourse::getCourseId, courseId);
        wrapper.isNotNull(RelationshipCourse::getTeacherId);
        return JSON.toJSONString(mapper.selectList(wrapper));
    }

    @Override
    public String selectSaList(int courseId){
        //如果没有这门课程，返回null
        if (mapper.selectById(courseId)==null){
            return null;
        }
        MPJLambdaWrapper<RelationshipCourse> wrapper = new MPJLambdaWrapper<RelationshipCourse>()
                .selectAll(RelationshipCourse.class)//查询group表全部字段
                .select(Student::getStudentName)//查询studentId字段
                .leftJoin(Student.class, Student::getStudentId, RelationshipCourse::getStudentId);
        wrapper.eq(RelationshipCourse::getCourseId, courseId);
        wrapper.isNotNull(RelationshipCourse::getSaId);
        return JSON.toJSONString(mapper.selectList(wrapper));
    }

}
