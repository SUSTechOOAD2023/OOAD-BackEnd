package com.example.service;

import com.example.entity.RelationshipCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-12-31
 */
public interface RelationshipCourseService extends IService<RelationshipCourse> {

    int addRelationshipCourse(RelationshipCourse relationshipCourse);

    boolean deleteRelationshipCourse(RelationshipCourse relationshipCourse);

    boolean isRelationshipCourseExist(int relationshipID);

    RelationshipCourse selectRelationshipCourse(int relationshipID);
}
