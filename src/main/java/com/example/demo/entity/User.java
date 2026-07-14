package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id // marks the primary key field
    @GeneratedValue(strategy = GenerationType.IDENTITY) // let the database auto-increment this for us
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}