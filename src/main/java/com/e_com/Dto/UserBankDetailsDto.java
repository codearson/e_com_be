package com.e_com.Dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Title: UserBankDetailsDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 15, 2025
 * @version 1.0
 **/

@Data
public class UserBankDetailsDto {

	private Integer id;
	
	private String accountHolderName;
	
	private String accountNumber;
	
	private LocalDateTime createdAt;
	
	private UserDto userDto;
	
	private BankDto bankDto;
	
	private BranchDto branchDto;
	
	private Boolean isActive;
	
}
