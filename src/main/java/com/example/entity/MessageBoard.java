package com.example.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * @since 2024-01-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MessageBoard implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "message_id", type = IdType.AUTO)
    private Integer messageId;

    private Integer studentId;

    private String message;

    private Timestamp messageTime;


}
