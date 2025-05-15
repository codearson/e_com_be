package com.e_com.DaoImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.UserBankDetailsDao;
import com.e_com.Domain.UserBankDetails;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.UserBankDetailsDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.UserBankDetailsTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: UserBankDetailsDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 15, 2025
 * @version 1.0
 **/

@Slf4j
@Repository
public class UserBankDetailsDaoImpl extends BaseDaoImpl<UserBankDetails> implements UserBankDetailsDao {

	@Autowired
	UserBankDetailsTransformer userBankDetailsTransformer;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public UserBankDetailsDto save(UserBankDetailsDto userBankDetailsDto) {
        log.info("UserBankDetailsDaoImpl.save() invoked.");
        UserBankDetails userBankDetails = userBankDetailsTransformer.reverseTransform(userBankDetailsDto);
        UserBankDetails savedUserBankDetails = saveOrUpdate(userBankDetails);
        return userBankDetailsTransformer.transform(savedUserBankDetails);
    }
    
    @Transactional
    public UserBankDetailsDto update(UserBankDetailsDto userBankDetailsDto) {
        log.info("UserBankDetailsDaoImpl.update() invoked.");
        UserBankDetails userBankDetails = userBankDetailsTransformer.reverseTransform(userBankDetailsDto);
        UserBankDetails updatedUserBankDetails = saveOrUpdate(userBankDetails);
        return userBankDetailsTransformer.transform(updatedUserBankDetails);
    }
    
    @Transactional
    public UserBankDetailsDto checkAvailability(Integer userBankDetailsId) {
        log.info("UserBankDetailsDaoImpl.checkAvailability() invoked with userBankDetailsId: {}", userBankDetailsId);
        Criteria criteria = getCurrentSession().createCriteria(UserBankDetails.class, "userBankDetails");
        criteria.add(Restrictions.eq("userBankDetails.id", userBankDetailsId));
        UserBankDetails userBankDetails = (UserBankDetails) criteria.uniqueResult();
        UserBankDetailsDto userBankDetailsDto = null;
        if (userBankDetails != null) {
        	userBankDetailsDto = userBankDetailsTransformer.transform(userBankDetails);
        }
        return userBankDetailsDto;
    }
    
    @Transactional
    public PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("UserBankDetailsDaoImpl.getAllPage() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<UserBankDetails> userBankDetailsList = null;
        int recordCount = 0;

        String countString = "SELECT COUNT(*) FROM user_bank_details";
        if (status != null) {
            countString += " WHERE is_active = " + (status ? "true" : "false");
        }
        int count = jdbcTemplate.queryForObject(countString, Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(UserBankDetails.class, "userBankDetails");

        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        userBankDetailsList = criteria.list();

        if (userBankDetailsList != null && !userBankDetailsList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(userBankDetailsList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(userBankDetailsList.stream().map(UserBankDetails -> {
                return userBankDetailsTransformer.transform(UserBankDetails);
            }).collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }
    
    @Override
    @Transactional
    public List<UserBankDetailsDto> getAll(String accountHolderName, String accountNumber) {
        log.info("UserBankDetailsDaoImpl.getAll() invoked with accountHolderName/accountNumber: {}", accountHolderName, accountNumber);
        Criteria criteria = getCurrentSession().createCriteria(UserBankDetails.class, "userBankDetails");

        if (accountHolderName != null && !accountHolderName.isEmpty()) {
            criteria.add(Restrictions.ilike("accountHolderName", "%" + accountHolderName + "%"));
        }
        
        if (accountNumber != null && !accountNumber.isEmpty()) {
            criteria.add(Restrictions.ilike("accountNumber", "%" + accountNumber + "%"));
        }

        List<UserBankDetails> userBankDetailsList = criteria.list();
        return userBankDetailsList.stream()
                       .map(userBankDetails -> userBankDetailsTransformer.transform(userBankDetails))
                       .collect(Collectors.toList());
    }
	
}
