package com.example.service;

import com.example.entity.InviteCode;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
