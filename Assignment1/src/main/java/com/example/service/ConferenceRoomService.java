package com.example.service;

import com.example.entity.ConferenceRoom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sending
 * @since 2023-10-14
 */
public interface ConferenceRoomService extends IService<ConferenceRoom> {

    List<ConferenceRoom> selectList(ConferenceRoom Room);


    int deleteRoom(ConferenceRoom Room);

//    void generateData();
}
