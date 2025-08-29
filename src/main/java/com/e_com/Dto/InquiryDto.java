package com.e_com.Dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InquiryDto {

    private Integer id;
    private String referenceNumber;
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userRole;
    private String subject;
    private String message;
    private String status;
    private String adminReply;
    private LocalDateTime repliedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;
}