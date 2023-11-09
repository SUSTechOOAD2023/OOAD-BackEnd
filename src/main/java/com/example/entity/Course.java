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
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "course_id", type = IdType.AUTO)
      private Integer courseId;

    private String courseName;


}
