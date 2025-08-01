package com.e_com.Service;

import org.springframework.web.multipart.MultipartFile;

import com.e_com.Dto.ProfileDto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: ProfileService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 30 July 2025
 * @time 6:18:45 pm
 * @version 1.0
 **/

public interface ProfileService {

    ResponseDto saveProfile(ProfileDto profileDto);

    ResponseDto updateProfile(ProfileDto profileDto);

    ResponseDto findByUserId(Integer userId);

    ResponseDto uploadProfileImage(MultipartFile file, Integer userId);

    ResponseDto updateProfileStatus(Integer profileId, Boolean isActive);
} 