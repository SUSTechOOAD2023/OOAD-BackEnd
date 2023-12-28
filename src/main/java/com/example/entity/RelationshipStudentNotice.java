package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class RelationshipStudentNotice implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "relation_student_notice_id", type = IdType.AUTO)

    private Integer relationId;

    private Integer studentId;

    private Integer noticeId;


}
