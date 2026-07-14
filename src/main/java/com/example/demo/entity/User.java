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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // unique = true means the database itself will reject a duplicate
    // username at the DB level — this is our safety net UNDER the
    // application-level duplicate check we do in the service layer.
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    // NOTE for later: in a real production app you would NEVER store a
    // plain text password like this. You'd hash it with something like
    // BCryptPasswordEncoder before saving, and never return it in any
    // response. For this homework we keep it simple, but this is exactly
    // the kind of thing Spring Security exists to solve.
    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", length = 100)
    private String fullName;

    // set once, when the row is first created — see @PrePersist below
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // lets us "soft delete" / disable a user without actually removing
    // the row — that's what our PATCH endpoint will flip
    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;

    // @PrePersist runs automatically, right before Hibernate INSERTs this
    // row for the first time — this is how we stamp createdAt without
    // having to remember to set it manually every time we build a User.
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}