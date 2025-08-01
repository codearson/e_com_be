package com.e_com.DaoImpl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.ProfileDao;
import com.e_com.Domain.Profile;
import com.e_com.Domain.User;
import com.e_com.Dto.ProfileDto;
import com.e_com.Transformer.ProfileTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProfileDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 30 July 2025
 * @time 6:32:10 pm
 * @version 1.0
 **/

@Slf4j
@Repository
public class ProfileDaoImpl extends BaseDaoImpl<Profile> implements ProfileDao {

    @Autowired
    private ProfileTransformer profileTransformer;

    @Override
    @Transactional
    public ProfileDto saveProfile(ProfileDto profileDto) {
        log.info("ProfileDaoImpl.saveProfile() invoked.");
        profileDto.setCreatedDate(LocalDateTime.now());
        profileDto.setIsActive(true);
        Profile profile = profileTransformer.reverseTransform(profileDto);
        Profile savedProfile = saveOrUpdate(profile);
        return profileTransformer.transform(savedProfile);
    }

    @Override
    @Transactional
    public ProfileDto findByUserId(Integer userId) {
        log.info("ProfileDaoImpl.findByUserId() invoked with userId: {}", userId);
        Criteria criteria = getCurrentSession().createCriteria(Profile.class, "profile")
                .createAlias("profile.user", "user")
                .add(Restrictions.eq("user.id", userId))
                .add(Restrictions.eq("profile.isActive", true));
        Profile profile = (Profile) criteria.uniqueResult();
        return profile != null ? profileTransformer.transform(profile) : null;
    }

    @Override
    @Transactional
    public ProfileDto updateProfile(ProfileDto profileDto) {
        log.info("ProfileDaoImpl.updateProfile() invoked.");
        profileDto.setModifiedDate(LocalDateTime.now());
        Profile profile = profileTransformer.reverseTransform(profileDto);
        Profile updatedProfile = saveOrUpdate(profile);
        return profileTransformer.transform(updatedProfile);
    }
    
    @Override
    @Transactional
    public ProfileDto updateProfileStatus(Integer profileId, Boolean isActive) {
        log.info("ProfileDaoImpl.updateProfileStatus() invoked with profileId: {} and isActive: {}", profileId, isActive);
        Criteria criteria = getCurrentSession().createCriteria(Profile.class, "profile");
        criteria.add(Restrictions.eq("profile.id", profileId));
        Profile profile = (Profile) criteria.uniqueResult();
        
        if (profile != null) {
            profile.setIsActive(isActive);
            profile.setModifiedDate(LocalDateTime.now());
            Profile updatedProfile = saveOrUpdate(profile);
            return profileTransformer.transform(updatedProfile);
        }
        return null;
    }
} 