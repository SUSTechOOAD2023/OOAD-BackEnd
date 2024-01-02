package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.RelationshipStudentClassGroup;
import com.example.mapper.RelationshipStudentClassGroupMapper;
import com.example.service.RelationshipStudentClassGroupService;
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
 * @since 2023-12-28
 */
@Service
public class RelationshipStudentClassGroupServiceImpl extends ServiceImpl<RelationshipStudentClassGroupMapper, RelationshipStudentClassGroup> implements RelationshipStudentClassGroupService {
    @Autowired
    RelationshipStudentClassGroupMapper mapper;
    @Override
    public int deleteStudentFromGroup(int studentId, int groupId) {
        QueryWrapper<RelationshipStudentClassGroup> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.eq("group_id",groupId);
        return mapper.delete(queryWrapper);
    }

    @Override
    public List<RelationshipStudentClassGroup> selectList(RelationshipStudentClassGroup relationshipStudentClassGroup){
        QueryWrapper<RelationshipStudentClassGroup> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(relationshipStudentClassGroup.getStudentId()!=null,RelationshipStudentClassGroup::getStudentId,relationshipStudentClassGroup.getStudentId())
                .eq(relationshipStudentClassGroup.getGroupId()!=null,RelationshipStudentClassGroup::getGroupId,relationshipStudentClassGroup.getGroupId())
                .eq(relationshipStudentClassGroup.getRelationId()!=null,RelationshipStudentClassGroup::getRelationId,relationshipStudentClassGroup.getRelationId());
        return mapper.selectList(queryWrapper);
    }

    @Override
    //给出studentId,groupId，检查关系是否存在
    public boolean checkRelation(int studentId, int groupId){
        QueryWrapper<RelationshipStudentClassGroup> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(RelationshipStudentClassGroup::getStudentId,studentId)
                .eq(RelationshipStudentClassGroup::getGroupId,groupId);
        return !mapper.selectList(queryWrapper).isEmpty();
    }

    //返回某个群组中的所有学生
    @Override
    public List<RelationshipStudentClassGroup> selectStudentList(int groupId){
        QueryWrapper<RelationshipStudentClassGroup> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(RelationshipStudentClassGroup::getGroupId,groupId);
        return mapper.selectList(queryWrapper);
    }


}
