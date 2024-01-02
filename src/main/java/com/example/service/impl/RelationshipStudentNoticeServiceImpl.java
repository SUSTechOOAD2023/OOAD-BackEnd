package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.RelationshipStudentNotice;
import com.example.mapper.RelationshipStudentNoticeMapper;
import com.example.service.RelationshipStudentNoticeService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
    public List<RelationshipStudentNotice> selectList(RelationshipStudentNotice relationshipStudentNotice){
        QueryWrapper<RelationshipStudentNotice> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(relationshipStudentNotice.getRelationId()!=null,RelationshipStudentNotice::getRelationId,relationshipStudentNotice.getRelationId())
                .eq(relationshipStudentNotice.getNoticeId()!=null,RelationshipStudentNotice::getNoticeId,relationshipStudentNotice.getNoticeId())
                .eq(relationshipStudentNotice.getStudentId()!=null,RelationshipStudentNotice::getStudentId,relationshipStudentNotice.getStudentId());
        return mapper.selectList(queryWrapper);
    }
    @Override
    public List<Integer> listStudentId(int studentId) {
        QueryWrapper<RelationshipStudentNotice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        List<RelationshipStudentNotice> list = mapper.selectList(queryWrapper);
        List<Integer> ret = new ArrayList<>();
        for (RelationshipStudentNotice relationshipStudentNotice : list) {
            ret.add(relationshipStudentNotice.getNoticeId());
        }
        return ret;
    }
    @Override
    public List<Integer> listNoticeId(int noticeId) {
        QueryWrapper<RelationshipStudentNotice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("notice_id",noticeId);
        List<RelationshipStudentNotice> list = mapper.selectList(queryWrapper);
        List<Integer> ret = new ArrayList<Integer>();
        for (RelationshipStudentNotice relationshipStudentNotice : list) {
            ret.add(relationshipStudentNotice.getStudentId());
        }
        return ret;
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
        queryWrapper.eq("notice_id",noticeId);
        return mapper.delete(queryWrapper);
    }
    @Override
    public int deleteStudent(int studentId) {
        QueryWrapper<RelationshipStudentNotice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        return mapper.delete(queryWrapper);
    }

    @Override
    public int deleteNotice(int noticeId) {
        QueryWrapper<RelationshipStudentNotice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("notice_id",noticeId);
        return mapper.delete(queryWrapper);
    }
}
