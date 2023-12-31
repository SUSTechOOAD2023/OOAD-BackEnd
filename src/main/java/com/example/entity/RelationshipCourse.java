package com.example.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2023-12-31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("relationship_course")
@EqualsAndHashCode(callSuper = false)
public class RelationshipCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId( type = IdType.AUTO)
    private Integer relationshipId;

    private Integer studentId;

    private Integer teacherId;

    private Integer courseId;

    private Integer saId;


}
