package com.e_com.Transformer;

import com.e_com.Domain.EmailVerificationToken;
import java.util.UUID;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import com.e_com.Domain.EmailVerificationToken;


public class EmailVerificationTokenTransformer {

    public static String generateRandomToken(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static EmailVerificationToken toEntity(String email) {
        EmailVerificationToken token = new EmailVerificationToken();
        token.setEmail(email);
        token.setToken(generateRandomToken(6));
        token.setExpiryTokenTime(LocalDateTime.now().plusHours(1));
        return token;
    }
}
