package com.e_com.Dao;

import java.util.Map;

import com.e_com.Domain.Brand;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.PaginatedResponseDto;

/**
 * Title: BrandDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 13 May 2025
 * @time 19:07:17
 * @version 1.0
 **/

public interface BrandDao extends BaseDao<Brand> {
	
    BrandDto saveBrand(BrandDto brandDto);
    
    BrandDto updateBrand(BrandDto brandDto);
    
    BrandDto checkBrandAvailability(Integer brandId);
    
    PaginatedResponseDto getAllPageBrand(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
}