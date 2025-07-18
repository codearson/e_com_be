package com.e_com.Service.BL;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.ProductDao;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 18:34:29
 * @version 1.0
 **/

@Slf4j
@Service
public class ProductServiceBL {

    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private ProductServiceBL productServiceBL;

    public ProductDto saveProduct(ProductDto productDto) {
        log.info("ProductServiceBL.saveProduct() invoked.");
        if (productDto != null) {
            productDto.setCreatedAt(LocalDateTime.now());
            productDto.setUpdatedAt(productDto.getCreatedAt());
        }
        return productDao.saveProduct(productDto);
    }

    public ProductDto updateProduct(ProductDto productDto) {
        log.info("ProductServiceBL.updateProduct() invoked.");
        ProductDto existingDto = productDao.checkProductAvailability(productDto.getId());
        if (existingDto == null) {
            log.info("Product with id {} not found.", productDto.getId());
            return null;
        }
        if (productDto != null) {
            productDto.setCreatedAt(existingDto.getCreatedAt());
            productDto.setUpdatedAt(LocalDateTime.now());
        }
        return productDao.updateProduct(productDto);
    }

    public ProductDto updateProductStatus(Integer productId, Boolean status) {
        log.info("ProductServiceBL.updateProductStatus() invoked with productId: {}, status: {}", productId, status);
        ProductDto productDto = productDao.checkProductAvailability(productId);
        if (productDto != null) {
            productDto.setIsActive(status);
            productDto.setUpdatedAt(LocalDateTime.now());
            return productDao.updateProduct(productDto);
        } else {
            log.info("Product with id {} not found.", productId);
            return null;
        }
    }

    public PaginatedResponseDto getAllPageProduct(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ProductServiceBL.getAllPageProduct() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return productDao.getAllPageProduct(pageNumber, pageSize, status, searchParameters);
    }
    
    public PaginatedResponseDto getAllPageProductBySearch(int pageNumber, int pageSize, Boolean status,String title,String description, Map<String, String> searchParameters) {
        log.info("ProductServiceBL.getAllPageProductBySearch() invoked with pageNumber: {}, pageSize: {}, status: {},title: {},description: {}", 
                 pageNumber, pageSize, status, title, description);
        return productDao.getAllPageProductBySearch(pageNumber, pageSize, status, title, description, searchParameters);
    }
    
    public PaginatedResponseDto getAllPageFilter(int pageNumber, int pageSize, Boolean status, String category, String size, String brandName, String conditionType, String color,  Map<String, String> searchParameters) {
        log.info("ProductServiceBL.getAllPageFilter() invoked with pageNumber: {}, pageSize: {}, status: {}, category: {}, size: {}, brandName: {}, conditionType: {}, color: {}", 
                 pageNumber, pageSize, status, category, size, brandName, conditionType, color);
        return productDao.getAllPageFilter(pageNumber, pageSize, status, category, size, brandName, conditionType, color, searchParameters);
    }


    public List<ProductDto> getAllBySearchProduct(String categoryName, String brandName, String conditionType, String type, String title) {
        log.info("ProductServiceBL.getAllBySearchProduct() invoked with categoryName: {}, brandName: {}, conditionType: {}, type: {}, title: {}", 
                 categoryName, brandName, conditionType, type, title);
        return productDao.getAllBySearchProduct(categoryName, brandName, conditionType, type, title);
    }
    
    public PaginatedResponseDto getAllPageSortByPrice(int pageNumber, int pageSize, Boolean status,Boolean asc) {
        log.info("ProductServiceBL.getAllPageProduct() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return productDao.getAllPageSortByPrice(pageNumber, pageSize, status, asc);
    }

    public List<ProductDto> getAllProducts() {
        log.info("ProductServiceBL.getAllProducts() invoked.");
        return productDao.getAllProducts();
    }
    
    public void updateProductIsActiveBasedOnQuantity(Integer productId) {
        productDao.updateProductIsActiveBasedOnQuantity(productId);
    }
}