package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.JoinGroupInvitation;
import com.example.mapper.JoinGroupInvitationMapper;
import com.example.service.JoinGroupInvitationService;
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
public class JoinGroupInvitationServiceImpl extends ServiceImpl<JoinGroupInvitationMapper, JoinGroupInvitation> implements JoinGroupInvitationService {
    @Autowired
    JoinGroupInvitationMapper mapper;
    @Override
    public List<JoinGroupInvitation> selectList(JoinGroupInvitation joinGroupInvitation){
        QueryWrapper<JoinGroupInvitation> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(joinGroupInvitation.getJoinGroupInvitationId()!=null,JoinGroupInvitation::getJoinGroupInvitationId,joinGroupInvitation.getReceiveStudentId())
                .eq(joinGroupInvitation.getReceiveStudentId()!=null,JoinGroupInvitation::getReceiveStudentId,joinGroupInvitation.getReceiveStudentId())
                .eq(joinGroupInvitation.getSendStudentId()!=null,JoinGroupInvitation::getSendStudentId,joinGroupInvitation.getSendStudentId())
                .eq(joinGroupInvitation.getGroupId()!=null,JoinGroupInvitation::getGroupId,joinGroupInvitation.getGroupId())
                .eq(joinGroupInvitation.getInvitationContent()!=null,JoinGroupInvitation::getInvitationContent,joinGroupInvitation.getInvitationContent());
        return mapper.selectList(queryWrapper);
    }
    @Override
    public int delete(JoinGroupInvitation joinGroupInvitation){
        QueryWrapper<JoinGroupInvitation> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(joinGroupInvitation.getJoinGroupInvitationId()!=null,JoinGroupInvitation::getJoinGroupInvitationId,joinGroupInvitation.getReceiveStudentId())
                .eq(joinGroupInvitation.getReceiveStudentId()!=null,JoinGroupInvitation::getReceiveStudentId,joinGroupInvitation.getReceiveStudentId())
                .eq(joinGroupInvitation.getSendStudentId()!=null,JoinGroupInvitation::getSendStudentId,joinGroupInvitation.getSendStudentId())
                .eq(joinGroupInvitation.getGroupId()!=null,JoinGroupInvitation::getGroupId,joinGroupInvitation.getGroupId())
                .eq(joinGroupInvitation.getInvitationContent()!=null,JoinGroupInvitation::getInvitationContent,joinGroupInvitation.getInvitationContent());
        return mapper.delete(queryWrapper);
    }
}
