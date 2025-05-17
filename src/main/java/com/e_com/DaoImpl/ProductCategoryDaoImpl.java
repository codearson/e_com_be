package com.e_com.DaoImpl;

import com.e_com.Dao.ProductCategoryDao;
import com.e_com.Domain.ProductCategory;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.ProductCategoryTransformer;
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
public class ProductCategoryDaoImpl extends BaseDaoImpl<ProductCategory> implements ProductCategoryDao {

    @Autowired
    ProductCategoryTransformer productCategoryTransformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public ProductCategoryDto saveProductCategory(ProductCategoryDto productCategoryDto) {
        log.info("ProductCategoryDaoImpl.saveProductCategory() invoked.");
        try {
            ProductCategory productCategory = productCategoryTransformer.reverseTransform(productCategoryDto);
            ProductCategory savedProductCategory = saveOrUpdate(productCategory);
            return productCategoryTransformer.transform(savedProductCategory);
        } catch (Exception e) {
            log.error("Error saving ProductCategory: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public ProductCategoryDto updateProductCategory(ProductCategoryDto productCategoryDto) {
        log.info("ProductCategoryDaoImpl.updateProductCategory() invoked.");
        try {
            ProductCategory productCategory = productCategoryTransformer.reverseTransform(productCategoryDto);
            ProductCategory updatedProductCategory = saveOrUpdate(productCategory);
            return productCategoryTransformer.transform(updatedProductCategory);
        } catch (Exception e) {
            log.error("Error updating ProductCategory: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public ProductCategoryDto checkProductCategoryAvailability(Integer productCategoryId) {
        log.info("ProductCategoryDaoImpl.checkProductCategoryAvailability() invoked with productCategoryId: {}", 
                 productCategoryId);
        Criteria criteria = getCurrentSession().createCriteria(ProductCategory.class, "productCategory");
        criteria.add(Restrictions.eq("id", productCategoryId));
        ProductCategory productCategory = (ProductCategory) criteria.uniqueResult();
        return productCategory != null ? productCategoryTransformer.transform(productCategory) : null;
    }

    @Override
    @Transactional
    public PaginatedResponseDto getAllPageProductCategory(int pageNumber, int pageSize, Boolean status, 
                                                         Map<String, String> searchParameters) {
        log.info("ProductCategoryDaoImpl.getAllPageProductCategory() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<ProductCategory> productCategoryList = null;

        // Build the count query with status filter
        StringBuilder countString = new StringBuilder("SELECT COUNT(*) FROM product_category");
        if (status != null) {
            countString.append(" WHERE is_active = ?");
        }
        int count = status != null 
            ? jdbcTemplate.queryForObject(countString.toString(), new Object[]{status}, Integer.class)
            : jdbcTemplate.queryForObject(countString.toString(), Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(ProductCategory.class, "productCategory");

        // Add status filter if provided
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        productCategoryList = criteria.list();

        if (productCategoryList != null && !productCategoryList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(productCategoryList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(productCategoryList.stream()
                .map(productCategoryTransformer::transform)
                .collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }

    @Override
    @Transactional
    public List<ProductCategoryDto> getAllBySearchProductCategory(String name) {
        log.info("ProductCategoryDaoImpl.getAllBySearchProductCategory() invoked with name: {}", name);
        Criteria criteria = getCurrentSession().createCriteria(ProductCategory.class, "productCategory");

        // Add search filter if provided
        if (name != null && !name.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("name", "%" + name.trim() + "%"));
        }

        List<ProductCategory> productCategoryList = criteria.list();
        return productCategoryList.stream()
                                 .map(productCategoryTransformer::transform)
                                 .collect(Collectors.toList());
    }
}