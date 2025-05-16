package com.e_com.Domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Title: Brand.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 13 May 2025
 * @time 19:04:09
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "brand")
public class Brand implements Serializable {
   
	private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "brandName", nullable = false)
    private String brandName;
    @Column(name = "isActive")
    private Boolean isActive;

}