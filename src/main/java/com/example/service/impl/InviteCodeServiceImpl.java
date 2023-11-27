package com.example.service.impl;

import com.example.entity.InviteCode;
import com.example.mapper.InviteCodeMapper;
import com.example.service.InviteCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sending
 * @since 2023-11-24
 */
@Service
public class InviteCodeServiceImpl extends ServiceImpl<InviteCodeMapper, InviteCode> implements InviteCodeService {
    @Autowired InviteCodeMapper inviteCodeMapper;


    @Override
    public void generateAndSaveInviteCodes(int count) {
        for (int i = 0; i < count; i++) {
            InviteCode inviteCode = new InviteCode();
            // 生成邀请码逻辑，这里可以使用UUID或者其他算法
            String code = UUID.randomUUID().toString();
            inviteCode.setCode(code);
            // 分配身份，这里需要根据你的业务逻辑来分配

            inviteCode.setIdentity("User"); // 示例身份
            // 保存到数据库
            inviteCodeMapper.insert(inviteCode);
        }
    }

}
