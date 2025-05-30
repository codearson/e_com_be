package com.e_com.Domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Title: ProductImage.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 28, 2025
 * @time 7:32:43 PM
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "ProductImage")
public class ProductImage implements Serializable {
   
	private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "url")
    private String url;
    @ManyToOne(optional = false)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;
    @Column(name = "isActive")
    private Boolean isActive;

}