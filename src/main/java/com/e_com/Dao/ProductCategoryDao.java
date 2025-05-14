package com.e_com.Dao;

import java.util.List;
import java.util.Map;

import com.e_com.Domain.ProductCategory;
import com.e_com.Dto.ProductCategoryDto;

public interface ProductCategoryDao extends BaseDao<ProductCategory> {
    ProductCategoryDto saveProductCategory(ProductCategoryDto productCategoryDto);
}