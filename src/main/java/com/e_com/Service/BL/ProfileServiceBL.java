package com.e_com.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.ProfileDao;
import com.e_com.Dao.UserDao;
import com.e_com.Domain.User;
import com.e_com.Dto.ProfileDto;
import com.e_com.Dto.UserDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProfileServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 30 July 2025
 * @time 6:25:40 pm
 * @version 1.0
 **/

@Slf4j
@Service
public class ProfileServiceBL {

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private UserDao userDao;

    public ProfileDto saveProfile(ProfileDto profileDto) {
        log.info("ProfileServiceBL.saveProfile() invoked.");
        return profileDao.saveProfile(profileDto);
    }

    public ProfileDto updateProfile(ProfileDto profileDto) {
        log.info("ProfileServiceBL.updateProfile() invoked.");
        return profileDao.updateProfile(profileDto);
    }

    public ProfileDto findByUserId(Integer userId) {
        log.info("ProfileServiceBL.findByUserId() invoked with userId: {}", userId);
        return profileDao.findByUserId(userId);
    }

    public ProfileDto uploadProfileImage(String fileName, Integer userId) {
        log.info("ProfileServiceBL.uploadProfileImage() invoked with fileName: {} and userId: {}", fileName, userId);
        
        // Check if user exists
        User user = userDao.findUserById(userId);
        if (user == null) {
            log.error("User not found with userId: {}", userId);
            return null;
        }

        // Always create a new profile record for each upload
        ProfileDto newProfile = new ProfileDto();
        newProfile.setUserId(userId);
        newProfile.setProfileImage(fileName);
        return profileDao.saveProfile(newProfile);
    }

    public User getUserById(Integer userId) {
        log.info("ProfileServiceBL.getUserById() invoked with userId: {}", userId);
        return userDao.findUserById(userId);
    }

    public ProfileDto updateProfileStatus(Integer profileId, Boolean isActive) {
        log.info("ProfileServiceBL.updateProfileStatus() invoked with profileId: {} and isActive: {}", profileId, isActive);
        return profileDao.updateProfileStatus(profileId, isActive);
    }
} 