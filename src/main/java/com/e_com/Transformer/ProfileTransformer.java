package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.Profile;
import com.e_com.Dto.ProfileDto;
import com.e_com.Domain.User;

/**
 * Title: ProfileTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 30 July 2025
 * @time 6:42:15 pm
 * @version 1.0
 **/

@Component
public class ProfileTransformer implements BaseTransformer<Profile, ProfileDto> {

    @Autowired
    private UserTransfomer userTransformer;

    @Override
    public ProfileDto transform(Profile profile) {
        ProfileDto profileDto = null;
        if (profile != null) {
            profileDto = new ProfileDto();
            profileDto.setId(profile.getId());
            profileDto.setProfileImage(profile.getProfileImage());
            profileDto.setIsActive(profile.getIsActive());
            profileDto.setCreatedDate(profile.getCreatedDate());
            profileDto.setModifiedDate(profile.getModifiedDate());
            
            if (profile.getUser() != null) {
                profileDto.setUserId(profile.getUser().getId());
                profileDto.setUserDto(userTransformer.transform(profile.getUser()));
            }
        }
        return profileDto;
    }

    @Override
    public Profile reverseTransform(ProfileDto profileDto) {
        Profile profile = null;
        if (profileDto != null) {
            profile = new Profile();
            profile.setId(profileDto.getId());
            profile.setProfileImage(profileDto.getProfileImage());
            profile.setIsActive(profileDto.getIsActive());
            profile.setCreatedDate(profileDto.getCreatedDate());
            profile.setModifiedDate(profileDto.getModifiedDate());
            
            if (profileDto.getUserId() != null) {
                User user = new User();
                user.setId(profileDto.getUserId());
                profile.setUser(user);
            }
        }
        return profile;
    }
} 