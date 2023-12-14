package com.example.service;

import com.example.entity.Group;
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
public interface GroupService extends IService<Group> {
    List<Group> selectList(Group group);
    int delete(Group group);
}
