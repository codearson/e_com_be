package com.e_com.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.ProductCategoryLevel2Dao;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel2Dto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductSubCategoryServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 15, 2025
 * @time 4:50:48 PM
 * @version 1.0
 **/
@Slf4j
@Service
public class ProductCategoryLevel2ServiceBL {

	@Autowired
	ProductCategoryLevel2Dao productCategoryLevel2Dao;

	public PaginatedResponseDto getAllPageProductCategoryLevel2(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
		log.info("ProductCategoryLevel2ServiceBL.getAllPageProductCategoryLevel2() invoked with pageNumber: {}, pageSize: {}, status: {}", 
				 pageNumber, pageSize, status);
		return productCategoryLevel2Dao.getAllPageProductCategoryLevel2(pageNumber, pageSize, status, searchParameters);
	}

	public ProductCategoryLevel2Dto save(ProductCategoryLevel2Dto productCategoryLevel2Dto) {
		log.info("productCategoryLevel2ServiceBL.save() invoked.");
		return productCategoryLevel2Dao.save(productCategoryLevel2Dto);
	}

	public ProductCategoryLevel2Dto updateProductCategoryLevel2(ProductCategoryLevel2Dto productCategoryLevel2Dto) {
	    log.info("ProductCategoryLevel2ServiceBL.updateProductCategoryLevel2() invoked.");
	    return productCategoryLevel2Dao.update(productCategoryLevel2Dto);
	}
	
	public List<ProductCategoryLevel2Dto> getProductCategoryLevel2ByName(String productCategoryLevel2Name) {
	    log.info("ProductCategoryLevel2ServiceBL.getProductCategoryLevel2ByName() invoked");
	    return productCategoryLevel2Dao.getProductCategoryLevel2ByName(productCategoryLevel2Name);
	}
	
	public ProductCategoryLevel2Dto updateProductCategoryLevel2Status(Integer productCategoryLevel2Id, Boolean status) {
		ProductCategoryLevel2Dto productCategoryLevel2Dto = productCategoryLevel2Dao.checkProductCategoryLevel2Availability(productCategoryLevel2Id);
		if (productCategoryLevel2Dto != null) {
			productCategoryLevel2Dto.setIsActive(status);
			return productCategoryLevel2Dao.update(productCategoryLevel2Dto);
		} else {
			return null;
		}
	}
	
	public List<ProductCategoryLevel2Dto> getProductCategoryLevel2ById(Integer id) {
		log.info("ProductCategoryLevel2ServiceBL.getProductCategoryLevel2ById()invoked");
		return productCategoryLevel2Dao.getProductCategoryLevel2ById(id);
	}
	
	public List<ProductCategoryLevel2Dto> getAllProductCategoryLevel2(String productCategoryLevel2Name) {
        log.info("ProductCategoryLevel2ServiceBL.getAllProductCategoryLevel2() invoked with productCategoryLevel2Name: {}", productCategoryLevel2Name);
        return productCategoryLevel2Dao.getAllProductCategoryLevel2(productCategoryLevel2Name);
    }
}
