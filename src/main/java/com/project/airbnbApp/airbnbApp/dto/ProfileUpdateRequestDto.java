package com.project.airbnbApp.airbnbApp.dto;

import com.project.airbnbApp.airbnbApp.entity.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileUpdateRequestDto {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
}
