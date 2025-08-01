package com.e_com.Dao;

import com.e_com.Domain.Profile;
import com.e_com.Dto.ProfileDto;

/**
 * Title: ProfileDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 30 July 2025
 * @time 6:28:55 pm
 * @version 1.0
 **/

public interface ProfileDao extends BaseDao<Profile> {

    ProfileDto saveProfile(ProfileDto profileDto);

    ProfileDto findByUserId(Integer userId);

    ProfileDto updateProfile(ProfileDto profileDto);

    ProfileDto updateProfileStatus(Integer profileId, Boolean isActive);
} 