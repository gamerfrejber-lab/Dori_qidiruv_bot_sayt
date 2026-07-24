package com.example.dori_qidiruv_bot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dori")
public class Dori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomi")
    private String name;

    @Column(name = "nomi_ru")
    private String nameRu;

    @Column(name = "ishlab_chiqaruvchi")
    private String manufacturer;

    @Column(name = "narx")
    private Double price;

    @Column(name = "mavjud")
    private Boolean available;

    @Column(name = "dorixona_id")
    private Long dorixonaId;
}
