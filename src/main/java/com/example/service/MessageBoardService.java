package com.example.service;

import com.example.entity.MessageBoard;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2024-01-03
 */
public interface MessageBoardService extends IService<MessageBoard> {
    List<MessageBoard> selectList(MessageBoard messageBoard);
    int delete(MessageBoard messageBoard);
}
