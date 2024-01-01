package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.controller.RelationshipStudentClassGroupController;
import com.example.entity.JoinGroupInvitation;
import com.example.entity.Student;
import com.example.mapper.JoinGroupInvitationMapper;
import com.example.mapper.RelationshipStudentClassGroupMapper;
import com.example.service.JoinGroupInvitationService;
import com.example.service.RelationshipStudentClassGroupService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    RelationshipStudentClassGroupController relationshipStudentClassGroupController;
    @Override
    public List<JoinGroupInvitation> selectList(JoinGroupInvitation joinGroupInvitation){
        QueryWrapper<JoinGroupInvitation> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(joinGroupInvitation.getJoinGroupInvitationId()!=null,JoinGroupInvitation::getJoinGroupInvitationId,joinGroupInvitation.getReceiveStudentId())
                .eq(joinGroupInvitation.getReceiveStudentId()!=null,JoinGroupInvitation::getReceiveStudentId,joinGroupInvitation.getReceiveStudentId())
                .eq(joinGroupInvitation.getSendStudentId()!=null,JoinGroupInvitation::getSendStudentId,joinGroupInvitation.getSendStudentId())
                .eq(joinGroupInvitation.getGroupId()!=null,JoinGroupInvitation::getGroupId,joinGroupInvitation.getGroupId())
                ;
        return mapper.selectList(queryWrapper);
    }
    @Override
    public int delete(JoinGroupInvitation joinGroupInvitation){
        QueryWrapper<JoinGroupInvitation> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(joinGroupInvitation.getJoinGroupInvitationId()!=null,JoinGroupInvitation::getJoinGroupInvitationId,joinGroupInvitation.getReceiveStudentId())
                .eq(joinGroupInvitation.getReceiveStudentId()!=null,JoinGroupInvitation::getReceiveStudentId,joinGroupInvitation.getReceiveStudentId())
                .eq(joinGroupInvitation.getSendStudentId()!=null,JoinGroupInvitation::getSendStudentId,joinGroupInvitation.getSendStudentId())
                .eq(joinGroupInvitation.getGroupId()!=null,JoinGroupInvitation::getGroupId,joinGroupInvitation.getGroupId());
        return mapper.delete(queryWrapper);
    }


    @Override
    public int addInvitation(@RequestParam int receiveStudentId, @RequestParam int sendStudentId, @RequestParam int groupId){
        JoinGroupInvitation joinGroupInvitation=new JoinGroupInvitation();
        joinGroupInvitation.setReceiveStudentId(receiveStudentId);
        joinGroupInvitation.setSendStudentId(sendStudentId);
        joinGroupInvitation.setGroupId(groupId);
        joinGroupInvitation.setIsAccepted(0);
//        joinGroupInvitation.setInvitationContent(invitationContent);
        mapper.insert(joinGroupInvitation);
        return joinGroupInvitation.getJoinGroupInvitationId();
    }

    //查询所有发起的邀请，然后返回被邀请人的id和姓名
    @Override
    public List<JoinGroupInvitation> searchSend(int sendStudentId){
        MPJLambdaWrapper<JoinGroupInvitation> wrapper = new MPJLambdaWrapper<JoinGroupInvitation>()
                .selectAll(JoinGroupInvitation.class)//查询invitation表全部字段
                .select(Student::getStudentName)//查询studentName字段
                .leftJoin(Student.class, Student::getStudentId, JoinGroupInvitation::getReceiveStudentId);
        wrapper.eq("sendStudentId",sendStudentId);
        return mapper.selectList(wrapper);
    }

    //查询所有接受的邀请，然后返回邀请人的id和姓名
    @Override
    public List<JoinGroupInvitation> searchReceive(int receiveStudentId){
        MPJLambdaWrapper<JoinGroupInvitation> wrapper = new MPJLambdaWrapper<JoinGroupInvitation>()
                .selectAll(JoinGroupInvitation.class)//查询invitation表全部字段
                .select(Student::getStudentName)//查询studentName字段
                .leftJoin(Student.class, Student::getStudentId, JoinGroupInvitation::getSendStudentId);
        wrapper.eq("receiveStudentId",receiveStudentId);
        return mapper.selectList(wrapper);
    }


    //查询邀请是否存在
    public boolean isInvitationExist(int joinGroupInvitationId){
        QueryWrapper<JoinGroupInvitation> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("join_group_invitation_id",joinGroupInvitationId);
        return mapper.selectCount(queryWrapper)>0;
    }

    //根据邀请id查询邀请
    @Override
    public JoinGroupInvitation selectInvitation(int joinGroupInvitationId){
        if(!isInvitationExist(joinGroupInvitationId)){
            return null;
        }
        QueryWrapper<JoinGroupInvitation> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("join_group_invitation_id",joinGroupInvitationId);
        return mapper.selectOne(queryWrapper);
    }

    //接受邀请
    @Override
    public String acceptInvitation(int joinGroupInvitationId){
        if (!isInvitationExist(joinGroupInvitationId)){
            return "该邀请不存在";
        }
        JoinGroupInvitation joinGroupInvitation=selectInvitation(joinGroupInvitationId);
        joinGroupInvitation.setIsAccepted(1);
        mapper.updateById(joinGroupInvitation);
        relationshipStudentClassGroupController.addStudentToGroup(joinGroupInvitation.getReceiveStudentId(),joinGroupInvitation.getGroupId());
        return "接受邀请";
    }




}
