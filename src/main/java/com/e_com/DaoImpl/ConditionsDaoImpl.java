package com.e_com.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.ConditionsDao;
import com.e_com.Domain.Conditions;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.ConditionsTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ConditionsDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 14 May 2025
 * @time 7:18:20 pm
 * @version 1.0
 **/
@Slf4j
@Repository
public class ConditionsDaoImpl extends BaseDaoImpl<Conditions> implements ConditionsDao {
	
	@Autowired
    private ConditionsTransformer conditionsTransformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public ConditionsDto saveConditions(ConditionsDto conditionsDto) {
        log.info("ConditionsDaoImpl.saveConditions() invoked.");
        Conditions conditions = conditionsTransformer.reverseTransform(conditionsDto);
        Conditions saveConditions = saveOrUpdate(conditions); 
        return conditionsTransformer.transform(saveConditions);
    }
    
    @Transactional
    public ConditionsDto updateConditions(ConditionsDto conditionsDto) {
        log.info("ConditionsDaoImpl.updateConditions() invoked.");
        Conditions conditions = conditionsTransformer.reverseTransform(conditionsDto);
        Conditions updatedConditions = saveOrUpdate(conditions);
        return conditionsTransformer.transform(updatedConditions);
    }
    
    @Transactional
    public ConditionsDto checkConditionsAvailability(Integer conditionsId) {
        log.info("ConditionsDaoImpl.checkConditionsAvailability() invoked with conditionsId: {}", conditionsId);
        Criteria criteria = getCurrentSession().createCriteria(Conditions.class, "conditions");
        criteria.add(Restrictions.eq("conditions.id", conditionsId));
        Conditions conditions = (Conditions) criteria.uniqueResult();
        ConditionsDto conditionsDto = null;
        if (conditions != null) {
            conditionsDto = conditionsTransformer.transform(conditions);
        }
        return conditionsDto;
    }
    
    @Override
	@Transactional
	public List<ConditionsDto> getAllConditions() {
		log.info("ConditionsDaoImpl.getAllConditions() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Conditions.class, "conditions");
		criteria.addOrder(Order.asc("id"));
		List<ConditionsDto> conditionsDtoList = null;
		List<Conditions> conditionsList = (List<Conditions>) criteria.list();
		if (conditionsList != null && !conditionsList.isEmpty()) {
			conditionsDtoList = new ArrayList<>();
			for (Conditions conditions : conditionsList) {
				conditionsDtoList.add(conditionsTransformer.transform(conditions));
			}
		}
		return conditionsDtoList;
	}
    
    @Override
    @Transactional
    public PaginatedResponseDto getAllPageConditions(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ConditionsDaoImpl.getAllPageConditions() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<Conditions> conditionsList = null;
        int recordCount = 0;

        // Modify the count query to consider the status filter
        String countString = "SELECT COUNT(*) FROM conditions";
        if (status != null) {
            countString += " WHERE is_active = " + (status ? "true" : "false");
        }
        int count = jdbcTemplate.queryForObject(countString, Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(Conditions.class, "conditions");

        // Add status filter if provided
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        conditionsList = criteria.list();

        if (conditionsList != null && !conditionsList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(conditionsList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(conditionsList.stream().map(brand -> {
                return conditionsTransformer.transform(brand);
            }).collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }
    
}
