package com.example.service;

import com.example.entity.RelationshipStudentClassGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-12-28
 */
public interface RelationshipStudentClassGroupService extends IService<RelationshipStudentClassGroup> {

    int deleteStudentFromGroup(int studentId, int groupId);
    List<RelationshipStudentClassGroup> selectList(RelationshipStudentClassGroup relationshipStudentClassGroup);

    //给出studentId,groupId，检查关系是否存在
    boolean checkRelation(int studentId, int groupId);
}
