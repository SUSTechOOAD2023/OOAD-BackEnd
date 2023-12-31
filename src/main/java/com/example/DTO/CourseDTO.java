package com.example.DTO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CourseDTO {
    private Integer studentId;
    private String studentName;
}
