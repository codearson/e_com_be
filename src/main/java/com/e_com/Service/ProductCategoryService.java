package com.e_com.Service;

import com.e_com.Dto.ProductCategoryDto;
import com.e_com.Dto.ResponseDto;

public interface ProductCategoryService {
    ResponseDto saveProductCategory(ProductCategoryDto productCategoryDto);
}