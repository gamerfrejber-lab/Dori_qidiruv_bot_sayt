package com.example.dori_qidiruv_bot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dori {
    private String name;
    private String nameRu;
    private String manufacturer;
    private double price;
    private boolean available;
    private Dorixona dorixona;
}