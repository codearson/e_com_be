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
 * Title: ProductCategoryLevel4.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date Jun 25, 2025
 * @time 8:10:24 PM
 * @version 1.0
 **/

@Data
@Entity
@Table(name ="ProductCategoryLevel4")
public class ProductCategoryLevel4 implements  Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@JoinColumn(name = "parentId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private ProductCategoryLevel3 productCategoryLevel3;
	@Column(name = "isActive")
    private Boolean isActive;
}