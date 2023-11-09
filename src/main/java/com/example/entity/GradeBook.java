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
public class GradeBook implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer gradebookId;

    private Integer studentId;

    private Integer classId;

    private String gradebookContent;


}
