package com.example.entity;

import java.io.Serializable;

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

    private static final long serialVersionUID = 1L;

      private Integer submissionId;

    private Integer groupNumber;

    private Integer studentId;

    private Integer homeworkId;

    private String submissionContent;

    private String submissionComment;

    private Double submissionScore;


}
