package com.example.service.impl;

import com.example.entity.Account;
import com.example.mapper.AccountMapper;
import com.example.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Autowired
    AccountMapper mapper;
//    @Override
//    public
    public List<Account> selectList(){
        return new ArrayList<Account>();
    }
    public boolean isAccountExist(String accountName){
        return true;
    }

    public boolean isCorrect(String accountName, String password){
        return true;
    }
}
