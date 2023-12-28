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
 * @since 2023-12-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("relationship_student_group")
public class RelationshipStudentClassGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "relation_student_class_group_id", type = IdType.AUTO)
    private Integer relation_id;
//    @TableId(value = "student_id", type = IdType.NONE)
    private Integer studentId;

//    @TableId(value = "group_id", type = IdType.NONE)
    private Integer groupId;


}
