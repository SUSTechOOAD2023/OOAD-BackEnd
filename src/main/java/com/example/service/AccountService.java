package com.example.service;

import com.example.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.User;

import java.util.List;

import java.util.List;
import java.util.Map;

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


    List<Account> selectIdentity(String identity);

//    boolean isAccountExist(String accountName);

//    boolean isEmailExist(String email);

    boolean isAccountExist(String identity, String accountName);

    Account selectAccount(String identity, String accountName);

    boolean isEmailExist(String identity, String email);

    Account selectEmailAccount(String identity, String email);

    boolean isCorrect(String accountName, String password);

    boolean emailIsCorrect(String email, String password);

    Account selectAccount(int accountID);
}
