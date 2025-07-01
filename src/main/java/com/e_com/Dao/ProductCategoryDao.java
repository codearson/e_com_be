package com.e_com.Dao;

import com.e_com.Dto.ProductCategoryDto;
import com.e_com.Dto.PaginatedResponseDto;
import java.util.List;

public interface ProductCategoryDao {
	
    ProductCategoryDto saveCategory(ProductCategoryDto dto);
    
    ProductCategoryDto updateCategory(ProductCategoryDto dto);
    
    ProductCategoryDto getCategoryById(Long id);
    
    List<ProductCategoryDto> getAllCategories();
    
    PaginatedResponseDto getAllCategoriesPage(int pageNumber, int pageSize, Integer level, Long parentId);
    
    List<ProductCategoryDto> getCategoryTree();
    
    boolean updateStatus(Long id, Boolean isActive);
    
    PaginatedResponseDto getAllCategoriesPageBySearch(int pageNumber, int pageSize, String search, Integer level, Long parentId);
} 