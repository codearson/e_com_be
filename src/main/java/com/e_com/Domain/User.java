package com.e_com.Domain;

import java.io.Serializable;
import java.time.LocalDate;
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
 * May 9, 2025 
 * 10:01:39 AM
 * @author Lathusan Thurairajah
 **/

@Data
@Entity
@Table(name = "user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "firstName",nullable = false)
	private String firstName;
	@Column(name = "lastName",nullable = false)
	private String lastName;
	@Column(name = "userUrl")
	private String userUrl;
	@Column(name = "password",nullable = false)
	private String password;
	@Column(name = "address")
	private String address;
	@Column(name = "emailAddress",nullable = false)
	private String emailAddress;
	@Column(name = "mobileNumber")
	private String mobileNumber;
	@Column(name = "whatsappNumber")
	private String whatsappNumber;
	@Column(name =  "createdDate")
	private LocalDateTime createdDate;
	@Column(name = "modifiedDate")
	private LocalDateTime modifiedDate;
	@Column(name = "gender")
	private String gender;
	@Column(name = "dateOfBirth")
	private LocalDate dateOfBirth;
	@Column(name = "about")
	private String about;
	@Column(name = "twoStepVerification")
	private Boolean twoStepVerification;
	@Column(name = "isActive")
	private Boolean isActive;
	@JoinColumn(name = "userRoleId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private UserRole userRole;
	
}
