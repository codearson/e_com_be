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
 * Title: Orders.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 21:58:31
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "orders")
public class Orders implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
	@JoinColumn(name = "product", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product product;
	@JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;
	@JoinColumn(name = "postagePartner", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PostagePartner postagePartner;
	@JoinColumn(name = "shippingAddress", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ShippingAddress shippingAddress;
	@JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
	@Column(name = "createdAt")
    private LocalDateTime createdAt;
	@Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "estimateDeliveryDate")
    private LocalDateTime estimateDeliveryDate;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "isActive")
    private Boolean isActive;
    
}
