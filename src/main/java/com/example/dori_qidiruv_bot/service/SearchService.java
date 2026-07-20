package com.example.dori_qidiruv_bot.service;

import com.example.dori_qidiruv_bot.model.Dori;
import com.example.dori_qidiruv_bot.model.Dorixona;
import com.example.dori_qidiruv_bot.repository.DoriRepository;
import com.example.dori_qidiruv_bot.repository.DorixonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final DoriRepository doriRepository;
    private final DorixonaRepository dorixonaRepository;

    public List<Dori> search(String query) {
        if (query == null || query.isBlank()) return List.of();
        String q = query.toLowerCase(Locale.ROOT).trim();
        return doriRepository.findAll().stream()
                .filter(d -> (d.getName() != null && d.getName().toLowerCase(Locale.ROOT).contains(q))
                        || (d.getNameRu() != null && d.getNameRu().toLowerCase(Locale.ROOT).contains(q)))
                .map(entityDori -> {
                    Dorixona dorixona = dorixonaRepository.findById(entityDori.getDorixonaId())
                            .map(entityDorixona -> new Dorixona(
                                    entityDorixona.getName(),
                                    entityDorixona.getAddress(),
                                    entityDorixona.getTelefon(),
                                    entityDorixona.getLatitude(),
                                    entityDorixona.getLongitude()
                            ))
                            .orElse(null);

                    return new Dori(
                            entityDori.getName(),
                            entityDori.getNameRu(),
                            entityDori.getManufacturer(),
                            entityDori.getPrice(),
                            entityDori.getAvailable(),
                            dorixona
                    );
                })
                .collect(Collectors.toList());
    }
}