package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.ShippingAddressDto;
import com.e_com.Service.ShippingAddressService;
import com.e_com.Service.BL.ShippingAddressServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ShippingAddressServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 15 May 2025
 * @time 19:00:09
 * @version 1.0
 **/

@Component
@Slf4j
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Autowired
    private ShippingAddressServiceBL shippingAddressServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveShippingAddress(ShippingAddressDto shippingAddressDto) {
        log.info("ShippingAddressServiceImpl.saveShippingAddress invoked");
        ResponseDto responseDto = null;
        try {
            ShippingAddressDto savedShippingAddressDto = shippingAddressServiceBL.saveShippingAddress(shippingAddressDto);
            if (savedShippingAddressDto != null) {
                log.info("Shipping Address Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedShippingAddressDto);
            } else {
                log.info("Unable to save Shipping Address details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_SHIPPING_ADDRESS_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving Shipping Address details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_SHIPPING_ADDRESS_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateShippingAddress(ShippingAddressDto shippingAddressDto) {
        log.info("ShippingAddressServiceImpl.updateShippingAddress invoked");
        ResponseDto responseDto = null;
        try {
            ShippingAddressDto updatedShippingAddressDto = shippingAddressServiceBL.updateShippingAddress(shippingAddressDto);
            if (updatedShippingAddressDto != null) {
                log.info("Shipping Address Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedShippingAddressDto);
            } else {
                log.info("Unable to update Shipping Address details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_SHIPPING_ADDRESS_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Shipping Address details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_SHIPPING_ADDRESS_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateShippingAddressStatus(Integer shippingAddressId, Boolean status) {
        log.info("ShippingAddressServiceImpl.updateShippingAddressStatus invoked with shippingAddressId: {}, status: {}", 
                 shippingAddressId, status);
        ResponseDto responseDto = null;
        try {
            ShippingAddressDto updatedShippingAddressDto = shippingAddressServiceBL.updateShippingAddressStatus(shippingAddressId, status);
            if (updatedShippingAddressDto != null) {
                log.info("Shipping Address Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedShippingAddressDto);
            } else {
                log.info("Unable to update Shipping Address status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_SHIPPING_ADDRESS_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Shipping Address status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_SHIPPING_ADDRESS_STATUS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllPageShippingAddress(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ShippingAddressServiceImpl.getAllPageShippingAddress() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            PaginatedResponseDto paginatedResponseDto = shippingAddressServiceBL.getAllPageShippingAddress(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated Shipping Address details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated Shipping Address details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_SHIPPING_ADDRESS_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated Shipping Address details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_SHIPPING_ADDRESS_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllShippingAddress(String address) {
        log.info("ShippingAddressServiceImpl.getAllShippingAddress() invoked with address: {}", address);
        ResponseDto responseDto = null;
        try {
            List<ShippingAddressDto> shippingAddressList = shippingAddressServiceBL.getAllShippingAddress(address);
            if (shippingAddressList != null && !shippingAddressList.isEmpty()) {
                log.info("Retrieved all Shipping Address details.");
                responseDto = serviceUtil.getServiceResponse(shippingAddressList);
            } else {
                log.info("Unable to retrieve all Shipping Address details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_SHIPPING_ADDRESS_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving all Shipping Address details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_SHIPPING_ADDRESS_DETAILS);
        }
        return responseDto;
    }
}
