package com.example.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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
public class Notice implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(value = "notice_id", type = IdType.AUTO)
      private Integer noticeId;

    private Integer classId;

    private String noticeContent;

    private String noticeTitle;

    private String releaseTime;
}
