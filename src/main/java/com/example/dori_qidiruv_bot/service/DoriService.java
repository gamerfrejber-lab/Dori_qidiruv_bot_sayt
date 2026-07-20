package com.example.dori_qidiruv_bot.service;

import com.example.dori_qidiruv_bot.dto.DoriQidiruvResponse;
import com.example.dori_qidiruv_bot.entity.Dori;
import com.example.dori_qidiruv_bot.entity.Dorixona;
import com.example.dori_qidiruv_bot.repository.DoriRepository;
import com.example.dori_qidiruv_bot.repository.DorixonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoriService {
    private final DoriRepository doriRepository;
    private final DorixonaRepository dorixonaRepository;

    public List<DoriQidiruvResponse> qidirish(String nomi) {
        List<Dori> dorilar = doriRepository.findByNameContainingIgnoreCase(nomi);
        
        return dorilar.stream()
                .map(dori -> {
                    Dorixona dorixona = dorixonaRepository.findById(dori.getDorixonaId())
                            .orElse(null);
                    return new DoriQidiruvResponse(
                            dori.getName(),
                            dori.getNameRu(),
                            dori.getManufacturer(),
                            dori.getPrice(),
                            dori.getAvailable(),
                            dorixona
                    );
                })
                .collect(Collectors.toList());
    }

    public Dori doriQoshish(Dori dori) {
        return doriRepository.save(dori);
    }

    public List<Dori> barchaDorilar() {
        return doriRepository.findAll();
    }

    public void doriOchirish(Long id) {
        doriRepository.deleteById(id);
    }
}
