package com.example.entity;

import java.io.Serial;
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
public class Submission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(value = "submission_id", type = IdType.AUTO)

    private Integer submissionId;

    private Integer groupId;

    private Integer studentId;

    private Integer homeworkId;

    private String submissionContent;

    private String submissionComment;

    private Double submissionScore;

    private String submissionTime;
}
