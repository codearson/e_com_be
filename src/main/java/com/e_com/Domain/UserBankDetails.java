package com.e_com.Domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Title: UserBankDetails.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 15, 2025
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "userBankDetails")
public class UserBankDetails implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "accountHolderName", nullable = false)
    private String accountHolderName;
    @Column(name = "accountNumber", nullable = false)
    private String accountNumber;
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;
    @JoinColumn(name = "userId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;
    @JoinColumn(name = "bankId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Bank bank;
    @JoinColumn(name = "branchId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Branch branch;
    @Column(name = "isActive")
    private Boolean isActive;
	
}
