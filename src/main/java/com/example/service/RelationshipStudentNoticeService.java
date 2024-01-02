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
    List<RelationshipStudentNotice> selectList(RelationshipStudentNotice relationshipStudentNotice);
    List<Integer> listStudentId(int studentId);
    List<Integer> listNoticeId(int noticeId);
    boolean insert(int studentId, int noticeId);
    int delete(int studentId, int noticeId);
    int deleteStudent(int studentId);
    int deleteNotice(int noticeId);
}
