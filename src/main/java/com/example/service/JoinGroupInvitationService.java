package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.JoinGroupInvitation;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
public interface JoinGroupInvitationService extends IService<JoinGroupInvitation> {
    List<JoinGroupInvitation> selectList(JoinGroupInvitation joinGroupInvitation);
    int delete(JoinGroupInvitation joinGroupInvitation);
}
