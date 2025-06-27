package com.e_com.Service.BL;

import com.e_com.Dao.ProductCategoryLevel4Dao;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel4Dto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * Title: ProductCategoryLevel4ServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date Jan 27, 2025
 * @time 5:17 PM
 * @version 1.0
 **/

@Slf4j
@Service
public class ProductCategoryLevel4ServiceBL {

    @Autowired
    private ProductCategoryLevel4Dao productCategoryLevel4Dao;

    public ProductCategoryLevel4Dto saveProductCategoryLevel4(ProductCategoryLevel4Dto productCategoryLevel4Dto) {
        log.info("ProductCategoryLevel4ServiceBL.saveProductCategoryLevel4() invoked.");
        if (productCategoryLevel4Dto != null && productCategoryLevel4Dto.getIsActive() == null) {
            productCategoryLevel4Dto.setIsActive(true); // Default isActive to true for new records
        }
        return productCategoryLevel4Dao.saveProductCategoryLevel4(productCategoryLevel4Dto);
    }

    public ProductCategoryLevel4Dto updateProductCategoryLevel4(ProductCategoryLevel4Dto productCategoryLevel4Dto) {
        log.info("ProductCategoryLevel4ServiceBL.updateProductCategoryLevel4() invoked.");
        ProductCategoryLevel4Dto existingDto = productCategoryLevel4Dao.checkProductCategoryLevel4Availability(productCategoryLevel4Dto.getId());
        if (existingDto == null) {
            log.info("ProductCategoryLevel4 with id {} not found.", productCategoryLevel4Dto.getId());
            return null;
        }
        return productCategoryLevel4Dao.updateProductCategoryLevel4(productCategoryLevel4Dto);
    }

    public ProductCategoryLevel4Dto updateProductCategoryLevel4Status(Integer productCategoryLevel4Id, Boolean status) {
        log.info("ProductCategoryLevel4ServiceBL.updateProductCategoryLevel4Status() invoked with productCategoryLevel4Id: {}, status: {}", 
                 productCategoryLevel4Id, status);
        ProductCategoryLevel4Dto productCategoryLevel4Dto = productCategoryLevel4Dao.checkProductCategoryLevel4Availability(productCategoryLevel4Id);
        if (productCategoryLevel4Dto != null) {
            productCategoryLevel4Dto.setIsActive(status);
            return productCategoryLevel4Dao.updateProductCategoryLevel4(productCategoryLevel4Dto);
        } else {
            log.info("ProductCategoryLevel4 with id {} not found.", productCategoryLevel4Id);
            return null;
        }
    }

    public PaginatedResponseDto getAllPageProductCategoryLevel4(int pageNumber, int pageSize, Boolean status, 
                                                         Map<String, String> searchParameters) {
        log.info("ProductCategoryLevel4ServiceBL.getAllPageProductCategoryLevel4() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return productCategoryLevel4Dao.getAllPageProductCategoryLevel4(pageNumber, pageSize, status, searchParameters);
    }

    public List<ProductCategoryLevel4Dto> getAllBySearchProductCategoryLevel4(String name) {
        log.info("ProductCategoryLevel4ServiceBL.getAllBySearchProductCategoryLevel4() invoked with name: {}", name);
        return productCategoryLevel4Dao.getAllBySearchProductCategoryLevel4(name);
    }
} 