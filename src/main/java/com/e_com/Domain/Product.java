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
 * Title: Product.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 18:32:55
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "productSubCategory", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductSubCategory productSubCategory;
    @JoinColumn(name = "brand", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Brand brand;
    @JoinColumn(name = "conditions", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Conditions conditions;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "size")
    private String size;
    @Column(name = "color")
    private String color;
    @Column(name = "price")
    private Double price;
    @Column(name = "quentity")
    private Integer quentity;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "imageUrl")
    private String imageUrl;
    @Column(name = "isActive")
    private Boolean isActive;
    
}
