package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.example.entity.Account;
import com.example.entity.Student;
import com.example.service.AccountService;
import com.example.service.StudentService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService service1;
    @Autowired
    AccountService service2;


    @ApiOperation(value = "保存并更新某个学生的信息", tags = "学生类")
    @PostMapping("/update")
    public String updateStudent(@RequestBody Student student){
        if(!service1.isStudentExist(student.getStudentId())){
            return "学生不存在";
        }
        service1.saveOrUpdate(student);
        return "更新成功";
    }
    @ApiOperation(value = "返回某个学生的信息", tags = "学生类")
    @RequestMapping("/list")
    public String list(@RequestParam int StudentID){
        if (!service1.isStudentExist(StudentID)){
            return "学生不存在";
        }
        Student student=service1.selectStudent(StudentID);
        Account account=service2.selectAccount(student.getAccountId());
        Account account1=new Account();
        account1=account;
        account1.setAccountPassword("");
        return JSON.toJSONString(account1)+"\n"+JSON.toJSONString(student);
    }



}

