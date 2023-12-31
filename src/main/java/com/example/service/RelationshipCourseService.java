package com.example.service;

import com.example.entity.RelationshipCourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-12-31
 */
public interface RelationshipCourseService extends IService<RelationshipCourse> {

//    int addRelationshipCourse(RelationshipCourse relationshipCourse);

    boolean deleteRelationshipCourse(int relationshipID);

    boolean isRelationshipCourseExist(int relationshipID);

    List<RelationshipCourse> selectList(RelationshipCourse relationshipCourse);
    RelationshipCourse selectRelationshipCourse(int relationshipID);

    String selectStudentList(int courseId);

    String selectTeacherList(int courseId);

    String selectSaList(int courseId);
}
