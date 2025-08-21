package com.e_com.Service.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.ShippingPreferencesDao;
import com.e_com.Dao.UserDao;
import com.e_com.Domain.ShippingPreferences;
import com.e_com.Domain.User;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.ProductDto;
import com.e_com.Dto.ShippingPreferencesDto;
import com.e_com.Transformer.ShippingPreferencesTransformer;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ShippingPreferencesServiceBL {

    @Autowired
    ShippingPreferencesDao shippingPreferencesDao;

    @Autowired
    private UserDao userDao;

    public ShippingPreferencesDto saveShippingPreferences(ShippingPreferencesDto shippingPreferencesDto) {
        log.info("ConditionsServiceBL.saveConditions() invoked.");
        return shippingPreferencesDao.saveShippingPreferences(shippingPreferencesDto);
    }
    
    public ShippingPreferencesDto updateShippingPreferences(ShippingPreferencesDto shippingPreferencesDto) {
        log.info("ConditionsServiceBL.updateConditions() invoked.");
        return shippingPreferencesDao.updateShippingPreferences(shippingPreferencesDto);
    }
    
    public ShippingPreferencesDto updateShippingPreferencesStatus(Integer shippingPreferencesId, Boolean status) {
        log.info("ConditionsServiceBL.updateConditionsStatus() invoked with conditionsId: {}, status: {}", shippingPreferencesId, status);
        ShippingPreferencesDto shippingPreferencesDto = shippingPreferencesDao.checkShippingPreferencesAvailability(shippingPreferencesId);
        if (shippingPreferencesDto != null) {
        	shippingPreferencesDto.setIsActive(status);
            return shippingPreferencesDao.updateShippingPreferences(shippingPreferencesDto);
        } else {
            return null;
        }
    }
    public List<ShippingPreferencesDto> getShippingPreferencesByUserId(Integer shippingPreferencesId) {
        return shippingPreferencesDao.findByUserId(shippingPreferencesId);
    }

}
