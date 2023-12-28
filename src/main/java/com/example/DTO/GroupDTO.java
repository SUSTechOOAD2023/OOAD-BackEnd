package com.example.DTO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GroupDTO {
    private Integer groupId;

    private Integer teacherId;

    private Integer classId;

    private String groupName;

    private Integer groupSize;

    private Timestamp groupDeadline;

    private String groupTask;

    private Integer groupNumber;

    private Integer groupVisible;

    private Integer studentId;
}
