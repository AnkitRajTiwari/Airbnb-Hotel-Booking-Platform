package com.project.airbnbApp.airbnbApp.service;

import com.project.airbnbApp.airbnbApp.dto.BookingDto;
import com.project.airbnbApp.airbnbApp.dto.BookingRequest;
import com.project.airbnbApp.airbnbApp.dto.GuestDto;
import com.project.airbnbApp.airbnbApp.dto.HotelReportDto;
import com.project.airbnbApp.airbnbApp.entity.enums.BookingStatus;
import com.stripe.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<Long> guestIdList);

    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    BookingStatus getBookingStatus(Long bookingId);

    List<BookingDto> getAllBookingsByHotelId(Long hotelId);

    HotelReportDto getHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate);

    List<BookingDto> getMyBookings();

}