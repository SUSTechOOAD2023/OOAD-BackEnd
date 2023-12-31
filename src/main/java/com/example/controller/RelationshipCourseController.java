package com.example.controller;


import com.example.entity.RelationshipCourse;
import com.example.service.RelationshipCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sending
 * @since 2023-12-31
 */
@RestController
@RequestMapping("/relationshipCourse")
public class RelationshipCourseController {
    @Autowired
    RelationshipCourseService relationshipCourseService;

    @PostMapping("/add")
    public int addRelationshipCourse(@RequestBody RelationshipCourse relationshipCourse){
        return relationshipCourseService.addRelationshipCourse(relationshipCourse);
    }

    @PostMapping("/delete")
    public boolean deleteRelationshipCourse(@RequestBody RelationshipCourse relationshipCourse){
        if (!relationshipCourseService.isRelationshipCourseExist(relationshipCourse.getRelationshipId())){
            return false;
        }
        return relationshipCourseService.deleteRelationshipCourse(relationshipCourse);
    }

    @PostMapping("/select")
    public RelationshipCourse selectRelationshipCourse(@RequestBody RelationshipCourse relationshipCourse){
        if (!relationshipCourseService.isRelationshipCourseExist(relationshipCourse.getRelationshipId())){
            return null;
        }
        return relationshipCourseService.selectRelationshipCourse(relationshipCourse.getRelationshipId());
    }

}

