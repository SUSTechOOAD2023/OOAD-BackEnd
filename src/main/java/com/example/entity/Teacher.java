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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "teacher_id", type = IdType.AUTO)

      private Integer teacherId;

    private Integer accountId;

    private String teacherName;

    private String teacherGender;

    private String teacherDepartment;

    private String teacherInformation;


}
