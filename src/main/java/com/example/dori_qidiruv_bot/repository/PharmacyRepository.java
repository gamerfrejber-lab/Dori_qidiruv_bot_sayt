package com.example.dori_qidiruv_bot.repository;

import com.example.dori_qidiruv_bot.model.Dorixona;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PharmacyRepository {

    private final List<Dorixona> list = new ArrayList<>();

    public PharmacyRepository() {
        list.add(new Dorixona("Yunusobod Apteka", "Yunusobod 4-kvartal", "+998901112233", 41.3451, 69.2861));
        list.add(new Dorixona("Chilonzor Shifo", "Chilonzor 19-mavze", "+998901112234", 41.2622, 69.2051));
        list.add(new Dorixona("Sergeli Farm", "Sergeli 7-mavze", "+998901112235", 41.2550, 69.2210));
        list.add(new Dorixona("Mirzo Ulug'bek Dori", "Buyuk Ipak Yo'li ko'chasi", "+998901112236", 41.3264, 69.3342));
        list.add(new Dorixona("Olmazor Med", "Qorasaroy ko'chasi", "+998901112237", 41.3470, 69.2393));
        list.add(new Dorixona("Shayxontohur Apteka", "Navoiy shoh ko'chasi", "+998901112238", 41.3196, 69.2448));
        list.add(new Dorixona("Yakkasaroy Farm", "Shota Rustaveli ko'chasi", "+998901112239", 41.2856, 69.2535));
        list.add(new Dorixona("Mirobod Dori", "Fidokor ko'chasi", "+998901112240", 41.2968, 69.2836));
        list.add(new Dorixona("Bektemir Apteka", "Bektemir markazi", "+998901112241", 41.2096, 69.3341));
        list.add(new Dorixona("Uchtepa Shifo", "Uchtepa, Lutfiy ko'chasi", "+998901112242", 41.2921, 69.1810));
        list.add(new Dorixona("Yashnobod Farm", "Aviasozlar mavzesi", "+998901112243", 41.2929, 69.3430));
        list.add(new Dorixona("Mirobod 24/7", "Oybek metrosi yonida", "+998901112244", 41.2995, 69.2728));
        list.add(new Dorixona("Chorsu Dori", "Chorsu bozori atrofi", "+998901112245", 41.3265, 69.2352));
        list.add(new Dorixona("Tinchlik Apteka", "Tinchlik metrosi yonida", "+998901112246", 41.3341, 69.2161));
        list.add(new Dorixona("Novza Farm", "Novza metrosi yonida", "+998901112247", 41.2914, 69.2245));
        list.add(new Dorixona("Qo'yliq Shifo", "Qo'yliq bozori atrofi", "+998901112248", 41.2420, 69.3301));
        list.add(new Dorixona("Bodomzor Apteka", "Bodomzor metrosi yonida", "+998901112249", 41.3374, 69.2865));
        list.add(new Dorixona("Minor Dori", "Minor metrosi yonida", "+998901112250", 41.3278, 69.2828));
        list.add(new Dorixona("Parkent Farm", "Parkent ko'chasi", "+998901112251", 41.3218, 69.3262));
        list.add(new Dorixona("Qatortol Apteka", "Qatortol ko'chasi", "+998901112252", 41.2813, 69.2058));
    }

    public List<Dorixona> getAll() { return list; }
}