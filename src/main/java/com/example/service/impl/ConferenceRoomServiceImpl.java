package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.ConferenceRoom;
import com.example.mapper.ConferenceRoomMapper;
import com.example.service.ConferenceRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sending
 * @since 2023-10-14
 */
@Service
public class ConferenceRoomServiceImpl extends ServiceImpl<ConferenceRoomMapper, ConferenceRoom> implements ConferenceRoomService {
  @Autowired
  ConferenceRoomMapper mapper;
    @Override
    public List<ConferenceRoom> selectList(ConferenceRoom Room){
        QueryWrapper<ConferenceRoom> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Room.getRoomId()!=null,ConferenceRoom::getRoomId,Room.getRoomId())
                .eq(Room.getDepartment()!=null,ConferenceRoom::getDepartment,Room.getDepartment())
                .eq(Room.getRoomName()!=null,ConferenceRoom::getRoomName,Room.getRoomName())
                .eq(Room.getType()!=null,ConferenceRoom::getType,Room.getType())
                .eq(Room.getLocation()!=null,ConferenceRoom::getLocation,Room.getLocation())
                .eq(Room.getDate()!=null,ConferenceRoom::getDate,Room.getDate())
                .eq(Room.getStartTime()!=null,ConferenceRoom::getStartTime,Room.getStartTime())
                .eq(Room.getEndTime()!=null,ConferenceRoom::getEndTime,Room.getEndTime())
                .eq(Room.getMaxDuration()!=null,ConferenceRoom::getMaxDuration,Room.getMaxDuration());
        return mapper.selectList(queryWrapper);
    }

    @Override
    public int deleteRoom(ConferenceRoom Room){
        QueryWrapper<ConferenceRoom> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Room.getRoomId()!=null,ConferenceRoom::getRoomId,Room.getRoomId())
                .eq(Room.getDepartment()!=null,ConferenceRoom::getDepartment,Room.getDepartment())
                .eq(Room.getRoomName()!=null,ConferenceRoom::getRoomName,Room.getRoomName())
                .eq(Room.getType()!=null,ConferenceRoom::getType,Room.getType())
                .eq(Room.getLocation()!=null,ConferenceRoom::getLocation,Room.getLocation())
                .eq(Room.getDate()!=null,ConferenceRoom::getDate,Room.getDate())
                .eq(Room.getStartTime()!=null,ConferenceRoom::getStartTime,Room.getStartTime())
                .eq(Room.getEndTime()!=null,ConferenceRoom::getEndTime,Room.getEndTime())
                .eq(Room.getMaxDuration()!=null,ConferenceRoom::getMaxDuration,Room.getMaxDuration());
        return mapper.delete(queryWrapper);
    }

//@Override
//    public void generateData() {
//        for (int i = 0; i < 10; i++) {
//            ConferenceRoom room = new ConferenceRoom();
//            // 设置数据字段的值
//            room.setRoomId(i+1);
//            room.setRoomName("Room " + i);
//            room.setDepartment("Department " + i);
//            room.setType("Type " + i);
//            room.setLocation("Location " + i);
//            room.setDate(LocalDate.now());
//            room.setStartTime(LocalDateTime.now());
//            room.setEndTime(LocalDateTime.now().plusHours(1));
//            room.setMaxduration("Duration " + i);
//
//            mapper.insert(room);
//        }
//    }


}
