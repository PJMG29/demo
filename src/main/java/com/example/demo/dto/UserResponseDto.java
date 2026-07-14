package com.example.demo.dto;

import lombok.*;
import java.time.LocalDateTime;

// Notice: no "password" field here at all. This is the DTO that goes
// back out to the client, so the password simply doesn't exist in it —
// that's the cleanest way to guarantee it never leaks in a response.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private LocalDateTime createdAt;
    private Boolean active;
}