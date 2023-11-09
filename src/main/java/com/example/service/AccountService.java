package com.example.service;

import com.example.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
public interface AccountService extends IService<Account> {

    List<Account> selectList();

    boolean isAccountExist(String accountName);

    boolean isCorrect(String accountName, String password);
}
