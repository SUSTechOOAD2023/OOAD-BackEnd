package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.RelationshipStudentNotice;
import com.example.mapper.RelationshipStudentNoticeMapper;
import com.example.service.RelationshipStudentNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sending
 * @since 2023-12-28
 */
@Service
public class RelationshipStudentNoticeServiceImpl extends ServiceImpl<RelationshipStudentNoticeMapper, RelationshipStudentNotice> implements RelationshipStudentNoticeService {
    @Autowired
    RelationshipStudentNoticeMapper mapper;
    @Override
    public List<RelationshipStudentNotice> listStudentId(int studentId) {
        QueryWrapper<RelationshipStudentNotice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        return mapper.selectList(queryWrapper);
    }
    @Override
    public List<RelationshipStudentNotice> listNoticeId(int noticeId) {
        QueryWrapper<RelationshipStudentNotice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",noticeId);
        return mapper.selectList(queryWrapper);
    }
    @Override
    public boolean insert(int studentId, int noticeId){
        RelationshipStudentNotice relationshipStudentNotice = new RelationshipStudentNotice(null, studentId, noticeId);
        return saveOrUpdate(relationshipStudentNotice);
    }
    @Override
    public int delete(int studentId, int noticeId) {
        QueryWrapper<RelationshipStudentNotice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.eq("group_id",noticeId);
        return mapper.delete(queryWrapper);
    }
}