package com.project.airbnbApp.airbnbApp.entity;

import com.project.airbnbApp.airbnbApp.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;          // Many Guest records belong to ONE User eg. Me User booking for family - Guests

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dateOfBirth;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;
}
