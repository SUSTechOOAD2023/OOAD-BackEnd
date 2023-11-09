package com.example.entity;

import java.io.Serial;
import java.sql.Time;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.sql.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author sending
 * @since 2023-10-14
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("conference_room")
public class ConferenceRoom implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "room_id", type = IdType.AUTO)
    private Integer roomId;

    @TableField("room_name")
    private String roomName;

    @TableField("department")
    private String department;

    @TableField("type")
    private String type;

    @TableField("location")
    private String location;

    @TableField("date")
    private Date date;

    @TableField("start_time")
    private Time startTime;

    @TableField("end_time")
    private Time endTime;

    @TableField("max_duration")
    private String maxDuration;


}
