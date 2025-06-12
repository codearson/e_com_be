package com.e_com.DaoImpl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.e_com.Dao.TwoStepVerifyDao; // Import the TwoStepVerifyDao interface
import com.e_com.Domain.EmailVerificationToken;
import com.e_com.Domain.PasswordResetToken;
import com.e_com.Domain.TwoStepVerify; // Import the TwoStepVerify domain object
import com.e_com.Domain.User;

/**
 * Title: TwoStepVerifyDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 11 Jun 2025
 * @time 12:28 AM
 * @version 1.0
 **/

@Repository
@Transactional
public class TwoStepVerifyDaoImpl implements TwoStepVerifyDao { // Implement the interface

	@PersistenceContext
    private EntityManager entityManager;

    public void save(TwoStepVerify token) {
        entityManager.persist(token);
    }

    public TwoStepVerify findByToken(String token) {
        return entityManager.createQuery("SELECT t FROM TwoStepVerify t WHERE t.token = :token", TwoStepVerify.class)
                .setParameter("token", token)
                .getSingleResult();
    }

   
    public void delete(int userId) {
        entityManager.createQuery("DELETE FROM TwoStepVerify t WHERE t.user.id = :userId")
            .setParameter("userId", userId)
            .executeUpdate();
    }


    
   

}
