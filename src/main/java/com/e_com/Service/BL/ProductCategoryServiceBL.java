package com.e_com.Service.BL;

import com.e_com.Dao.ProductCategoryDao;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryDto;
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
public class ProductCategoryServiceBL {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    public ProductCategoryDto saveProductCategory(ProductCategoryDto productCategoryDto) {
        log.info("ProductCategoryServiceBL.saveProductCategory() invoked.");
        if (productCategoryDto != null && productCategoryDto.getIsActive() == null) {
            productCategoryDto.setIsActive(true); // Default isActive to true for new records
        }
        return productCategoryDao.saveProductCategory(productCategoryDto);
    }

    public ProductCategoryDto updateProductCategory(ProductCategoryDto productCategoryDto) {
        log.info("ProductCategoryServiceBL.updateProductCategory() invoked.");
        ProductCategoryDto existingDto = productCategoryDao.checkProductCategoryAvailability(productCategoryDto.getId());
        if (existingDto == null) {
            log.info("ProductCategory with id {} not found.", productCategoryDto.getId());
            return null;
        }
        return productCategoryDao.updateProductCategory(productCategoryDto);
    }

    public ProductCategoryDto updateProductCategoryStatus(Integer productCategoryId, Boolean status) {
        log.info("ProductCategoryServiceBL.updateProductCategoryStatus() invoked with productCategoryId: {}, status: {}", 
                 productCategoryId, status);
        ProductCategoryDto productCategoryDto = productCategoryDao.checkProductCategoryAvailability(productCategoryId);
        if (productCategoryDto != null) {
            productCategoryDto.setIsActive(status);
            return productCategoryDao.updateProductCategory(productCategoryDto);
        } else {
            log.info("ProductCategory with id {} not found.", productCategoryId);
            return null;
        }
    }

    public PaginatedResponseDto getAllPageProductCategory(int pageNumber, int pageSize, Boolean status, 
                                                         Map<String, String> searchParameters) {
        log.info("ProductCategoryServiceBL.getAllPageProductCategory() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return productCategoryDao.getAllPageProductCategory(pageNumber, pageSize, status, searchParameters);
    }

    public List<ProductCategoryDto> getAllBySearchProductCategory(String name) {
        log.info("ProductCategoryServiceBL.getAllBySearchProductCategory() invoked with name: {}", name);
        return productCategoryDao.getAllBySearchProductCategory(name);
    }
}