package com.example.service;

import com.example.entity.Homework;
import com.example.entity.Notice;
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
public interface NoticeService extends IService<Notice> {
    List<Notice> selectList(Notice notice);
    int delete(Notice notice);
}
