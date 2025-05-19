package com.e_com.DaoImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.BankDao;
import com.e_com.Domain.Bank;
import com.e_com.Dto.BankDto;
import com.e_com.Transformer.BankTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BankDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 15, 2025
 * @time 7:52:15 PM
 * @version 1.0
 **/

@Slf4j
@Repository
public class BankDaoImpl extends BaseDaoImpl<Bank> implements BankDao {

    @Autowired
    private BankTransformer bankTransformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public BankDto saveBank(BankDto bankDto) {
        log.info("BankDaoImpl.saveBank() invoked.");
        Bank bank = bankTransformer.reverseTransform(bankDto);
        Bank savedBank = saveOrUpdate(bank);
        return bankTransformer.transform(savedBank);
    }
    
    @Transactional
    public BankDto updateBank(BankDto bankDto) {
        log.info("BankDaoImpl.updateBank() invoked.");
        Bank bank = bankTransformer.reverseTransform(bankDto);
        Bank updatedBank = saveOrUpdate(bank);
        return bankTransformer.transform(updatedBank);
    }

    @Transactional
    public BankDto checkBankAvailability(Integer bankId) {
        log.info("BankDaoImpl.checkBankAvailability() invoked with bankId: {}", bankId);
        Criteria criteria = getCurrentSession().createCriteria(Bank.class, "bank");
        criteria.add(Restrictions.eq("bank.id", bankId));
        Bank bank = (Bank) criteria.uniqueResult();
        BankDto bankDto = null;
        if (bank != null) {
            bankDto = bankTransformer.transform(bank);
        }
        return bankDto;
    }
    
    @Override
    @Transactional
    public PaginatedResponseDto getAllPageBank(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("BankDaoImpl.getAllPageBank() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);

        PaginatedResponseDto paginatedResponseDto = null;
        List<Bank> bankList = null;
        int recordCount = 0;

       
        String countString = "SELECT COUNT(*) FROM bank";
        if (status != null) {
        	countString += " WHERE is_active = " + (status ? "true" : "false");
        }
        int count = jdbcTemplate.queryForObject(countString, Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(Bank.class, "bank");

        // Apply status filter
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        // Pagination
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        bankList = criteria.list();

        if (bankList != null && !bankList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(bankList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(bankList.stream().map(bank -> {
            	return bankTransformer.transform(bank);
            }).collect(Collectors.toList())
            );
        }

        return paginatedResponseDto;
    }
    
    @Override
    @Transactional
    public List<BankDto> getAllBank(String bankName) {
        log.info("BankDaoImpl.getAllBank() invoked with bankName: {}", bankName);

        Criteria criteria = getCurrentSession().createCriteria(Bank.class,"bank");

        // Add brandName filter if provided
        if (bankName != null && !bankName.isEmpty()) {
            criteria.add(Restrictions.ilike("bankName", "%" +  bankName + "%" ));
        }

        List<Bank> bankList = criteria.list();

        return bankList.stream()
                       .map(bankTransformer::transform)
                       .collect(Collectors.toList());
    }
        
        
}
