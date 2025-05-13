package com.e_com.DaoImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.BrandDao;
import com.e_com.Domain.Brand;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.BrandTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BrandDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 13 May 2025
 * @time 19:07:36
 * @version 1.0
 **/

@Slf4j
@Repository
public class BrandDaoImpl extends BaseDaoImpl<Brand> implements BrandDao {

    @Autowired
    BrandTransformer brandTransformer;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public BrandDto saveBrand(BrandDto brandDto) {
        log.info("BrandDaoImpl.saveBrand() invoked.");
        Brand brand = brandTransformer.reverseTransform(brandDto);
        Brand savedBrand = saveOrUpdate(brand);
        return brandTransformer.transform(savedBrand);
    }
    
    @Transactional
    public BrandDto updateBrand(BrandDto brandDto) {
        log.info("BrandDaoImpl.updateBrand() invoked.");
        Brand brand = brandTransformer.reverseTransform(brandDto);
        Brand updatedBrand = saveOrUpdate(brand);
        return brandTransformer.transform(updatedBrand);
    }
    
    @Transactional
    public BrandDto checkBrandAvailability(Integer brandId) {
        log.info("BrandDaoImpl.checkBrandAvailability() invoked with brandId: {}", brandId);
        Criteria criteria = getCurrentSession().createCriteria(Brand.class, "brand");
        criteria.add(Restrictions.eq("brand.id", brandId));
        Brand brand = (Brand) criteria.uniqueResult();
        BrandDto brandDto = null;
        if (brand != null) {
            brandDto = brandTransformer.transform(brand);
        }
        return brandDto;
    }
    
    @Override
    @Transactional
    public PaginatedResponseDto getAllPageBrand(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("BrandDaoImpl.getAllPageBrand() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<Brand> brandList = null;
        int recordCount = 0;

        // Modify the count query to consider the status filter
        String countString = "SELECT COUNT(*) FROM brand";
        if (status != null) {
            countString += " WHERE is_active = " + (status ? "true" : "false");
        }
        int count = jdbcTemplate.queryForObject(countString, Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(Brand.class, "brand");

        // Add status filter if provided
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        brandList = criteria.list();

        if (brandList != null && !brandList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(brandList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(brandList.stream().map(brand -> {
                return brandTransformer.transform(brand);
            }).collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }
    
    @Override
    @Transactional
    public List<BrandDto> getAllBrand(String brandName) {
        log.info("BrandDaoImpl.getAllBrand() invoked with brandName: {}", brandName);
        Criteria criteria = getCurrentSession().createCriteria(Brand.class, "brand");

        // Add brandName filter if provided
        if (brandName != null && !brandName.isEmpty()) {
            criteria.add(Restrictions.ilike("brandName", "%" + brandName + "%"));
        }

        List<Brand> brandList = criteria.list();
        return brandList.stream()
                       .map(brand -> brandTransformer.transform(brand))
                       .collect(Collectors.toList());
    }
}