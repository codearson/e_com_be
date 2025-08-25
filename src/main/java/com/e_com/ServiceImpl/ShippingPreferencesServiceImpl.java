package com.e_com.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.ShippingPreferencesDto;
import com.e_com.Service.ShippingPreferencesService;
import com.e_com.Service.BL.ShippingPreferencesServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Component
@Slf4j
public class ShippingPreferencesServiceImpl implements ShippingPreferencesService {

    @Autowired
    private ShippingPreferencesServiceBL shippingPreferencesServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveShippingPreferences(ShippingPreferencesDto shippingPreferencesDto) {
        log.info("ShippingPreferencesServiceImpl.saveShippingPreferences invoked");
        ResponseDto responseDto = null;

        try {
        	ShippingPreferencesDto savedShippingPreferencesDto = shippingPreferencesServiceBL.saveShippingPreferences(shippingPreferencesDto);
            if (savedShippingPreferencesDto != null) {
                log.info("ShippingPreferences details saved.");
                responseDto = serviceUtil.getServiceResponse(savedShippingPreferencesDto);
            } else {
                log.warn("Unable to save ShippingPreferences details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_SHIPPING_PREFERENCES_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving ShippingPreferences details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_SHIPPING_PREFERENCES_DETAILS);
        }

        return responseDto;
    }
    
    @Override
    public ResponseDto updateShippingPreferences(ShippingPreferencesDto shippingPreferencesDto) {
        log.info("ShippingPreferencesServiceImpl.updateShippingPreferences invoked");
        ResponseDto responseDto = null;
        try {
        	ShippingPreferencesDto updatedShippingPreferencesDto = shippingPreferencesServiceBL.updateShippingPreferences(shippingPreferencesDto);
            if (updatedShippingPreferencesDto != null) {
                log.info("ShippingPreferences details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedShippingPreferencesDto);
            } else {
                log.info("Unable to update ShippingPreferences details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_SHIPPING_PREFERENCES_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating ShippingPreferences details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_SHIPPING_PREFERENCES_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto updateShippingPreferencesStatus(Integer shippingPreferencesId, Boolean status) {
        log.info("ShippingPreferencesServiceImpl.updateShippingPreferencesStatus invoked with ShippingPreferencesId: {}, status: {}", shippingPreferencesId, status);
        ResponseDto responseDto = null;
        try {
        	ShippingPreferencesDto updatedShippingPreferencesDto = shippingPreferencesServiceBL.updateShippingPreferencesStatus(shippingPreferencesId, status);
            if (updatedShippingPreferencesDto != null) {
                log.info("ShippingPreferences status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedShippingPreferencesDto);
            } else {
                log.info("Unable to update ShippingPreferences status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_SHIPPING_PREFERENCES_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating ShippingPreferences status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_SHIPPING_PREFERENCES_DETAILS);
        }
        return responseDto;
    }
   
    


}
