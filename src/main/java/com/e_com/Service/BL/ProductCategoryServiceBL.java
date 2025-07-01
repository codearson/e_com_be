package com.e_com.Service.BL;

import com.e_com.Dao.ProductCategoryDao;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ProductCategoryServiceBL {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    public ProductCategoryDto saveCategory(ProductCategoryDto dto) {
        log.info("ProductCategoryServiceBL.saveCategory() invoked.");
        // Parent assignment handled in DAO
        return productCategoryDao.saveCategory(dto);
    }

    public ProductCategoryDto updateCategory(ProductCategoryDto dto) {
        log.info("ProductCategoryServiceBL.updateCategory() invoked.");
        return productCategoryDao.updateCategory(dto);
    }

    public ProductCategoryDto getCategoryById(Long id) {
        log.info("ProductCategoryServiceBL.getCategoryById() invoked.");
        return productCategoryDao.getCategoryById(id);
    }

    public List<ProductCategoryDto> getAllCategories() {
        log.info("ProductCategoryServiceBL.getAllCategories() invoked.");
        return productCategoryDao.getAllCategories();
    }

    public PaginatedResponseDto getAllCategoriesPage(int pageNumber, int pageSize, Integer level, Long parentId) {
        log.info("ProductCategoryServiceBL.getAllCategoriesPage() invoked.");
        return productCategoryDao.getAllCategoriesPage(pageNumber, pageSize, level, parentId);
    }

    public List<ProductCategoryDto> getCategoryTree() {
        log.info("ProductCategoryServiceBL.getCategoryTree() invoked.");
        return productCategoryDao.getCategoryTree();
    }

    public boolean updateStatus(Long id, Boolean isActive) {
        log.info("ProductCategoryServiceBL.updateStatus() invoked.");
        return productCategoryDao.updateStatus(id, isActive);
    }

    public PaginatedResponseDto getAllCategoriesPageBySearch(int pageNumber, int pageSize, String search, Integer level, Long parentId) {
        log.info("ProductCategoryServiceBL.getAllCategoriesPageBySearch() invoked.");
        return productCategoryDao.getAllCategoriesPageBySearch(pageNumber, pageSize, search, level, parentId);
    }
} 