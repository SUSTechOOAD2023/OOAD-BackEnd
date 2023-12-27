package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.JoinGroupRequest;
import com.example.mapper.JoinGroupRequestMapper;
import com.example.service.JoinGroupRequestService;
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
public class JoinGroupRequestServiceImpl extends ServiceImpl<JoinGroupRequestMapper, JoinGroupRequest> implements JoinGroupRequestService {
    @Autowired
    JoinGroupRequestMapper mapper;
    @Override
    public List<JoinGroupRequest> selectList(JoinGroupRequest joinGroupRequest){
        QueryWrapper<JoinGroupRequest> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(joinGroupRequest.getJoinGroupRequestId()!=null,JoinGroupRequest::getJoinGroupRequestId,joinGroupRequest.getJoinGroupRequestId())
                .eq(joinGroupRequest.getStudentId()!=null,JoinGroupRequest::getStudentId,joinGroupRequest.getStudentId())
                .eq(joinGroupRequest.getGroupId()!=null,JoinGroupRequest::getGroupId,joinGroupRequest.getGroupId())
                .eq(joinGroupRequest.getRequestContent()!=null,JoinGroupRequest::getRequestContent,joinGroupRequest.getRequestContent());
        ;
        return mapper.selectList(queryWrapper);
    }
    @Override
    public int delete(JoinGroupRequest joinGroupRequest){
        QueryWrapper<JoinGroupRequest> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(joinGroupRequest.getJoinGroupRequestId()!=null,JoinGroupRequest::getJoinGroupRequestId,joinGroupRequest.getJoinGroupRequestId())
                .eq(joinGroupRequest.getStudentId()!=null,JoinGroupRequest::getStudentId,joinGroupRequest.getStudentId())
                .eq(joinGroupRequest.getGroupId()!=null,JoinGroupRequest::getGroupId,joinGroupRequest.getGroupId())
                .eq(joinGroupRequest.getRequestContent()!=null,JoinGroupRequest::getRequestContent,joinGroupRequest.getRequestContent());
        return mapper.delete(queryWrapper);
    }
}
