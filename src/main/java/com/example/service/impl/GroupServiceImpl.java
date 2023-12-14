package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Group;
import com.example.mapper.GroupMapper;
import com.example.service.GroupService;
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
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {
    @Autowired
    GroupMapper mapper;
    @Override
    public List<Group> selectList(Group group){
        QueryWrapper<Group> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(group.getGroupNumber()!=null,Group::getGroupNumber,group.getGroupNumber())
                .eq(group.getTeacherId()!=null,Group::getTeacherId,group.getTeacherId())
                .eq(group.getClassId()!=null,Group::getClassId,group.getClassId())
                .eq(group.getGroupName()!=null,Group::getGroupName,group.getGroupName())
                .eq(group.getGroupSize()!=null,Group::getGroupSize,group.getGroupSize())
                .eq(group.getGroupDeadline()!=null,Group::getGroupDeadline,group.getGroupDeadline())
                .eq(group.getGroupTask()!=null,Group::getGroupTask,group.getGroupTask());
        return mapper.selectList(queryWrapper);
    }
    @Override
    public int delete(Group group){
        QueryWrapper<Group> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(group.getGroupNumber()!=null,Group::getGroupNumber,group.getGroupNumber())
                .eq(group.getTeacherId()!=null,Group::getTeacherId,group.getTeacherId())
                .eq(group.getClassId()!=null,Group::getClassId,group.getClassId())
                .eq(group.getGroupName()!=null,Group::getGroupName,group.getGroupName())
                .eq(group.getGroupSize()!=null,Group::getGroupSize,group.getGroupSize())
                .eq(group.getGroupDeadline()!=null,Group::getGroupDeadline,group.getGroupDeadline())
                .eq(group.getGroupTask()!=null,Group::getGroupTask,group.getGroupTask());
        return mapper.delete(queryWrapper);
    }
}
