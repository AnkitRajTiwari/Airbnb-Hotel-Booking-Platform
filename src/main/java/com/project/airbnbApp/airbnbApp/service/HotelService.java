package com.project.airbnbApp.airbnbApp.service;

import com.project.airbnbApp.airbnbApp.dto.HotelDto;
import com.project.airbnbApp.airbnbApp.dto.HotelInfoDto;
import com.project.airbnbApp.airbnbApp.dto.HotelInfoRequestDto;

import java.util.List;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(Long id);

    void activateHotel(Long hotelId);

    HotelInfoDto getHotelInfoById(Long hotelId, HotelInfoRequestDto hotelInfoRequestDto);

    List<HotelDto> getAllHotels();
}
