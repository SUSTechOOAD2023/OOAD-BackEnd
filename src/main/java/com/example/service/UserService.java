package com.example.service;

import com.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-10-13
 */
public interface UserService extends IService<User> {


    List<User> selectList();

    boolean isUserNameExists(String userName);

    boolean isCorrect(String userName, String password);
}
