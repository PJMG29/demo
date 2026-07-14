package com.example.demo.service;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto request);
    UserResponseDto getUserById(Long id);
    List<UserResponseDto> getAllUsers();
    List<UserResponseDto> searchByUsername(String keyword);
    UserResponseDto updateUser(Long id, UserRequestDto request);
    void deleteUser(Long id);
    UserResponseDto setActiveStatus(Long id, boolean active);
}