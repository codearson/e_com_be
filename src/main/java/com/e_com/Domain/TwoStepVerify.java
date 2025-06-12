package com.e_com.Domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data; // Using Lombok for boilerplate reduction
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Title: TwoFaToken.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 10 Jun 2025
 * @time 10:17:00 PM
 * @version 1.0
 **/

@Entity
@Table(name = "two_Step_verification")
@Data
public class TwoStepVerify {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiryTokenTime;
    
    
    
}