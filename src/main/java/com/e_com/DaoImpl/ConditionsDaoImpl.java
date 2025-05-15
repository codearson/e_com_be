package com.e_com.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.ConditionsDao;
import com.e_com.Domain.Brand;
import com.e_com.Domain.Conditions;
import com.e_com.Domain.UserRole;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.UserRoleDto;
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
    
}
