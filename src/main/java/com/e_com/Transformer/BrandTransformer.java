package com.e_com.Transformer;

import org.springframework.stereotype.Component;

import com.e_com.Domain.Brand;
import com.e_com.Dto.BrandDto;

/**
 * Title: BrandTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 13 May 2025
 * @time 19:05:12
 * @version 1.0
 **/

@Component
public class BrandTransformer implements BaseTransformer<Brand, BrandDto> {

    @Override
    public BrandDto transform(Brand brand) {
        BrandDto brandDto = null;
        if (brand != null) {
            brandDto = new BrandDto();
            brandDto.setId(brand.getId());
            brandDto.setBrandName(brand.getBrandName());
            brandDto.setIsActive(brand.getIsActive());
        }
        return brandDto;
    }

    @Override
    public Brand reverseTransform(BrandDto brandDto) {
        Brand brand = null;
        if (brandDto != null) {
            brand = new Brand();
            brand.setId(brandDto.getId());
            brand.setBrandName(brandDto.getBrandName());
            brand.setIsActive(brandDto.getIsActive());
        }
        return brand;
    }
}