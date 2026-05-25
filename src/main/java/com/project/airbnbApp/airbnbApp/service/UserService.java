package com.project.airbnbApp.airbnbApp.service;

import com.project.airbnbApp.airbnbApp.dto.ProfileUpdateRequestDto;
import com.project.airbnbApp.airbnbApp.dto.UserDto;
import com.project.airbnbApp.airbnbApp.entity.User;

public interface UserService {
    User getUserById(long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
