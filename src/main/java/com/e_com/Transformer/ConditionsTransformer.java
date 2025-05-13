package com.e_com.Transformer;


import org.springframework.stereotype.Component;

import com.e_com.Domain.Conditions;
import com.e_com.Dto.ConditionsDto;


/**
 * Title: ConditionTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 13 May 2025
 * @time 9:13:53 pm
 * @version 1.0
 **/

@Component
public class ConditionsTransformer implements BaseTransformer<Conditions, ConditionsDto> {

    @Override
    public ConditionsDto transform(Conditions condition) {
        ConditionsDto conditionDto = null;
        if (condition != null) {
            conditionDto = new ConditionsDto();
            conditionDto.setId(condition.getId());
            conditionDto.setConditionType(condition.getConditionType());
            conditionDto.setIsActive(condition.getIsActive());
        }
        return conditionDto;
    }

    @Override
    public Conditions reverseTransform(ConditionsDto conditionDto) {
        Conditions condition = null;
        if (conditionDto != null) {
            condition = new Conditions();
            condition.setId(conditionDto.getId());
            condition.setConditionType(conditionDto.getConditionType());
            condition.setIsActive(conditionDto.getIsActive());
        }
        return condition;
    }
}

