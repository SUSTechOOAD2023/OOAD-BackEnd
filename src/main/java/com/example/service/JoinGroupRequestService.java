package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.JoinGroupRequest;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-10-24
 */
public interface JoinGroupRequestService extends IService<JoinGroupRequest> {
    List<JoinGroupRequest> selectList(JoinGroupRequest joinGroupRequest);
    int delete(JoinGroupRequest joinGroupRequest);
}
