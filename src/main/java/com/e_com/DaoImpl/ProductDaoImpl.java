package com.e_com.DaoImpl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hibernate.criterion.Order;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.ProductDao;
import com.e_com.Domain.Product;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.ProductTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 18:34:59
 * @version 1.0
 **/

@Slf4j
@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

    @Autowired
    ProductTransformer productTransformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public ProductDto saveProduct(ProductDto productDto) {
        log.info("ProductDaoImpl.saveProduct() invoked.");
        try {
            Product product = productTransformer.reverseTransform(productDto);
            Product savedProduct = saveOrUpdate(product);
            return productTransformer.transform(savedProduct);
        } catch (Exception e) {
            log.error("Error saving Product: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        log.info("ProductDaoImpl.updateProduct() invoked.");
        try {
            Product product = productTransformer.reverseTransform(productDto);
            Product updatedProduct = saveOrUpdate(product);
            return productTransformer.transform(updatedProduct);
        } catch (Exception e) {
            log.error("Error updating Product: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public ProductDto checkProductAvailability(Integer productId) {
        log.info("ProductDaoImpl.checkProductAvailability() invoked with productId: {}", productId);
        Criteria criteria = getCurrentSession().createCriteria(Product.class, "product");
        criteria.add(Restrictions.eq("id", productId));
        Product product = (Product) criteria.uniqueResult();
        return product != null ? productTransformer.transform(product) : null;
    }

    @Override
    @Transactional
    public PaginatedResponseDto getAllPageProduct(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ProductDaoImpl.getAllPageProduct() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<Product> productList = null;

        // Build the count query with status filter
        StringBuilder countString = new StringBuilder("SELECT COUNT(*) FROM product");
        if (status != null) {
            countString.append(" WHERE is_active = ?");
        }
        int count = status != null 
            ? jdbcTemplate.queryForObject(countString.toString(), new Object[]{status}, Integer.class)
            : jdbcTemplate.queryForObject(countString.toString(), Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(Product.class, "product");

        // Add status filter if provided
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        productList = criteria.list();

        if (productList != null && !productList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(productList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(productList.stream()
                .map(productTransformer::transform)
                .collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }
    
    @Override
    @Transactional
    public PaginatedResponseDto getAllPageProductBySearch(int pageNumber, int pageSize, Boolean status, String title, String description, Map<String, String> searchParameters) {
        log.info("ProductDaoImpl.getAllPageProductBySearch() invoked with pageNumber: {}, pageSize: {}, status: {}, title: {}, description: {}", 
                 pageNumber, pageSize, status, title, description);
        PaginatedResponseDto paginatedResponseDto = null;
        List<Product> productList = null;

        // Build the count query with status filter
        StringBuilder countString = new StringBuilder("SELECT COUNT(*) FROM product");
        if (status != null) {
            countString.append(" WHERE is_active = ?");
        }
        int count = status != null 
            ? jdbcTemplate.queryForObject(countString.toString(), new Object[]{status}, Integer.class)
            : jdbcTemplate.queryForObject(countString.toString(), Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(Product.class, "product");

        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        if (title != null && !title.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("title", "%" + title.trim() + "%"));
        }

        if (description != null && !description.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("description", "%" + description.trim() + "%"));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        productList = criteria.list();


        if (productList != null && !productList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(productList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(productList.stream()
                .map(productTransformer::transform)
                .collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }

    @Override
    @Transactional
    public List<ProductDto> getAllBySearchProduct(String productCategoryName, String brandName, String conditionType, String type, String title) {
        log.info("ProductDaoImpl.getAllBySearchProduct() invoked with productCategoryName: {}, brandName: {}, conditionType: {}, type: {}, title: {}", 
                 productCategoryName, brandName, conditionType, type, title);
        Criteria criteria = getCurrentSession().createCriteria(Product.class, "product");

        // Join related entities
        criteria.createAlias("productCategory", "productCategory", JoinType.INNER_JOIN);
        criteria.createAlias("brand", "brand", JoinType.INNER_JOIN);
        criteria.createAlias("conditions", "conditions", JoinType.INNER_JOIN);
        criteria.createAlias("status", "status", JoinType.INNER_JOIN);

        // Add search filters if provided
        if (productCategoryName != null && !productCategoryName.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("productCategory.name", "%" + productCategoryName.trim() + "%"));
        }
        if (brandName != null && !brandName.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("brand.brandName", "%" + brandName.trim() + "%"));
        }
        if (conditionType != null && !conditionType.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("conditions.conditionType", "%" + conditionType.trim() + "%"));
        }
        if (type != null && !type.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("status.type", "%" + type.trim() + "%"));
        }
        if (title != null && !title.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("title", "%" + title.trim() + "%"));
        }

        List<Product> productList = criteria.list();
        return productList.stream()
                         .map(productTransformer::transform)
                         .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public PaginatedResponseDto getAllPageSortByPrice(int pageNumber, int pageSize, Boolean status, Boolean asc) {
        log.info("ProductDaoImpl.getAllPageSortByPrice() invoked with pageNumber: {}, pageSize: {}, status: {}, asc: {}", 
                 pageNumber, pageSize, status, asc);

        PaginatedResponseDto paginatedResponseDto = null;
        List<Product> productList = null;

        // Build the count query with optional status filter
        StringBuilder countQuery = new StringBuilder("SELECT COUNT(*) FROM product");
        if (status != null) {
            countQuery.append(" WHERE is_active = ?");
        }
        int count = status != null 
            ? jdbcTemplate.queryForObject(countQuery.toString(), new Object[]{status}, Integer.class)
            : jdbcTemplate.queryForObject(countQuery.toString(), Integer.class);

        // Handle case where pageSize is 0 (fetch all)
        if (pageSize == 0) {
            pageSize = count;
        }

        // Create criteria query
        Criteria criteria = getCurrentSession().createCriteria(Product.class, "product");

        // Apply status filter if provided
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        // Apply sorting based on asc boolean
        if (Boolean.TRUE.equals(asc)) {
            criteria.addOrder(Order.asc("price"));
        } else {
            criteria.addOrder(Order.desc("price"));
        }

        // Set pagination
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);

        // Execute query
        productList = criteria.list();

     // Prepare response
        paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(productList, pageNumber, pageSize, count);

        if (productList != null && !productList.isEmpty()) {
            paginatedResponseDto.setPayload(
                productList.stream()
                    .map(productTransformer::transform)
                    .collect(Collectors.toList())
            );
        } else {
            log.info("No products found matching criteria. Returning empty payload.");
            paginatedResponseDto.setPayload(Collections.emptyList());
        }


        return paginatedResponseDto;
    }

}