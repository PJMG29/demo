package com.example.demo.service;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto request) {

        userRepository.findByUsername(request.getUsername()).ifPresent(existing -> {
            throw new DuplicateResourceException(
                    "Username '" + request.getUsername() + "' is already taken");
        });
        userRepository.findByEmail(request.getEmail()).ifPresent(existing -> {
            throw new DuplicateResourceException(
                    "Email '" + request.getEmail() + "' is already registered");
        });

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // plain text for now — see note in entity
                .fullName(request.getFullName())
                .active(true)
                .build();

        User saved = userRepository.save(user);
        return toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        User user = findUserOrThrow(id);
        return toResponseDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> searchByUsername(String keyword) {
        return userRepository.findByUsernameContainingIgnoreCase(keyword).stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto request) {
        User user = findUserOrThrow(id);


        if (!user.getUsername().equals(request.getUsername())) {
            userRepository.findByUsername(request.getUsername()).ifPresent(existing -> {
                throw new DuplicateResourceException(
                        "Username '" + request.getUsername() + "' is already taken");
            });
        }
        if (!user.getEmail().equals(request.getEmail())) {
            userRepository.findByEmail(request.getEmail()).ifPresent(existing -> {
                throw new DuplicateResourceException(
                        "Email '" + request.getEmail() + "' is already registered");
            });
        }

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFullName(request.getFullName());

        User updated = userRepository.save(user);
        return toResponseDto(updated);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = findUserOrThrow(id);
        userRepository.delete(user);
    }


    @Override
    @Transactional
    public UserResponseDto setActiveStatus(Long id, boolean active) {
        User user = findUserOrThrow(id);
        user.setActive(active);
        User updated = userRepository.save(user);
        return toResponseDto(updated);
    }

    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    private UserResponseDto toResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .createdAt(user.getCreatedAt())
                .active(user.getActive())
                .build();
    }
}