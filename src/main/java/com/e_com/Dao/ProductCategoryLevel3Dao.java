package com.e_com.Dao;

import java.util.List;
import java.util.Map;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel3Dto;


/**
 * Title: ProductCategoryLevel3Dao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date Jun 27, 2025
 * @time 6:13:17 PM
 * @version 1.0
 **/
public interface ProductCategoryLevel3Dao {

    PaginatedResponseDto getAllPageProductCategoryLevel3(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);

    ProductCategoryLevel3Dto save(ProductCategoryLevel3Dto productCategoryLevel3Dto);

    ProductCategoryLevel3Dto update(ProductCategoryLevel3Dto productCategoryLevel3Dto);

    List<ProductCategoryLevel3Dto> getProductCategoryLevel3ByName(String productCategoryLevel3Name);

    ProductCategoryLevel3Dto checkProductCategoryLevel3Availability(Integer productCategoryLevel3Id);

    List<ProductCategoryLevel3Dto> getProductCategoryLevel3ById(Integer id);

    List<ProductCategoryLevel3Dto> getAllProductCategoryLevel3(String productCategoryLevel3Name);

}
