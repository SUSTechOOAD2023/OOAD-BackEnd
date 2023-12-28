package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Homework;
import com.example.entity.Notice;
import com.example.mapper.HomeworkMapper;
import com.example.mapper.NoticeMapper;
import com.example.service.NoticeService;
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
 * @since 2023-10-24
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Autowired
    NoticeMapper mapper;
    @Override
    public List<Notice> selectList(Notice notice){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(notice.getNoticeId()!=null,Notice::getNoticeId,notice.getNoticeId())
                .eq(notice.getTeacherId()!=null,Notice::getTeacherId,notice.getTeacherId())
                .eq(notice.getClassId()!=null,Notice::getClassId,notice.getClassId())
                .eq(notice.getNoticeContent()!=null,Notice::getNoticeContent,notice.getNoticeContent())
                .eq(notice.getNoticeTitle()!=null,Notice::getNoticeTitle,notice.getNoticeTitle());
        return mapper.selectList(queryWrapper);
    }
    @Override
    public int delete(Notice notice){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(notice.getNoticeId()!=null,Notice::getNoticeId,notice.getNoticeId())
                .eq(notice.getTeacherId()!=null,Notice::getTeacherId,notice.getTeacherId())
                .eq(notice.getClassId()!=null,Notice::getClassId,notice.getClassId())
                .eq(notice.getNoticeContent()!=null,Notice::getNoticeContent,notice.getNoticeContent())
                .eq(notice.getNoticeTitle()!=null,Notice::getNoticeTitle,notice.getNoticeTitle());
        return mapper.delete(queryWrapper);
    }
}
