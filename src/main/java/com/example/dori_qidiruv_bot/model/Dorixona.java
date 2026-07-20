package com.example.dori_qidiruv_bot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dorixona {
    private String name;
    private String address;
    private String telefon;
    private double latitude;
    private double longitude;
}