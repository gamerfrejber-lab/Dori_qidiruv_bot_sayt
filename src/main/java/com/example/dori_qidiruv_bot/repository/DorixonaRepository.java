package com.example.dori_qidiruv_bot.repository;

import com.example.dori_qidiruv_bot.entity.Dorixona;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class DorixonaRepository {
    private final Map<Long, Dorixona> dorixonalar = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public DorixonaRepository() {
        // Test ma'lumotlari
        save(new Dorixona(null, "36.6 Dorixonasi", "Amir Temur ko'chasi 107", "+998712002000", 41.311158, 69.279737));
        save(new Dorixona(null, "Farmiya Dorixonasi", "Bobur ko'chasi 34", "+998712003000", 41.295469, 69.279873));
        save(new Dorixona(null, "Soglik Dorixonasi", "Nukus ko'chasi 52", "+998712004000", 41.314876, 69.278394));
    }

    public Dorixona save(Dorixona dorixona) {
        if (dorixona.getId() == null) {
            dorixona.setId(idGenerator.getAndIncrement());
        }
        dorixonalar.put(dorixona.getId(), dorixona);
        return dorixona;
    }

    public List<Dorixona> findAll() {
        return new ArrayList<>(dorixonalar.values());
    }

    public Optional<Dorixona> findById(Long id) {
        return Optional.ofNullable(dorixonalar.get(id));
    }

    public void deleteById(Long id) {
        dorixonalar.remove(id);
    }
}
