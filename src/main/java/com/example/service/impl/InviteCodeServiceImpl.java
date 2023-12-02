package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.InviteCode;
import com.example.mapper.InviteCodeMapper;
import com.example.service.InviteCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
    @Autowired InviteCodeMapper mapper;


    @Override
    public void generateAndSaveInviteCodes(int count) {
        for (int i = 0; i < count; i++) {
            InviteCode inviteCode = new InviteCode();
            // 生成邀请码逻辑，这里可以使用UUID或者其他算法
            String code = UUID.randomUUID().toString();
            inviteCode.setCode(code);
            // 分配身份，这里需要根据你的业务逻辑来分配
            List<String> identities = Arrays.asList("teacher", "student", "SA");
            Random random = new Random();
            String identity = identities.get(random.nextInt(identities.size()));
            inviteCode.setIdentity(identity);
            inviteCode.setIsUsed(0);
            // 保存到数据库
            mapper.insert(inviteCode);
        }
    }

    @Override
    public boolean isInviteCodeExist(String inviteCode){
        System.out.println(inviteCode);
        QueryWrapper<InviteCode> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("code",inviteCode);
        System.out.println(mapper.selectCount(queryWrapper));
        return mapper.selectCount(queryWrapper)>0;
    }

    @Override
    public boolean isUsed(String inviteCode){
        QueryWrapper<InviteCode> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("code",inviteCode);
        queryWrapper.eq("is_used",1);
        return mapper.selectCount(queryWrapper)>0;
    }

    @Override
    public boolean isCorrect(String inviteCode, String identity){
        QueryWrapper<InviteCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", inviteCode);
        queryWrapper.eq("identity", identity);
        return mapper.selectCount(queryWrapper) > 0;
    }

}
