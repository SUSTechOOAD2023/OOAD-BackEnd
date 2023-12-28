package com.example.service;

import com.example.entity.VerifyCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-12-27
 */
public interface VerifyCodeService extends IService<VerifyCode> {

    boolean isVerifyCodeExist(String email, String identity, String verifyCode);

    boolean deleteVerifyCode(String email, String identity, String verifyCode);
}
