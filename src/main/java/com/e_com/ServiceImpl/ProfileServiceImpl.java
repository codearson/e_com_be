package com.e_com.ServiceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.ProfileDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProfileService;
import com.e_com.Service.BL.ProfileServiceBL;
import com.e_com.Service.Utils.FileUploadUtil;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProfileServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 30 July 2025
 * @time 6:22:15 pm
 * @version 1.0
 **/

@Component
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileServiceBL profileServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveProfile(ProfileDto profileDto) {
        log.info("ProfileServiceImpl.saveProfile() invoked");
        ResponseDto responseDto = null;
        try {
            ProfileDto savedDto = profileServiceBL.saveProfile(profileDto);
            if (savedDto != null) {
                log.info("Profile saved successfully.");
                responseDto = serviceUtil.getServiceResponse(savedDto);
            } else {
                log.info("Unable to save profile.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PROFILE_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving profile.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PROFILE_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateProfile(ProfileDto profileDto) {
        log.info("ProfileServiceImpl.updateProfile() invoked");
        ResponseDto responseDto = null;
        try {
            ProfileDto updatedDto = profileServiceBL.updateProfile(profileDto);
            if (updatedDto != null) {
                log.info("Profile updated successfully.");
                responseDto = serviceUtil.getServiceResponse(updatedDto);
            } else {
                log.info("Unable to update profile.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PROFILE_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating profile.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PROFILE_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto findByUserId(Integer userId) {
        log.info("ProfileServiceImpl.findByUserId() invoked with userId: {}", userId);
        ResponseDto responseDto = null;
        try {
            ProfileDto profileDto = profileServiceBL.findByUserId(userId);
            if (profileDto != null) {
                log.info("Profile found for userId: {}", userId);
                responseDto = serviceUtil.getServiceResponse(profileDto);
            } else {
                log.info("Profile not found for userId: {}", userId);
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_PROFILE_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Exception occurs while finding profile by userId.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_FIND_PROFILE_BY_USER_ID);
        }
        return responseDto;
    }

    @Override
    public ResponseDto uploadProfileImage(MultipartFile file, Integer userId) {
        log.info("ProfileServiceImpl.uploadProfileImage() invoked with userId: {}", userId);
        ResponseDto responseDto = null;
        try {
            if (file == null || file.isEmpty()) {
                log.warn("No file provided for upload.");
                return serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_NO_FILE_PROVIDED
                );
            }

            if (userId == null) {
                log.warn("No userId provided for upload.");
                return serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_USER_ID_REQUIRED
                );
            }

            // Get user email for file naming
            com.e_com.Domain.User user = profileServiceBL.getUserById(userId);
            if (user == null) {
                log.error("User not found with userId: {}", userId);
                return serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_USER_NOT_FOUND
                );
            }

            // Create filename with email and timestamp
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            
            String email = user.getEmailAddress();
            String sanitizedEmail = email.replaceAll("[^a-zA-Z0-9]", "_");
            String fileName = sanitizedEmail + "_" + System.currentTimeMillis() + fileExtension;

            // Save file to local directory
            String uploadDir = "uploads/profiles/";
            try {
                FileUploadUtil.saveFile(uploadDir, fileName, file);
            } catch (IOException e) {
                log.error("Failed to save file to local disk: {}", fileName, e);
                return serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_FILE_SAVE_FAILED
                );
            }

            // Save profile information to database
            ProfileDto savedProfile = profileServiceBL.uploadProfileImage(fileName, userId);
            if (savedProfile != null) {
                log.info("Profile image uploaded successfully for userId: {}", userId);
                responseDto = serviceUtil.getServiceResponse(savedProfile);
            } else {
                log.error("Failed to save profile information to database for userId: {}", userId);
                responseDto = serviceUtil.getErrorServiceResponse(
                    ApplicationMessageConstants.ServiceErrorMessages.ERR_PROFILE_SAVE_FAILED
                );
            }

        } catch (Exception e) {
            log.error("Exception occurred while uploading profile image.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                ApplicationMessageConstants.ServiceErrorMessages.EX_UPLOAD_PROFILE_IMAGE
            );
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateProfileStatus(Integer profileId, Boolean isActive) {
        log.info("ProfileServiceImpl.updateProfileStatus() invoked with profileId: {} and isActive: {}", profileId, isActive);
        ResponseDto responseDto = null;
        try {
            ProfileDto updatedDto = profileServiceBL.updateProfileStatus(profileId, isActive);
            if (updatedDto != null) {
                log.info("Profile status updated successfully.");
                responseDto = serviceUtil.getServiceResponse(updatedDto);
            } else {
                log.info("Profile not found for status update.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_PROFILE_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating profile status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PROFILE_STATUS);
        }
        return responseDto;
    }
} 