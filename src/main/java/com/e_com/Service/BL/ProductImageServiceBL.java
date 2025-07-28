package com.e_com.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.ProductImageDao;
import com.e_com.Dto.ProductImageDto;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.PaginatedResponseDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductImageServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 * 
 * @author Asjath
 * @date 28 May 2025
 * @version 1.0
 **/

@Slf4j
@Service
public class ProductImageServiceBL {

    @Autowired
    private  ProductImageDao productImageDao;

    public ProductImageDto saveProductImage(ProductImageDto productImageDto) {
        log.info("ProductImageServiceBL.saveProductImage() invoked.");
        return productImageDao.saveProductImage(productImageDto);
    }

    public ProductImageDto updateProductImage(ProductImageDto productImageDto) {
        log.info("ProductImageServiceBL.updateProductImage() invoked.");
        return productImageDao.updateProductImage(productImageDto);
    }

    public ProductImageDto checkProductImageAvailability(Integer productImageId) {
        log.info("ProductImageServiceBL.checkProductImageAvailability() invoked with productImageId: {}", productImageId);
        return productImageDao.checkProductImageAvailability(productImageId);
    }

    public PaginatedResponseDto getAll(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ProductImageServiceBL.getAllPageProductImage() invoked with pageNumber: {}, pageSize: {}, status: {}",
                 pageNumber, pageSize, status);
        return productImageDao.getAllPageProductImage(pageNumber, pageSize, status, searchParameters);
    }

    public List<ProductImageDto> getAllProductImage(String productImageUrl) {
        log.info("BrandServiceBL.getAllBrand() invoked with brandName: {}", productImageUrl);
        return productImageDao.getAllProductImage(productImageUrl);
    }
    
    public List<ProductImageDto> getProductImagesByProductId(Integer productId) {
        return productImageDao.getProductImagesByProductId(productId);
    }


}
