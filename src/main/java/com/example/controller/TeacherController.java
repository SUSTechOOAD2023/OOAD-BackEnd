package com.example.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.entity.Account;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.service.AccountService;
import com.example.service.TeacherService;
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
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService service1;
    @Autowired
    AccountService service2;
    @ApiOperation(value = "保存并更新某个老师的信息", tags = "老师类")
    @PostMapping("/update")
    public String updateTeacher(@RequestBody Teacher teacher){
        if(!service1.isTeacherExist(teacher.getTeacherId())){
            return "该老师不存在";
        }
        service1.saveOrUpdate(teacher);
        return "更新成功";
    }
    @ApiOperation(value = "返回某个老师的信息", tags = "老师类")
    @RequestMapping("/list")
    public String list(@RequestParam int teacherId){
        if (!service1.isTeacherExist(teacherId)){
            return "老师不存在";
        }
        Teacher teacher=service1.selectTeacher(teacherId);
        Account account=service2.selectAccount(teacher.getAccountId());
        Account account1=new Account();
        account1=account;
        account1.setAccountPassword("");
        // 将对象转换为JSON对象
        JSONObject accountJson = JSONObject.parseObject(JSON.toJSONString(account1));
        JSONObject teacherJson = JSONObject.parseObject(JSON.toJSONString(teacher));

        // 创建新的JSON对象并合并数据
        JSONObject mergedJson = new JSONObject();
        mergedJson.put("account", accountJson);
        mergedJson.put("teacher", teacherJson);

        // 将合并后的JSON对象转换为字符串
        return mergedJson.toJSONString();
    }

    @ApiOperation(value = "返回某个老师的ID", tags = "老师类")
    @PostMapping("/getTeacherID")
    public int getTeacherID(@RequestParam int accountID){
        Account account=service2.selectAccount(accountID);
        if (account.getTeacherId()!=null){
            return account.getTeacherId();
        }else {
            return -1;
        }
    }


}

