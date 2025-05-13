package com.e_com.Service;

import java.util.Map;

import com.e_com.Dto.BrandDto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: BrandService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 13 May 2025
 * @time 19:05:53
 * @version 1.0
 **/

public interface BrandService {
	
    ResponseDto saveBrand(BrandDto brandDto);
    
    ResponseDto updateBrand(BrandDto brandDto);
    
    ResponseDto updateBrandStatus(Integer brandId, Boolean status);
    
    ResponseDto getAllPageBrand(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    ResponseDto getAllBrand(String brandName);
}