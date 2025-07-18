package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dao.ProductDao;
import com.e_com.Domain.Product;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductService;
import com.e_com.Service.BL.ProductServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;



/**
 * Title: ProductServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 18:34:07
 * @version 1.0
 **/

@Component
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductServiceBL productServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;
    
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductDao productRepository; 

    

    @Override
    public ResponseDto saveProduct(ProductDto productDto) {
        log.info("ProductServiceLevel1Impl.saveProduct invoked");
        ResponseDto responseDto = null;
        try {
            if (productDto == null || productDto.getProductCategoryDto() == null || 
                productDto.getBrandDto() == null || productDto.getConditionsDto() == null || 
                productDto.getStatusDto() == null) {
                log.info("Invalid Product data provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_DETAILS);
            }
            ProductDto savedProductDto = productServiceBL.saveProduct(productDto);
            if (savedProductDto != null) {
                log.info("Product Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedProductDto);
            } else {
                log.info("Unable to save Product details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving Product details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCT_DETAILS);
        }
        return responseDto;    }

    @Override
    public ResponseDto updateProduct(ProductDto productDto) {
        log.info("ProductServiceImpl.updateProduct invoked");
        ResponseDto responseDto = null;
        try {
            if (productDto == null || productDto.getId() == null || 
                productDto.getProductCategoryDto() == null || 
                productDto.getBrandDto() == null || productDto.getConditionsDto() == null || 
                productDto.getStatusDto() == null) {
                log.info("Invalid Product data provided for update.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_DETAILS);
            }
            ProductDto updatedProductDto = productServiceBL.updateProduct(productDto);
            if (updatedProductDto != null) {
                log.info("Product Details updated.");
                productServiceBL.updateProductIsActiveBasedOnQuantity(updatedProductDto.getId());
                responseDto = serviceUtil.getServiceResponse(updatedProductDto);
            } else {
                log.info("Unable to update Product details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Product details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateProductStatus(Integer productId, Boolean status) {
        log.info("ProductServiceImpl.updateProductStatus invoked with productId: {}, status: {}", productId, status);
        ResponseDto responseDto = null;
        try {
            if (productId == null) {
                log.info("Invalid productId provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_STATUS);
            }
            ProductDto updatedProductDto = productServiceBL.updateProductStatus(productId, status);
            if (updatedProductDto != null) {
                log.info("Product Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedProductDto);
            } else {
                log.info("Unable to update Product status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Product status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_STATUS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllPageProduct(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ProductServiceImpl.getAllPageProduct() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            if (pageNumber < 1 || pageSize < 0) {
                log.info("Invalid pagination parameters provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_DETAILS);
            }
            PaginatedResponseDto paginatedResponseDto = productServiceBL.getAllPageProduct(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated Product details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated Product details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated Product details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllPageProductBySearch(int pageNumber, int pageSize, Boolean status, String title, String description, Map<String, String> searchParameters) {
        log.info("ProductServiceImpl.getAllPageProductBySearch() invoked with pageNumber: {}, pageSize: {}, status: {}, title: {}, description: {}", 
                 pageNumber, pageSize, status, title, description);
        ResponseDto responseDto = null;
        try {
            if (pageNumber < 1 || pageSize < 0) {
                log.info("Invalid pagination search parameters provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_DETAILS_BY_SEARCH);
            }
            PaginatedResponseDto paginatedResponseDto = productServiceBL.getAllPageProductBySearch(pageNumber, pageSize, status, title, description, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated search Product details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated search Product details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_DETAILS_BY_SEARCH);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated search Product details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_DETAILS_BY_SEARCH);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto getAllPageFilter(int pageNumber, int pageSize, Boolean status, String category, String size, String brandName, String conditionType, String color, Map<String, String> searchParameters) {
        log.info("ProductServiceImpl.getAllPageFilter() invoked with pageNumber: {}, pageSize: {}, status: {}, category: {}, size: {}, brandName: {}, conditionType: {}, color: {}", 
                 pageNumber, pageSize, status, category, size, brandName, conditionType, color);
        ResponseDto responseDto = null;
        try {
            if (pageNumber < 1 || pageSize < 0) {
                log.info("Invalid pagination parameters provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_FILTERED_PRODUCT_DETAILS);
            }
            PaginatedResponseDto paginatedResponseDto = productServiceBL.getAllPageFilter(pageNumber, pageSize, status, category, size, brandName, conditionType, color, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated filtered Product details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated filtered Product details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_FILTERED_PRODUCT_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated filtered Product details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_FILTERED_PRODUCT_DETAILS);
        }
        return responseDto;
    }


    @Override
    public ResponseDto getAllBySearchProduct(String categoryName, String brandName, String conditionType, String type, String title) {
        log.info("ProductServiceImpl.getAllBySearchProduct() invoked with categoryName: {}, brandName: {}, conditionType: {}, type: {}, title: {}", 
                 categoryName, brandName, conditionType, type, title);
        ResponseDto responseDto = null;
        try {
            List<ProductDto> productList = productServiceBL.getAllBySearchProduct(categoryName, brandName, conditionType, type, title);
            log.info("Retrieved Product details by search.");
            responseDto = serviceUtil.getServiceResponse(productList);
        } catch (Exception e) {
            log.error("Exception occurs while retrieving Product details by search.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto getAllPageSortByPrice(int pageNumber, int pageSize, Boolean status, Boolean asc) {
        log.info("ProductServiceImpl.getAllPageSortByPrice() invoked with pageNumber: {}, pageSize: {}, status: {}, asc: {}", 
                 pageNumber, pageSize, status, asc);
        ResponseDto responseDto = null;
        try {
            if (pageNumber < 1 || pageSize < 0) {
                log.info("Invalid pagination parameters provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_DETAILS);
            }
            PaginatedResponseDto paginatedResponseDto = productServiceBL.getAllPageSortByPrice(pageNumber, pageSize, status, asc);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated Product details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated Product details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated Product details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllProducts() {
        log.info("ProductServiceImpl.getAllProducts invoked");
        ResponseDto responseDto = null;
        try {
            List<ProductDto> productList = productServiceBL.getAllProducts();
            if (productList != null && !productList.isEmpty()) {
                log.info("All products retrieved.");
                responseDto = serviceUtil.getServiceResponse(productList);
            } else {
                log.info("No products found.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving all products.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getProductsByCategoryAndDescendants(Long categoryId) {
        log.info("ProductServiceImpl.getProductsByCategoryAndDescendants invoked with categoryId: {}", categoryId);
        try {
            List<ProductDto> products = productDao.getProductsByCategoryAndDescendants(categoryId);
            return serviceUtil.getServiceResponse(products);
        } catch (Exception e) {
            log.error("Exception occurs while fetching products by category and descendants.", e);
            return serviceUtil.getExceptionServiceResponseByProperties("ex.get.products.by.category.descendants");
        }
    }

    @Override
    public ResponseDto getProductById(Integer id) {
        log.info("ProductServiceImpl.getProductById invoked with id: {}", id);
        ResponseDto responseDto = null;
        try {
            if (id == null) {
                log.info("Invalid product id provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_DETAILS);
            }
            ProductDto productDto = productDao.checkProductAvailability(id);
            if (productDto != null) {
                log.info("Product details retrieved.");
                responseDto = serviceUtil.getServiceResponse(productDto);
            } else {
                log.info("Unable to retrieve product details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving product details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_DETAILS);
        }
        return responseDto;
    }

}