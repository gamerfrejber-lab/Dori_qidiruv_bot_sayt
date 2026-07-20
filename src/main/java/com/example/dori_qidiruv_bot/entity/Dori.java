package com.example.dori_qidiruv_bot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dori {
    private Long id;
    private String name;
    private String nameRu;
    private String manufacturer;
    private Double price;
    private Boolean available;
    private Long dorixonaId;
}
