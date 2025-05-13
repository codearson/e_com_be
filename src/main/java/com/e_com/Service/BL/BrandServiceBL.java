package com.e_com.Service.BL;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.BrandDao;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.PaginatedResponseDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BrandServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 13 May 2025
 * @time 19:07:01
 * @version 1.0
 **/

@Slf4j
@Service
public class BrandServiceBL {

    @Autowired
    private BrandDao brandDao;

    public BrandDto saveBrand(BrandDto brandDto) {
        log.info("BrandServiceBL.saveBrand() invoked.");
        return brandDao.saveBrand(brandDto);
    }
    
    public BrandDto updateBrand(BrandDto brandDto) {
        log.info("BrandServiceBL.updateBrand() invoked.");
        return brandDao.updateBrand(brandDto);
    }
    
    public BrandDto updateBrandStatus(Integer brandId, Boolean status) {
        log.info("BrandServiceBL.updateBrandStatus() invoked with brandId: {}, status: {}", brandId, status);
        BrandDto brandDto = brandDao.checkBrandAvailability(brandId);
        if (brandDto != null) {
            brandDto.setIsActive(status);
            return brandDao.updateBrand(brandDto);
        } else {
            return null;
        }
    }
    
    public PaginatedResponseDto getAllPageBrand(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("BrandServiceBL.getAllPageBrand() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return brandDao.getAllPageBrand(pageNumber, pageSize, status, searchParameters);
    }
}