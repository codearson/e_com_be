package com.e_com.Domain;

import java.io.Serializable;

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
 * Title: ShippingAddress.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 14 May 2025
 * @time 23:25:18
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "shippingAddress")
public class ShippingAddress implements Serializable {
    
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "mobileNumber")
    private Integer mobileNumber;
    @JoinColumn(name = "user", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;
    @Column(name = "isActive")
    private Boolean isActive;

}
