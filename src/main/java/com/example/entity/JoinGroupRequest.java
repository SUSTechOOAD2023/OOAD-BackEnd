package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

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
public class JoinGroupRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(value = "join_group_request_id", type = IdType.AUTO)
      private Integer joinGroupRequestId;

    private Integer studentId;

    private Integer groupId;

    private String requestContent;

    private Timestamp requestTime;

    private int isAccepted;

}
