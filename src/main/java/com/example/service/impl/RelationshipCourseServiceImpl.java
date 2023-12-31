package com.example.service.impl;

import com.example.entity.RelationshipCourse;
import com.example.mapper.RelationshipCourseMapper;
import com.example.service.RelationshipCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public int addRelationshipCourse(RelationshipCourse relationshipCourse){
        mapper.insert(relationshipCourse);
        return relationshipCourse.getRelationshipId();
    }

    @Override
    public boolean deleteRelationshipCourse(RelationshipCourse relationshipCourse){
        return mapper.deleteById(relationshipCourse.getRelationshipId())>0;
    }

    @Override
    public boolean isRelationshipCourseExist(int relationshipID){
        return mapper.selectById(relationshipID)!=null;
    }

    @Override
    public RelationshipCourse selectRelationshipCourse(int relationshipID){
        return mapper.selectById(relationshipID);
    }

}
