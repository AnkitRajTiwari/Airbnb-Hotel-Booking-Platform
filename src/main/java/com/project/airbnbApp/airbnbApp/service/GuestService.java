package com.project.airbnbApp.airbnbApp.service;

import com.project.airbnbApp.airbnbApp.dto.GuestDto;

import java.util.List;

public interface GuestService {
    List<GuestDto> getAllGuests();

    void updateGuest(Long guestId, GuestDto guestDto);

    void deleteGuest(Long guestId);

    GuestDto addNewGuest(GuestDto guestDto);

}
