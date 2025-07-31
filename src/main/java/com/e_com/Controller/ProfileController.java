package com.e_com.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.e_com.Dto.ProfileDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProfileService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProfileController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 30 July 2025
 * @time 6:15:30 pm
 * @version 1.0
 **/

@Slf4j
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/save")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseDto saveProfile(@RequestBody ProfileDto profileDto) {
        log.info("ProfileController.saveProfile() invoked");
        return profileService.saveProfile(profileDto);
    }

    @PutMapping("/update")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseDto updateProfile(@RequestBody ProfileDto profileDto) {
        log.info("ProfileController.updateProfile() invoked");
        return profileService.updateProfile(profileDto);
    }

    @PutMapping("/updateStatus")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseDto updateProfileStatus(@RequestParam("profileId") Integer profileId, 
                                         @RequestParam("isActive") Boolean isActive) {
        log.info("ProfileController.updateProfileStatus() invoked with profileId: {} and isActive: {}", profileId, isActive);
        return profileService.updateProfileStatus(profileId, isActive);
    }

    @GetMapping("/findByUserId")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseDto findByUserId(@RequestParam("userId") Integer userId) {
        log.info("ProfileController.findByUserId() invoked with userId: {}", userId);
        return profileService.findByUserId(userId);
    }

    @PostMapping("/uploadImage")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseDto uploadProfileImage(@RequestParam("file") MultipartFile file,
                                         @RequestParam("userId") Integer userId) {
        log.info("ProfileController.uploadProfileImage() invoked with userId: {}", userId);
        return profileService.uploadProfileImage(file, userId);
    }
} 