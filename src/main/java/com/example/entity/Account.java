package com.example.entity;

import java.io.Serial;
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
 * @since 2023-10-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("account")
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(value = "account_id", type = IdType.AUTO)
    private Integer accountId;

    private Integer teacherId;

    private Integer studentId;

    private String accountName;

    private String accountPassword;

    private String accountType;


}
