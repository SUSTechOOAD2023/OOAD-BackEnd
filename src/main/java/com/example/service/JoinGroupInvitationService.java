package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.JoinGroupInvitation;
import org.springframework.web.bind.annotation.RequestParam;

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

    int addInvitation(@RequestParam int receiveStudentId, @RequestParam int sendStudentId, @RequestParam int groupId);

    //查询所有发起的邀请，然后返回被邀请人的id和姓名
    List<JoinGroupInvitation> searchSend(int sendStudentId);

    //查询所有接受的邀请，然后返回邀请人的id和姓名
    List<JoinGroupInvitation> searchReceive(int receiveStudentId);

    JoinGroupInvitation selectInvitation(int joinGroupInvitationId);

    //接受邀请
    String acceptInvitation(int joinGroupInvitationId);
}
