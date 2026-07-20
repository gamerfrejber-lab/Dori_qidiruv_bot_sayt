package com.example.dori_qidiruv_bot.service;

import com.example.dori_qidiruv_bot.entity.User;
import com.example.dori_qidiruv_bot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final SmsService smsService;
    private final JwtService jwtService;

    /**
     * Telefon raqam orqali SMS kod yuborish
     */
    public String sendVerificationCode(String phoneNumber) {
        // Telefon raqamni tozalash (+998 prefixi bilan)
        phoneNumber = cleanPhoneNumber(phoneNumber);
        
        // Kod generatsiya qilish
        String code = smsService.generateVerificationCode();
        
        // User topish yoki yangi yaratish
        Optional<User> existingUser = userRepository.findByPhoneNumber(phoneNumber);
        User user;
        
        if (existingUser.isPresent()) {
            user = existingUser.get();
        } else {
            user = new User();
            user.setPhoneNumber(phoneNumber);
            user.setIsVerified(false);
        }
        
        // Kodni saqlash (5 daqiqa amal qiladi)
        user.setVerificationCode(code);
        user.setCodeExpiry(LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);
        
        // SMS yuborish
        smsService.sendVerificationCode(phoneNumber, code);
        
        return "Kod yuborildi";
    }

    /**
     * Kodni tekshirish va JWT token qaytarish
     */
    public String verifyCode(String phoneNumber, String code) {
        phoneNumber = cleanPhoneNumber(phoneNumber);
        
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User topilmadi"));
        
        // Kod tekshirish
        if (!code.equals(user.getVerificationCode())) {
            throw new RuntimeException("Noto'g'ri kod");
        }
        
        // Kod muddati tekshirish
        if (user.getCodeExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Kod muddati tugagan");
        }
        
        // Userni tasdiqlash
        user.setIsVerified(true);
        user.setLastLoginAt(LocalDateTime.now());
        user.setVerificationCode(null);
        user.setCodeExpiry(null);
        userRepository.save(user);
        
        // JWT token yaratish
        return jwtService.generateToken(user);
    }

    /**
     * Telefon raqamni tozalash
     */
    private String cleanPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[^0-9+]", "");
        if (!phoneNumber.startsWith("+")) {
            phoneNumber = "+998" + phoneNumber;
        }
        return phoneNumber;
    }

    /**
     * Token orqali userni topish
     */
    public User getUserByToken(String token) {
        Long userId = jwtService.getUserIdFromToken(token);
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User topilmadi"));
    }
}
