package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.CourseClass;
import com.example.entity.Group;
import com.example.entity.RelationshipStudentClassGroup;
import com.example.mapper.CourseClassMapper;
import com.example.mapper.GroupMapper;
import com.example.service.GroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {
    @Autowired
    GroupMapper mapper;
    @Autowired
    CourseClassMapper courseClassMapper;


    @Override
    public List<Group> selectList() {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        return mapper.selectList(queryWrapper);
    }

    //加入新的群组，设定名字/所属课程id，返回新群组的id
    @Override
    public int addGroup(String groupName, int classId) {
        Group group = new Group();
        group.setGroupName(groupName);
        group.setClassId(classId);
        mapper.insert(group);
        return group.getGroupId();
    }

    //根据群组id，返回群组
    @Override
    public Group selectList(int groupId) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", groupId);
        return mapper.selectOne(queryWrapper);
    }

    //根据群组id，删除群组
    @Override
    public String delete(int groupId) {
        if (!isGroupExist(groupId)) {
            return "该群组不存在";
        }
        mapper.deleteById(groupId);
        return "删除成功";
    }

    //根据群组id，查看群组是否存在
    @Override
    public boolean isGroupExist(int groupId) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", groupId);
        return mapper.selectCount(queryWrapper) > 0;
    }

    //根据群组id，更新群组信息
    @Override
    public String updateGroup(int groupId, Group group) {
        if (!isGroupExist(groupId)) {
            return "该群组不存在";
        }
        return "更新成功";
    }

    @Override
    public Group selectGroup(int studentId, int classId) {
        MPJLambdaWrapper<Group> wrapper = new MPJLambdaWrapper<Group>()
                .selectAll(Group.class)//查询group表全部字段
                .select(RelationshipStudentClassGroup::getStudentId)//查询studentId字段
                .leftJoin(RelationshipStudentClassGroup.class, RelationshipStudentClassGroup::getGroupId, Group::getGroupId);
        wrapper.eq(RelationshipStudentClassGroup::getStudentId, studentId);
        wrapper.eq(Group::getClassId, classId);
        return mapper.selectOne(wrapper);
    }

    @Override
    public List<Group> selectGroupStatus(Integer classId, Integer visible, Integer valid, Integer expired) {
        CourseClass courseClass = courseClassMapper.selectById(classId);
        if (courseClass == null) {
            return null;
        }
        int min = courseClass.getMinimumGroupSize();
        int max = courseClass.getMaximumGroupSize();
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_id", classId);
        if (visible != null) {
            if (visible == 1) {
                queryWrapper.eq("group_visible", visible);
            } else {
                queryWrapper.ne("group_visible", visible);
            }
        }
        if (valid != null) {
            if (valid == 1) {
                queryWrapper.between("group_size", min, max);
            } else {
                queryWrapper.notBetween("group_size", min, max);
            }
        }
        if (expired != null) {
            if (expired == 0) {
                queryWrapper.gt("group_deadline", LocalDateTime.now());
            } else {
                queryWrapper.lt("group_deadline", LocalDateTime.now());
            }
        }
        return mapper.selectList(queryWrapper);
    }

}
