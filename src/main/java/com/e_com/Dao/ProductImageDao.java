package com.e_com.Dao;

import java.util.List;
import java.util.Map;

import com.e_com.Domain.ProductImage;
import com.e_com.Dto.ProductImageDto;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.PaginatedResponseDto;

/**
 * Title: ProductImageDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 28, 2025
 * @time 10:26:52 PM
 * @version 1.0
 **/

public interface ProductImageDao extends BaseDao<ProductImage> {

    ProductImageDto saveProductImage(ProductImageDto productImageDto);

    ProductImageDto updateProductImage(ProductImageDto productImageDto);

    ProductImageDto checkProductImageAvailability(Integer productImageId);

    PaginatedResponseDto getAllPageProductImage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);

    List<ProductImageDto> getAllProductImage(String ProductImageUrl);
    
    List<ProductImageDto> getProductImagesByProductId(Integer productId);

    
}
