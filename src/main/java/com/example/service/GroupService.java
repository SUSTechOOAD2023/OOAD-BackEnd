package com.example.service;

import com.example.entity.Group;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Student;

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

    Group selectVisibleList(int groupId);

    String delete(int groupId);

    //根据群组id，查看群组是否存在
    boolean isGroupExist(int groupId);

    //根据群组id，更新群组信息
    String updateGroup(int groupId, Group group);

    Group selectGroup(int studentId, int classId);


    List<Group> selectGroupStatus(Integer classId, Integer visible, Integer valid, Integer expired);

    //返回某个课程中不在群组中的学生
    //找到课程中的所有学生，然后找到课程中的所有群组中的学生，然后返回不在群组中的学生
    List<Student> selectStudentNotInGroup(int groupId);

    //找到某个课程中的所有群组
    List<Group> selectGroupInClass(int classId);
}
