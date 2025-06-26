package com.e_com.Service.BL;

import com.e_com.Dao.ProductCategoryLevel1Dao;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel1Dto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * Title: ProductCategoryServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 17, 2025
 * @time 12:15:32 AM
 * @version 1.0
 **/

@Slf4j
@Service
public class ProductCategoryLevel1ServiceBL {

    @Autowired
    private ProductCategoryLevel1Dao productCategoryLevel1Dao;

    public ProductCategoryLevel1Dto saveProductCategoryLevel1(ProductCategoryLevel1Dto productCategoryLevel1Dto) {
        log.info("ProductCategoryServiceBL.saveProductCategoryLevel1() invoked.");
        if (productCategoryLevel1Dto != null && productCategoryLevel1Dto.getIsActive() == null) {
            productCategoryLevel1Dto.setIsActive(true); // Default isActive to true for new records
        }
        return productCategoryLevel1Dao.saveProductCategoryLevel1(productCategoryLevel1Dto);
    }

    public ProductCategoryLevel1Dto updateProductCategoryLevel1(ProductCategoryLevel1Dto productCategoryLevel1Dto) {
        log.info("ProductCategoryServiceBL.updateProductCategoryLevel1() invoked.");
        ProductCategoryLevel1Dto existingDto = productCategoryLevel1Dao.checkProductCategoryLevel1Availability(productCategoryLevel1Dto.getId());
        if (existingDto == null) {
            log.info("ProductCategoryLevel1 with id {} not found.", productCategoryLevel1Dto.getId());
            return null;
        }
        return productCategoryLevel1Dao.updateProductCategoryLevel1(productCategoryLevel1Dto);
    }

    public ProductCategoryLevel1Dto updateProductCategoryLevel1Status(Integer productCategoryLevel1Id, Boolean status) {
        log.info("ProductCategoryServiceBL.updateProductCategoryLevel1Status() invoked with productCategoryLevel1Id: {}, status: {}", 
                 productCategoryLevel1Id, status);
        ProductCategoryLevel1Dto productCategoryLevel1Dto = productCategoryLevel1Dao.checkProductCategoryLevel1Availability(productCategoryLevel1Id);
        if (productCategoryLevel1Dto != null) {
            productCategoryLevel1Dto.setIsActive(status);
            return productCategoryLevel1Dao.updateProductCategoryLevel1(productCategoryLevel1Dto);
        } else {
            log.info("ProductCategoryLevel1 with id {} not found.", productCategoryLevel1Id);
            return null;
        }
    }

    public PaginatedResponseDto getAllPageProductCategoryLevel1(int pageNumber, int pageSize, Boolean status, 
                                                         Map<String, String> searchParameters) {
        log.info("ProductCategoryServiceBL.getAllPageProductCategoryLevel1() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return productCategoryLevel1Dao.getAllPageProductCategoryLevel1(pageNumber, pageSize, status, searchParameters);
    }

    public List<ProductCategoryLevel1Dto> getAllBySearchProductCategoryLevel1(String name) {
        log.info("ProductCategoryServiceBL.getAllBySearchProductCategoryLevel1() invoked with name: {}", name);
        return productCategoryLevel1Dao.getAllBySearchProductCategoryLevel1(name);
    }
}