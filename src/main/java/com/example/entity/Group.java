package com.example.entity;

import java.time.LocalDateTime;
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
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer groupNumber;

    private Integer teacherId;

    private Integer classId;

    private String groupName;

    private Integer groupSize;

    private LocalDateTime groupDeadline;

    private String groupTask;


}
