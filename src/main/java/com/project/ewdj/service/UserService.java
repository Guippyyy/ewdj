package com.project.ewdj.service;

import com.project.ewdj.dto.UserDto;
import com.project.ewdj.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
}