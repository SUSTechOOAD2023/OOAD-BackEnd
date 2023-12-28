package com.example.service;

import com.example.entity.Group;
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
public interface GroupService extends IService<Group> {

    List<Group> selectList();

    int addGroup(String groupName, int classId);

    Group selectList(int groupId);

    String delete(int groupId);

    //根据群组id，查看群组是否存在
    boolean isGroupExist(int groupId);

    //根据群组id，更新群组信息
    String updateGroup(int groupId, Group group);

    Group selectGroup(int studentId, int classId);

    List<Group> selectGroupStatus(int classId, int visible, int valid, int expired);
}
