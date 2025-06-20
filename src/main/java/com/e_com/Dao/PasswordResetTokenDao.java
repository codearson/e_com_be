package com.e_com.Dao;

import com.e_com.Domain.PasswordResetToken;
import com.e_com.Domain.EmailVerificationToken;


/**
 * Title: PasswordResetTokenDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 19, 2025
 * @version 1.0
 **/

public interface PasswordResetTokenDao {

	void save(PasswordResetToken token);
	
    PasswordResetToken findByToken(String token);
    
    void delete(PasswordResetToken token);
    
    void save(EmailVerificationToken token);
    
    EmailVerificationToken findByEmailToken(String token);

}
