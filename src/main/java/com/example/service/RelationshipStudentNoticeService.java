package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.RelationshipStudentNotice;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-12-28
 */
public interface RelationshipStudentNoticeService extends IService<RelationshipStudentNotice> {

    public List<Integer> listStudentId(int studentId);
    public List<Integer> listNoticeId(int noticeId);
    public boolean insert(int studentId, int noticeId);
    int delete(int studentId, int noticeId);
}
