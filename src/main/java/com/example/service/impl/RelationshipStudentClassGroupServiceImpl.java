package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.RelationshipStudentClassGroup;
import com.example.mapper.RelationshipStudentClassGroupMapper;
import com.example.service.RelationshipStudentClassGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
