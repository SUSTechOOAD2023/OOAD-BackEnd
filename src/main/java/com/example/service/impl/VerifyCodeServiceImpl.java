package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.VerifyCode;
import com.example.mapper.VerifyCodeMapper;
import com.example.service.VerifyCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sending
 * @since 2023-12-27
 */
@Service
public class VerifyCodeServiceImpl extends ServiceImpl<VerifyCodeMapper, VerifyCode> implements VerifyCodeService {
    @Autowired
    VerifyCodeMapper mapper;
    @Override
    public boolean isVerifyCodeExist(String email, String identity, String verifyCode) {
        QueryWrapper<VerifyCode> wrapper=new QueryWrapper<>();
        wrapper.eq("email",email);
        wrapper.eq("identity",identity);
        wrapper.eq("verify_code",verifyCode);
        return mapper.selectCount(wrapper)>0;
    }

    @Override
    public boolean deleteVerifyCode(String email, String identity, String verifyCode) {
        QueryWrapper<VerifyCode> wrapper=new QueryWrapper<>();
        wrapper.eq("email",email);
        wrapper.eq("identity",identity);
        wrapper.eq("verify_code",verifyCode);
        return mapper.delete(wrapper)>0;
    }

}
