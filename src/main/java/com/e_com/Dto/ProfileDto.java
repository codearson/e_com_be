package com.e_com.Dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Title: ProfileDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 30 July 2025
 * @time 6:38:50 pm
 * @version 1.0
 **/

@Data
public class ProfileDto {

    private Integer id;
    private Integer userId;
    private String profileImage;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private UserDto userDto;
} 