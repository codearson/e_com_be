package com.e_com.Dto;

import lombok.Data;

/**
 * Title: EmailVerificationDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date Jun 10, 2025
 * @time 11:54:49 AM
 * @version 1.0
 **/

@Data
public class EmailVerificationDto {
	private String email;
    private String token;
}
