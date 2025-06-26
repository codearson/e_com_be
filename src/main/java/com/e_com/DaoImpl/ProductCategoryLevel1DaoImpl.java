package com.e_com.DaoImpl;

import com.e_com.Dao.ProductCategoryLevel1Dao;
import com.e_com.Domain.ProductCategoryLevel1;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel1Dto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.ProductCategoryLevel1Transformer;
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
 * Title: ProductCategoryDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 17, 2025
 * @time 12:16:21 AM
 * @version 1.0
 **/

@Slf4j
@Repository
public class ProductCategoryLevel1DaoImpl extends BaseDaoImpl<ProductCategoryLevel1> implements ProductCategoryLevel1Dao {

    @Autowired
    ProductCategoryLevel1Transformer productCategoryLevel1Transformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public ProductCategoryLevel1Dto saveProductCategoryLevel1(ProductCategoryLevel1Dto productCategoryLevel1Dto) {
        log.info("ProductCategoryLevel1DaoImpl.saveProductCategoryLevel1() invoked.");
        try {
            ProductCategoryLevel1 productCategoryLevel1 = productCategoryLevel1Transformer.reverseTransform(productCategoryLevel1Dto);
            ProductCategoryLevel1 savedProductCategoryLevel1 = saveOrUpdate(productCategoryLevel1);
            return productCategoryLevel1Transformer.transform(savedProductCategoryLevel1);
        } catch (Exception e) {
            log.error("Error saving ProductCategoryLevel1: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public ProductCategoryLevel1Dto updateProductCategoryLevel1(ProductCategoryLevel1Dto productCategoryLevel1Dto) {
        log.info("ProductCategoryLevel1DaoImpl.updateProductCategoryLevel1() invoked.");
        try {
            ProductCategoryLevel1 productCategoryLevel1 = productCategoryLevel1Transformer.reverseTransform(productCategoryLevel1Dto);
            ProductCategoryLevel1 updatedProductCategoryLevel1 = saveOrUpdate(productCategoryLevel1);
            return productCategoryLevel1Transformer.transform(updatedProductCategoryLevel1);
        } catch (Exception e) {
            log.error("Error updating ProductCategoryLevel1: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public ProductCategoryLevel1Dto checkProductCategoryLevel1Availability(Integer productCategoryLevel1Id) {
        log.info("ProductCategoryLevel1DaoImpl.checkProductCategoryLevel1Availability() invoked with productCategoryLevel1Id: {}", 
                 productCategoryLevel1Id);
        Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel1.class, "productCategoryLevel1");
        criteria.add(Restrictions.eq("id", productCategoryLevel1Id));
        ProductCategoryLevel1 productCategoryLevel1 = (ProductCategoryLevel1) criteria.uniqueResult();
        return productCategoryLevel1 != null ? productCategoryLevel1Transformer.transform(productCategoryLevel1) : null;
    }

    @Override
    @Transactional
    public PaginatedResponseDto getAllPageProductCategoryLevel1(int pageNumber, int pageSize, Boolean status, 
                                                         Map<String, String> searchParameters) {
        log.info("ProductCategoryLevel1DaoImpl.getAllPageProductCategoryLevel1() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<ProductCategoryLevel1> productCategoryLevel1List = null;

        // Build the count query with status filter
        StringBuilder countString = new StringBuilder("SELECT COUNT(*) FROM product_category_level1");
        if (status != null) {
            countString.append(" WHERE is_active = ?");
        }
        int count = status != null 
            ? jdbcTemplate.queryForObject(countString.toString(), new Object[]{status}, Integer.class)
            : jdbcTemplate.queryForObject(countString.toString(), Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel1.class, "productCategoryLevel1");

        // Add status filter if provided
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        productCategoryLevel1List = criteria.list();

        if (productCategoryLevel1List != null && !productCategoryLevel1List.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(productCategoryLevel1List, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(productCategoryLevel1List.stream()
                .map(productCategoryLevel1Transformer::transform)
                .collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }

    @Override
    @Transactional
    public List<ProductCategoryLevel1Dto> getAllBySearchProductCategoryLevel1(String name) {
        log.info("ProductCategoryLevel1DaoImpl.getAllBySearchProductCategoryLevel1() invoked with name: {}", name);
        Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel1.class, "productCategoryLevel1");

        // Add search filter if provided
        if (name != null && !name.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("name", "%" + name.trim() + "%"));
        }

        List<ProductCategoryLevel1> productCategoryLevel1List = criteria.list();
        return productCategoryLevel1List.stream()
                                 .map(productCategoryLevel1Transformer::transform)
                                 .collect(Collectors.toList());
    }
}