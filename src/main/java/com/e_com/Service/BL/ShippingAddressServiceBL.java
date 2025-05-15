package com.e_com.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.ShippingAddressDao;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ShippingAddressDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ShippingAddressServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 15 May 2025
 * @time 19:00:39
 * @version 1.0
 **/

@Slf4j
@Service
public class ShippingAddressServiceBL {

    @Autowired
    private ShippingAddressDao shippingAddressDao;

    public ShippingAddressDto saveShippingAddress(ShippingAddressDto shippingAddressDto) {
        log.info("ShippingAddressServiceBL.saveShippingAddress() invoked.");
        return shippingAddressDao.saveShippingAddress(shippingAddressDto);
    }

    public ShippingAddressDto updateShippingAddress(ShippingAddressDto shippingAddressDto) {
        log.info("ShippingAddressServiceBL.updateShippingAddress() invoked.");
        return shippingAddressDao.updateShippingAddress(shippingAddressDto);
    }

    public ShippingAddressDto updateShippingAddressStatus(Integer shippingAddressId, Boolean status) {
        log.info("ShippingAddressServiceBL.updateShippingAddressStatus() invoked with shippingAddressId: {}, status: {}", 
                 shippingAddressId, status);
        ShippingAddressDto shippingAddressDto = shippingAddressDao.checkShippingAddressAvailability(shippingAddressId);
        if (shippingAddressDto != null) {
            shippingAddressDto.setIsActive(status);
            return shippingAddressDao.updateShippingAddress(shippingAddressDto);
        } else {
            return null;
        }
    }

    public PaginatedResponseDto getAllPageShippingAddress(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ShippingAddressServiceBL.getAllPageShippingAddress() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return shippingAddressDao.getAllPageShippingAddress(pageNumber, pageSize, status, searchParameters);
    }

    public List<ShippingAddressDto> getAllShippingAddress(String address) {
        log.info("ShippingAddressServiceBL.getAllShippingAddress() invoked with address: {}", address);
        return shippingAddressDao.getAllShippingAddress(address);
    }
}