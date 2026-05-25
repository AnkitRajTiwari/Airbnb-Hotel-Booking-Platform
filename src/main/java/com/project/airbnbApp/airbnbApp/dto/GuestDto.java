package com.project.airbnbApp.airbnbApp.dto;

import com.project.airbnbApp.airbnbApp.entity.User;
import com.project.airbnbApp.airbnbApp.entity.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GuestDto {
    private Long id;
    private String name;
    private Gender gender;
    private LocalDate dateOfBirth;
}
