package com.e_com.DaoImpl;

import com.e_com.Dao.ProductCategoryLevel4Dao;
import com.e_com.Domain.ProductCategoryLevel4;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel4Dto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.ProductCategoryLevel4Transformer;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Title: ProductCategoryLevel4DaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date Jan 27, 2025
 * @time 5:13 PM
 * @version 1.0
 **/

@Slf4j
@Repository
public class ProductCategoryLevel4DaoImpl extends BaseDaoImpl<ProductCategoryLevel4> implements ProductCategoryLevel4Dao {

    @Autowired
    ProductCategoryLevel4Transformer productCategoryLevel4Transformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public ProductCategoryLevel4Dto saveProductCategoryLevel4(ProductCategoryLevel4Dto productCategoryLevel4Dto) {
        log.info("ProductCategoryLevel4DaoImpl.saveProductCategoryLevel4() invoked.");
        try {
            ProductCategoryLevel4 productCategoryLevel4 = productCategoryLevel4Transformer.reverseTransform(productCategoryLevel4Dto);
            ProductCategoryLevel4 savedProductCategoryLevel4 = saveOrUpdate(productCategoryLevel4);
            return productCategoryLevel4Transformer.transform(savedProductCategoryLevel4);
        } catch (Exception e) {
            log.error("Error saving ProductCategoryLevel4: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public ProductCategoryLevel4Dto updateProductCategoryLevel4(ProductCategoryLevel4Dto productCategoryLevel4Dto) {
        log.info("ProductCategoryLevel4DaoImpl.updateProductCategoryLevel4() invoked.");
        try {
            ProductCategoryLevel4 productCategoryLevel4 = productCategoryLevel4Transformer.reverseTransform(productCategoryLevel4Dto);
            ProductCategoryLevel4 updatedProductCategoryLevel4 = saveOrUpdate(productCategoryLevel4);
            return productCategoryLevel4Transformer.transform(updatedProductCategoryLevel4);
        } catch (Exception e) {
            log.error("Error updating ProductCategoryLevel4: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public ProductCategoryLevel4Dto checkProductCategoryLevel4Availability(Integer productCategoryLevel4Id) {
        log.info("ProductCategoryLevel4DaoImpl.checkProductCategoryLevel4Availability() invoked with productCategoryLevel4Id: {}", productCategoryLevel4Id);
        Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel4.class, "productCategoryLevel4");
        criteria.add(Restrictions.eq("id", productCategoryLevel4Id));
        ProductCategoryLevel4 productCategoryLevel4 = (ProductCategoryLevel4) criteria.uniqueResult();
        ProductCategoryLevel4Dto productCategoryLevel4Dto = null;
        if (productCategoryLevel4 != null) {
            productCategoryLevel4Dto = productCategoryLevel4Transformer.transform(productCategoryLevel4);
        }
        return productCategoryLevel4Dto;
    }

    @Override
    @Transactional
    public PaginatedResponseDto getAllPageProductCategoryLevel4(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ProductCategoryLevel4DaoImpl.getAllPageProductCategoryLevel4() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<ProductCategoryLevel4> productCategoryLevel4List = null;

        // Build the count query with status filter
        StringBuilder countString = new StringBuilder("SELECT COUNT(*) FROM product_category_level4");
        if (status != null) {
            countString.append(" WHERE is_active = ?");
        }
        int count = status != null 
            ? jdbcTemplate.queryForObject(countString.toString(), new Object[]{status}, Integer.class)
            : jdbcTemplate.queryForObject(countString.toString(), Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel4.class, "productCategoryLevel4");

        // Add status filter if provided
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        productCategoryLevel4List = criteria.list();

        if (productCategoryLevel4List != null && !productCategoryLevel4List.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(productCategoryLevel4List, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(productCategoryLevel4List.stream()
                .map(productCategoryLevel4Transformer::transform)
                .collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }

    @Override
    @Transactional
    public List<ProductCategoryLevel4Dto> getAllBySearchProductCategoryLevel4(String name) {
        log.info("ProductCategoryLevel4DaoImpl.getAllBySearchProductCategoryLevel4() invoked with name: {}", name);
        Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel4.class, "productCategoryLevel4");

        // Add search filter if provided
        if (name != null && !name.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("name", "%" + name.trim() + "%"));
        }

        List<ProductCategoryLevel4> productCategoryLevel4List = criteria.list();
        return productCategoryLevel4List.stream()
                                 .map(productCategoryLevel4Transformer::transform)
                                 .collect(Collectors.toList());
    }
} 