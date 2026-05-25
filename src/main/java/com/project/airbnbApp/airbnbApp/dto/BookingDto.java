package com.project.airbnbApp.airbnbApp.dto;

import com.project.airbnbApp.airbnbApp.entity.enums.BookingStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private Integer roomsCount;
    private LocalDate CheckInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guest;
    private BigDecimal amount;
}
