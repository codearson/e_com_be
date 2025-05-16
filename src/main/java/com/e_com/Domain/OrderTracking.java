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
 * Title: OrderTracking.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 16 May 2025
 * @time 11:33:14 pm
 * @version 1.0
 **/
@Data
@Entity
@Table(name = "orderTracking")
public class OrderTracking implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@JoinColumn(name = "postagePartnerId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private PostagePartner postagePartner;
	@JoinColumn(name = "region", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Branch branch;
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;
	@JoinColumn(name = "orderId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Orders orders;
	@Column(name = "trackingId",nullable = false)
	private String trackingId;
	@Column(name = "isActive")
	private Boolean isActive;
	
}  
