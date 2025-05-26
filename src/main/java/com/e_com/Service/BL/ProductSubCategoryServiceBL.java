package com.e_com.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.ProductSubCategoryDao;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductSubCategoryDto;

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
public class ProductSubCategoryServiceBL {

	@Autowired
	ProductSubCategoryDao productSubCategoryDao;

	public PaginatedResponseDto getAllPageProductSubCategory(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
		log.info("ProductSubCategoryServiceBL.getAllPageProductSubCategory() invoked with pageNumber: {}, pageSize: {}, status: {}", 
				 pageNumber, pageSize, status);
		return productSubCategoryDao.getAllPageProductSubCategory(pageNumber, pageSize, status, searchParameters);
	}

	public ProductSubCategoryDto save(ProductSubCategoryDto productSubCategoryDto) {
		log.info("ProductSubCategoryServiceBL.save() invoked.");
		return productSubCategoryDao.save(productSubCategoryDto);
	}

	public ProductSubCategoryDto updateProductSubCategory(ProductSubCategoryDto productSubCategoryDto) {
	    log.info("ProductSubCategoryServiceBL.updateProductSubCategory() invoked.");
	    return productSubCategoryDao.update(productSubCategoryDto);
	}
	
	public List<ProductSubCategoryDto> getProductSubCategoryByName(String productSubCategoryName) {
	    log.info("ProductSubCategoryServiceBL.getProductSubCategoryByName() invoked");
	    return productSubCategoryDao.getProductSubCategoryByName(productSubCategoryName);
	}
	
	public ProductSubCategoryDto updateProductSubCategoryStatus(Integer productSubCategoryId, Boolean status) {
		ProductSubCategoryDto productSubCategoryDto = productSubCategoryDao.checkProductSubCategoryAvailability(productSubCategoryId);
		if (productSubCategoryDto != null) {
			productSubCategoryDto.setIsActive(status);
			return productSubCategoryDao.update(productSubCategoryDto);
		} else {
			return null;
		}
	}
	
	public List<ProductSubCategoryDto> getProductSubCategoryById(Integer id) {
		log.info("ProductSubCategoryServiceBL.getProductSubCategoryById()invoked");
		return productSubCategoryDao.getProductSubCategoryById(id);
	}
	
	public List<ProductSubCategoryDto> getAllProductSubCategory(String productSubCategoryName) {
        log.info("ProductSubCategoryServiceBL.getAllProductSubCategory() invoked with productSubCategoryName: {}", productSubCategoryName);
        return productSubCategoryDao.getAllProductSubCategory(productSubCategoryName);
    }
}
