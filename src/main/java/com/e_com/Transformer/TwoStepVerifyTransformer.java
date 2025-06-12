package com.e_com.Transformer;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import com.e_com.Domain.TwoStepVerify;
import com.e_com.Domain.User;

/**
 * Title: TwoFaTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 9 Jun 2025
 * @time 11:12:36 pm
 * @version 1.0
 **/

public class TwoStepVerifyTransformer {

    /**
     * Generates a secure, 6-digit one-time password (OTP).
     *
     * @return A 6-digit numeric string.
     */
    public static String generateRandomToken() {
        SecureRandom random = new SecureRandom();
        // Generate a number between 100000 and 999999
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    
    public static TwoStepVerify toEntity(User user) {
    	TwoStepVerify verification = new TwoStepVerify();
        verification.setUser(user);
        verification.setToken(generateRandomToken());
        // Set a shorter expiry time suitable for 2FA, e.g., 10 minutes
        verification.setExpiryTokenTime(LocalDateTime.now().plusMinutes(10));
        return verification;
    }
    
}