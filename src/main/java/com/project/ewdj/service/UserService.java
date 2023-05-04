package com.project.ewdj.service;

import java.util.List;

import com.project.ewdj.dto.UserDto;
import com.project.ewdj.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    public List<User> findAllUsers();
}