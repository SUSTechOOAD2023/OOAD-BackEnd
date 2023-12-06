package com.example.entity;

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
 * @since 2023-11-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("invite_code")
public class InviteCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "invite_code_id", type = IdType.AUTO)
    private Integer inviteCodeId;

    private String code;

    private String identity;

    private Integer isUsed;


}
