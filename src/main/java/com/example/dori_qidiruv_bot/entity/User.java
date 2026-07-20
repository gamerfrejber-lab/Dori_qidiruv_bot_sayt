package com.example.dori_qidiruv_bot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String phoneNumber;
    private String name;
    private String verificationCode;
    private LocalDateTime codeExpiry;
    private Boolean isVerified;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;
}
