package com.e_com.DaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.e_com.Dao.PasswordResetTokenDao;
import com.e_com.Domain.PasswordResetToken;
import com.e_com.Domain.EmailVerificationToken;

/**
 * Title: PasswordResetTokenDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 19, 2025
 * @version 1.0
 **/

@Repository
@Transactional
public class PasswordResetTokenDaoImpl implements PasswordResetTokenDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(PasswordResetToken token) {
        entityManager.persist(token);
    }

    public PasswordResetToken findByToken(String token) {
        return entityManager.createQuery("SELECT t FROM PasswordResetToken t WHERE t.token = :token", PasswordResetToken.class)
                .setParameter("token", token)
                .getSingleResult();
    }

    public void delete(PasswordResetToken token) {
        entityManager.remove(token);
    }
    
    public void save(EmailVerificationToken token) {
    	entityManager.persist(token); 
    }
    
    public EmailVerificationToken findByEmailToken(String token) {
        return entityManager.createQuery("SELECT t FROM EmailVerificationToken t WHERE t.token = :token", EmailVerificationToken.class)
                .setParameter("token", token)
                .getSingleResult();
    }
}
