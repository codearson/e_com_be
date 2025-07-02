package com.e_com.Service;

import com.e_com.Dto.ProductCategoryDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ResponseDto;
import java.util.List;

public interface ProductCategoryService {
	
    ResponseDto saveCategory(ProductCategoryDto dto);
    
    ResponseDto updateCategory(ProductCategoryDto dto);
    
    ResponseDto getCategoryById(Long id);
    
    ResponseDto getAllCategories();
    
    ResponseDto getAllCategoriesPage(int pageNumber, int pageSize, Integer level, Long parentId);
    
    ResponseDto getCategoryTree();
    
    ResponseDto updateStatus(Long id, Boolean isActive);
    
    ResponseDto getAllCategoriesPageBySearch(int pageNumber, int pageSize, String search, Integer level, Long parentId);
} 