package com.example.dori_qidiruv_bot.repository;

import com.example.dori_qidiruv_bot.entity.Dori;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class DoriRepository {
    private final Map<Long, Dori> dorilar = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public DoriRepository() {
        // Test ma'lumotlari
        save(new Dori(null, "Paratsetamol", "Парацетамол", "Фармстандарт", 5000.0, true, 1L));
        save(new Dori(null, "Aspirin", "Аспирин", "Байер", 8000.0, true, 1L));
        save(new Dori(null, "Ibuprofen", "Ибупрофен", "Tatximfarm", 12000.0, true, 2L));
        save(new Dori(null, "Amoksitsillin", "Амоксициллин", "Юрия-Фарм", 15000.0, false, 2L));
        save(new Dori(null, "Vitamin C", "Витамин С", "Фармстандарт", 7000.0, true, 3L));
    }

    public Dori save(Dori dori) {
        if (dori.getId() == null) {
            dori.setId(idGenerator.getAndIncrement());
        }
        dorilar.put(dori.getId(), dori);
        return dori;
    }

    public List<Dori> findAll() {
        return new ArrayList<>(dorilar.values());
    }

    public Optional<Dori> findById(Long id) {
        return Optional.ofNullable(dorilar.get(id));
    }

    public List<Dori> findByNameContainingIgnoreCase(String name) {
        return dorilar.values().stream()
                .filter(d -> d.getName().toLowerCase().contains(name.toLowerCase()) ||
                        d.getNameRu().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        dorilar.remove(id);
    }
}
