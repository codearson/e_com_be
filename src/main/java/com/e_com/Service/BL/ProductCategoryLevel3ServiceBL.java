package com.e_com.Service.BL;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.e_com.Dao.ProductCategoryLevel3Dao;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel3Dto;
import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductCategoryLevel3ServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date Jun 27, 2025
 * @time 5:50:33 PM
 * @version 1.0
 **/

@Slf4j
@Service
public class ProductCategoryLevel3ServiceBL {

    @Autowired
    ProductCategoryLevel3Dao productCategoryLevel3Dao;

    public PaginatedResponseDto getAllPageProductCategoryLevel3(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ProductCategoryLevel3ServiceBL.getAllPageProductCategoryLevel3() invoked with pageNumber: {}, pageSize: {}, status: {}", pageNumber, pageSize, status);
        return productCategoryLevel3Dao.getAllPageProductCategoryLevel3(pageNumber, pageSize, status, searchParameters);
    }

    public ProductCategoryLevel3Dto save(ProductCategoryLevel3Dto productCategoryLevel3Dto) {
        log.info("ProductCategoryLevel3ServiceBL.save() invoked.");
        return productCategoryLevel3Dao.save(productCategoryLevel3Dto);
    }

    public ProductCategoryLevel3Dto updateProductCategoryLevel3(ProductCategoryLevel3Dto productCategoryLevel3Dto) {
        log.info("ProductCategoryLevel3ServiceBL.updateProductCategoryLevel3() invoked.");
        return productCategoryLevel3Dao.update(productCategoryLevel3Dto);
    }

    public List<ProductCategoryLevel3Dto> getProductCategoryLevel3ByName(String productCategoryLevel3Name) {
        log.info("ProductCategoryLevel3ServiceBL.getProductCategoryLevel3ByName() invoked");
        return productCategoryLevel3Dao.getProductCategoryLevel3ByName(productCategoryLevel3Name);
    }

    public ProductCategoryLevel3Dto updateProductCategoryLevel3Status(Integer productCategoryLevel3Id, Boolean status) {
        ProductCategoryLevel3Dto productCategoryLevel3Dto = productCategoryLevel3Dao.checkProductCategoryLevel3Availability(productCategoryLevel3Id);
        if (productCategoryLevel3Dto != null) {
            productCategoryLevel3Dto.setIsActive(status);
            return productCategoryLevel3Dao.update(productCategoryLevel3Dto);
        } else {
            return null;
        }
    }

    public List<ProductCategoryLevel3Dto> getProductCategoryLevel3ById(Integer id) {
        log.info("ProductCategoryLevel3ServiceBL.getProductCategoryLevel3ById() invoked");
        return productCategoryLevel3Dao.getProductCategoryLevel3ById(id);
    }

    public List<ProductCategoryLevel3Dto> getAllProductCategoryLevel3(String productCategoryLevel3Name) {
        log.info("ProductCategoryLevel3ServiceBL.getAllProductCategoryLevel3() invoked with productCategoryLevel3Name: {}", productCategoryLevel3Name);
        return productCategoryLevel3Dao.getAllProductCategoryLevel3(productCategoryLevel3Name);
    }
}
