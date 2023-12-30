package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.CourseClass;
import com.example.service.CourseClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
@RestController
@RequestMapping("/class")
public class CourseClassController {
    @Autowired
    CourseClassService service;
    @PostMapping("/list")
    public String list(@RequestBody CourseClass courseClass) {
        return JSON.toJSONString(service.selectList(courseClass));
    }
    @RequestMapping("/all")
    public String all() {
        return JSON.toJSONString(service.selectList(new CourseClass()));
    }
    @PostMapping("/new")
    public int addCourse(@RequestParam String courseTitle, @RequestParam String courseName){
        return service.addCourse(courseTitle,courseName);
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam int classId){
        return service.delete(classId);
    }

    @PostMapping("/update")
    public String update(@RequestBody CourseClass courseClass){
        if (!service.isCourseExist(courseClass.getClassId())){
            return "该课程不存在";
        }
        service.saveOrUpdate(courseClass);
        return "更新成功";
    }

}

