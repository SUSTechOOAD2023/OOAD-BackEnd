package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

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
 * @since 2023-12-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VerifyCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "verify_code_id", type = IdType.AUTO)

    private int verifyCodeId;

    private String email;

    private String identity;

    @TableField("verifyCode")
    private String verifyCode;


}
