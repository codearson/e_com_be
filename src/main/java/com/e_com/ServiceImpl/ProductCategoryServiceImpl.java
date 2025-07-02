package com.e_com.ServiceImpl;

import com.e_com.Dto.ProductCategoryDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductCategoryService;
import com.e_com.Service.BL.ProductCategoryServiceBL;
import com.e_com.Service.Utils.ServiceUtil;
import com.e_com.Constants.ApplicationMessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryServiceBL productCategoryServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveCategory(ProductCategoryDto dto) {
        log.info("ProductCategoryServiceImpl.saveCategory invoked");
        try {
            // Validation
            if (dto == null || dto.getName() == null || dto.getName().trim().isEmpty() || dto.getLevel() == null) {
                return serviceUtil.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_CATEGORY);
            }
            ProductCategoryDto saved = productCategoryServiceBL.saveCategory(dto);
            return serviceUtil.getServiceResponse(saved);
        } catch (Exception e) {
            log.error("Exception occurs while saving category.", e);
            return serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCT_CATEGORY);
        }
    }

    @Override
    public ResponseDto updateCategory(ProductCategoryDto dto) {
        log.info("ProductCategoryServiceImpl.updateCategory invoked");
        try {
            if (dto == null || dto.getId() == null || dto.getName() == null || dto.getName().trim().isEmpty() || dto.getLevel() == null) {
                return serviceUtil.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY);
            }
            ProductCategoryDto updated = productCategoryServiceBL.updateCategory(dto);
            return serviceUtil.getServiceResponse(updated);
        } catch (Exception e) {
            log.error("Exception occurs while updating category.", e);
            return serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_CATEGORY);
        }
    }

    @Override
    public ResponseDto getCategoryById(Long id) {
        log.info("ProductCategoryServiceImpl.getCategoryById invoked");
        try {
            ProductCategoryDto dto = productCategoryServiceBL.getCategoryById(id);
            return serviceUtil.getServiceResponse(dto);
        } catch (Exception e) {
            log.error("Exception occurs while fetching category by id.", e);
            return serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_PRODUCT_CATEGORY_BY_ID);
        }
    }

    @Override
    public ResponseDto getAllCategories() {
        log.info("ProductCategoryServiceImpl.getAllCategories invoked");
        try {
            return serviceUtil.getServiceResponse(productCategoryServiceBL.getAllCategories());
        } catch (Exception e) {
            log.error("Exception occurs while fetching all categories.", e);
            return serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_ALL_PRODUCT_CATEGORY);
        }
    }

    @Override
    public ResponseDto getAllCategoriesPage(int pageNumber, int pageSize, Integer level, Long parentId) {
        log.info("ProductCategoryServiceImpl.getAllCategoriesPage invoked");
        try {
            PaginatedResponseDto page = productCategoryServiceBL.getAllCategoriesPage(pageNumber, pageSize, level, parentId);
            return serviceUtil.getServiceResponse(page);
        } catch (Exception e) {
            log.error("Exception occurs while fetching paginated categories.", e);
            return serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_ALL_PRODUCT_CATEGORY_PAGE);
        }
    }

    @Override
    public ResponseDto getCategoryTree() {
        log.info("ProductCategoryServiceImpl.getCategoryTree invoked");
        try {
            return serviceUtil.getServiceResponse(productCategoryServiceBL.getCategoryTree());
        } catch (Exception e) {
            log.error("Exception occurs while fetching category tree.", e);
            return serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_PRODUCT_CATEGORY_TREE);
        }
    }

    @Override
    public ResponseDto updateStatus(Long id, Boolean isActive) {
        log.info("ProductCategoryServiceImpl.updateStatus invoked");
        try {
            boolean updated = productCategoryServiceBL.updateStatus(id, isActive);
            return serviceUtil.getServiceResponse(updated);
        } catch (Exception e) {
            log.error("Exception occurs while updating category status.", e);
            return serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_CATEGORY_STATUS);
        }
    }

    @Override
    public ResponseDto getAllCategoriesPageBySearch(int pageNumber, int pageSize, String search, Integer level, Long parentId) {
        log.info("ProductCategoryServiceImpl.getAllCategoriesPageBySearch invoked");
        try {
            PaginatedResponseDto page = productCategoryServiceBL.getAllCategoriesPageBySearch(pageNumber, pageSize, search, level, parentId);
            return serviceUtil.getServiceResponse(page);
        } catch (Exception e) {
            log.error("Exception occurs while fetching paginated categories by search.", e);
            return serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_ALL_PRODUCT_CATEGORY_PAGE_SEARCH);
        }
    }
} 