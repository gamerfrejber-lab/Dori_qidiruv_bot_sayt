package com.example.dori_qidiruv_bot.dto;

import lombok.Data;

@Data
public class VerifyRequest {
    private String phoneNumber;
    private String code;
}
