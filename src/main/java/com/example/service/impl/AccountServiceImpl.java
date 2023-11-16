package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.mapper.AccountMapper;
import com.example.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public List<Account> selectList(){
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        return mapper.selectList(queryWrapper);
    }

    @Override
    public boolean isAccountExist(String accountName){
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account_name",accountName);
        return mapper.selectCount(queryWrapper)>0;
    }

    @Override
    public boolean isCorrect(String accountName, String password){
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_name", accountName);
        queryWrapper.eq("user_password", password.hashCode()+"");
        return mapper.selectCount(queryWrapper) > 0;
    }

}