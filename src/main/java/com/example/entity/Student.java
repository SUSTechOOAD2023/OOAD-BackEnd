package com.example.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "student_id", type = IdType.AUTO)

    private Integer studentId;

    private Integer classId;

    private Integer groupId;

    private Integer accountId;

    private String studentName;

    private String studentGender;

    private String technicalStack;

    private String programmingSkills;

    private String intendedTeammates;

    private String studentDepartment;

    private String studentInformation;


}
