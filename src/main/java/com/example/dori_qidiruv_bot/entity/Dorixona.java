package com.example.dori_qidiruv_bot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dorixona {
    private Long id;
    private String name;
    private String address;
    private String telefon;
    private Double latitude;
    private Double longitude;
}
