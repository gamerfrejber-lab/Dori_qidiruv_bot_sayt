package com.example.dori_qidiruv_bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class SmsService {

    private final Random random = new Random();
    private final RestClient restClient = RestClient.create();

    @Value("${eskiz.base-url}")
    private String baseUrl;

    @Value("${eskiz.email:}")
    private String email;

    @Value("${eskiz.password:}")
    private String password;

    @Value("${eskiz.from:4546}")
    private String from;

    @Value("${telegram.notify.url:}")
    private String telegramNotifyUrl;

    @Value("${telegram.notify.token:}")
    private String telegramNotifyToken;

    private volatile String cachedToken;
    private volatile Instant tokenExpiresAt = Instant.EPOCH;

    /**
     * SMS yuborish. Avval Telegram bot orqali yuborishga urinadi (agar foydalanuvchi botga
     * /start bosgan bo'lsa). Ishlamasa Eskiz.uz'ga o'tadi; Eskiz ham sozlanmagan bo'lsa
     * (email/password bo'sh) test rejimida ishlaydi va kodni faqat logga yozadi.
     */
    public void sendVerificationCode(String phoneNumber, String code) {
        if (sendViaTelegram(phoneNumber, code)) {
            log.info("Kod Telegram bot orqali yuborildi: {}", phoneNumber);
            return;
        }

        if (email.isBlank() || password.isBlank()) {
            log.info("SMS yuborildi (test rejimi): {} raqamiga kod: {}", phoneNumber, code);
            return;
        }

        String message = "Dori Qidiruv tasdiqlash kodi: " + code;
        try {
            sendViaEskiz(phoneNumber, message);
            log.info("SMS Eskiz orqali yuborildi: {}", phoneNumber);
        } catch (Exception e) {
            log.error("Eskiz orqali SMS yuborib bo'lmadi ({}). Kod: {}", phoneNumber, code, e);
        }
    }

    /**
     * Telegram bot orqali yuborishga urinadi. Foydalanuvchi botga /start bosmagan bo'lsa
     * yoki bot ishlamayotgan bo'lsa, jim qolib false qaytaradi (keyingi kanal sinaladi).
     */
    private boolean sendViaTelegram(String phoneNumber, String code) {
        if (telegramNotifyUrl.isBlank()) return false;
        try {
            String url = telegramNotifyUrl
                    + "?phone=" + URLEncoder.encode(phoneNumber, StandardCharsets.UTF_8)
                    + "&code=" + URLEncoder.encode(code, StandardCharsets.UTF_8)
                    + "&token=" + URLEncoder.encode(telegramNotifyToken, StandardCharsets.UTF_8);
            restClient.post().uri(url).retrieve().toBodilessEntity();
            return true;
        } catch (Exception e) {
            log.warn("Telegram bot orqali yuborib bo'lmadi ({}): {}", phoneNumber, e.getMessage());
            return false;
        }
    }

    private void sendViaEskiz(String phoneNumber, String message) {
        String digitsOnly = phoneNumber.replaceAll("[^0-9]", "");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("mobile_phone", digitsOnly);
        body.add("message", message);
        body.add("from", from);

        restClient.post()
                .uri(baseUrl + "/message/sms/send")
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body)
                .retrieve()
                .toBodilessEntity();
    }

    private synchronized String getToken() {
        if (cachedToken != null && Instant.now().isBefore(tokenExpiresAt)) {
            return cachedToken;
        }

        MultiValueMap<String, String> loginBody = new LinkedMultiValueMap<>();
        loginBody.add("email", email);
        loginBody.add("password", password);

        Map<String, Object> response = restClient.post()
                .uri(baseUrl + "/auth/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(loginBody)
                .retrieve()
                .body(Map.class);

        @SuppressWarnings("unchecked")
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        cachedToken = (String) data.get("token");
        // Eskiz tokeni ~30 kun amal qiladi, 29 kundan keyin qayta login qilamiz
        tokenExpiresAt = Instant.now().plusSeconds(29L * 24 * 60 * 60);
        return cachedToken;
    }

    /**
     * 6 xonali tasodifiy kod generatsiya qilish
     */
    public String generateVerificationCode() {
        return String.format("%06d", random.nextInt(1000000));
    }
}
