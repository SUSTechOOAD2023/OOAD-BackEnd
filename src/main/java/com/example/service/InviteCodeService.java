package com.example.service;

import com.example.entity.InviteCode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-11-24
 */
public interface InviteCodeService extends IService<InviteCode> {

    void generateAndSaveInviteCodes(int count);

    boolean isInviteCodeExist(String inviteCode);

    boolean isUsed(String inviteCode);

    boolean isCorrect(String inviteCode, String identity);

    InviteCode findID(String inviteCode);

    List<InviteCode> getInviteCode(int k, String identity);
}
