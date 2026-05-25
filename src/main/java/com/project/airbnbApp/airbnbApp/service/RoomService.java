package com.project.airbnbApp.airbnbApp.service;

import com.project.airbnbApp.airbnbApp.dto.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto createNewRoom(Long hotelId, RoomDto roomDto);

    List<RoomDto> getAllRoomsInHotel(Long hotelId);

    RoomDto getRoomById(Long hotelId, Long roomId);

    void deleteRoomById(Long hotelId, Long roomId);

    RoomDto updateRoomById(Long hotelId, Long roomId, RoomDto roomDto);

}
