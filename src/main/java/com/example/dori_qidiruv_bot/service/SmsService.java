package com.example.dori_qidiruv_bot.service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Service
@Slf4j
public class SmsService {
    
    private final Random random = new Random();

    /**
     * SMS yuborish (Test rejimida faqat log qiladi)
     * Real production uchun Eskiz.uz, Playmobile yoki boshqa SMS provider integratsiya qilish kerak
     */
    public void sendVerificationCode(String phoneNumber, String code) {
        // Test rejimida faqat log qilamiz
        log.info("SMS yuborildi: {} raqamiga kod: {}", phoneNumber, code);
        
        // PRODUCTION UCHUN:
        // Eskiz.uz API: https://documenter.getpostman.com/view/663428/RzfmES4z
        // Playmobile API: https://www.playmobile.uz/api
        // RestTemplate yoki WebClient ishlatib SMS yuborish kerak
    }

    /**
     * 6 xonali tasodifiy kod generatsiya qilish
     */
    public String generateVerificationCode() {
        return String.format("%06d", random.nextInt(1000000));
    }
}
