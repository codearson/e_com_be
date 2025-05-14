package com.e_com.DaoImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.ConditionsDao;
import com.e_com.Domain.Conditions;
import com.e_com.Dto.ConditionsDto;
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
        Conditions saveConditions = saveOrUpdate(conditions); // inherited from BaseDaoImpl
        return conditionsTransformer.transform(saveConditions);
    }
    
}
