package com.e_com.Service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.e_com.Domain.Product;
import com.e_com.Dto.ProductDto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: ProductService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 18:33:48
 * @version 1.0
 **/

public interface ProductService {
	
    ResponseDto saveProduct(ProductDto productDto);
    
    ResponseDto updateProduct(ProductDto productDto);
    
    ResponseDto updateProductStatus(Integer productId, Boolean status);
    
    ResponseDto getAllPageProduct(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    ResponseDto getAllPageProductBySearch(int pageNumber, int pageSize, Boolean status, String title, String description, Map<String, String> searchParameters);
    
    ResponseDto getAllPageFilter(int pageNumber, int pageSize, Boolean status, String category, String size, String brandName, String conditionType, String color, Map<String, String> searchParameters);
    
    ResponseDto getAllBySearchProduct(String categoryName, String brandName, String conditionType, String type, String title);
  
    ResponseDto getAllPageSortByPrice(int pageNumber, int pageSize, Boolean status, Boolean asc);
    
    ResponseDto getProductsByCategoryAndDescendants(Long categoryId);

	ResponseDto getProductById(Integer id);

    ResponseDto getAllProducts();
}