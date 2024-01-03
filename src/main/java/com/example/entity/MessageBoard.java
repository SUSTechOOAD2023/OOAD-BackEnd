package com.example.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author sending
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MessageBoard implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer messageId;

    private Integer studentId;

    private String message;


}
