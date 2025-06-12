package com.e_com.Dao;

import java.util.Optional;

import com.e_com.Domain.EmailVerificationToken;
import com.e_com.Domain.PasswordResetToken;
import com.e_com.Domain.TwoStepVerify;
import com.e_com.Domain.TwoStepVerify;
import com.e_com.Domain.User;
/**
 * Title: TwoFaTokenDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 9 Jun 2025
 * @time 11:11:43 pm
 * @version 1.0
 **/

public interface TwoStepVerifyDao {
	
	void save(TwoStepVerify token);
	
	TwoStepVerify findByToken(String token);
    
	void delete(int userId);

}