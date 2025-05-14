package com.e_com.Service.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.ProductCategoryDao;
import com.e_com.Dto.ProductCategoryDto;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class ProductCategoryServiceBL {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    public ProductCategoryDto saveProductCategory(ProductCategoryDto productCategoryDto) {
        log.info("ProductCategoryServiceBL.saveProductCategory() invoked.");
        return productCategoryDao.saveProductCategory(productCategoryDto);
    }

   
}
