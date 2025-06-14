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
import lombok.*;



@Data
@Entity
@Table(name = "productCategory")
public class ProductCategory implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "parentId")
    private Integer parentId;
    @Column(name = "level")
    private Integer level;
    @Column(name = "isActive")
    private Boolean isActive;
    
}