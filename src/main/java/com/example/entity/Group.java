package com.example.entity;

import java.time.LocalDateTime;
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
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "group_id", type = IdType.AUTO)

      private Integer groupNumber;

    private Integer teacherId;

    private Integer classId;

    private String groupName;

    private Integer groupSize;

    private LocalDateTime groupDeadline;

    private String groupTask;


}
