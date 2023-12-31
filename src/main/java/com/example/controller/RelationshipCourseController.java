package com.example.controller;


import com.example.entity.RelationshipCourse;
import com.example.service.RelationshipCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
//        relationshipCourse.setTeacherId(10);
//        relationshipCourse.setRelationshipId(10);
        System.out.println(relationshipCourseService.saveOrUpdate(relationshipCourse));
        return relationshipCourse.getRelationshipId();
    }

    @PostMapping("/delete")
    public boolean deleteRelationshipCourse(@RequestParam int relationshipId){
        if (!relationshipCourseService.isRelationshipCourseExist(relationshipId)){
            return false;
        }
        return relationshipCourseService.deleteRelationshipCourse(relationshipId);
    }

    @PostMapping("/select")
    public RelationshipCourse selectRelationshipCourse(@RequestParam int relationshipId){
        if (!relationshipCourseService.isRelationshipCourseExist(relationshipId)){
            return null;
        }
        return relationshipCourseService.selectRelationshipCourse(relationshipId);
    }

//    @PostMapping("/list")
//    public String list(@RequestParam int courseId){
//        return relationshipCourseService.selectList(relationshipCourse).toString();
//    }

}

