package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.MessageBoard;
import com.example.entity.Notice;
import com.example.mapper.MessageBoardMapper;
import com.example.mapper.NoticeMapper;
import com.example.service.MessageBoardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sending
 * @since 2024-01-03
 */
@Service
public class MessageBoardServiceImpl extends ServiceImpl<MessageBoardMapper, MessageBoard> implements MessageBoardService {
    @Autowired
    MessageBoardMapper mapper;
    @Override
    public List<MessageBoard> selectList(MessageBoard messageBoard){
        QueryWrapper<MessageBoard> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(messageBoard.getMessageId()!=null,MessageBoard::getMessageId,messageBoard.getMessageId());
        return mapper.selectList(queryWrapper);
    }
    @Override
    public int delete(MessageBoard messageBoard){
        QueryWrapper<MessageBoard> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(messageBoard.getMessageId()!=null,MessageBoard::getMessageId,messageBoard.getMessageId());
        return mapper.delete(queryWrapper);
    }
}
