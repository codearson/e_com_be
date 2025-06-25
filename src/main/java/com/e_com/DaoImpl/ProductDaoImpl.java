package com.e_com.DaoImpl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.hibernate.criterion.Order;

import javax.transaction.Transactional;

import org.hibernate. Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
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
    public PaginatedResponseDto getAllPageFilter(int pageNumber, int pageSize, Boolean status, String category, String size, String brandName, String conditionType, String color,  Map<String, String> searchParameters) {
        log.info("ProductDaoImpl.getAllPageFilter() invoked with filters - category: {}, size: {}, brandName: {}, conditionType: {}, color: {}", 
                category, size, brandName, conditionType, color);
        
        PaginatedResponseDto paginatedResponseDto = null;
        List<Product> productList = null;

        try {
            // Create criteria and apply filters
            Criteria criteria = createFilteredCriteria(status, category, size, brandName, conditionType, color);

            // Get total count
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.countDistinct("p.id"));
            criteria.setProjection(projectionList);
            Number totalCount = (Number) criteria.uniqueResult();
            int total = totalCount != null ? totalCount.intValue() : 0;

            log.debug("Total count before pagination: {}", total);

            // Reset criteria for actual results with same filters
            criteria = createFilteredCriteria(status, category, size, brandName, conditionType, color);

            // Add order by
            criteria.addOrder(Order.asc("p.id"));

            // Apply pagination
            if (pageSize == 0) {
                pageSize = total;
            }
            criteria.setFirstResult((pageNumber - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            
            // Ensure distinct results
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            
            // Execute query
            productList = criteria.list();
            log.debug("Retrieved {} products after filtering", productList != null ? productList.size() : 0);

            // Create response
            if (productList != null && !productList.isEmpty()) {
                paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(productList, pageNumber, pageSize, total);
                paginatedResponseDto.setPayload(productList.stream()
                    .map(productTransformer::transform)
                    .collect(Collectors.toList()));
            } else {
                List<Product> emptyList = new ArrayList<>();
                paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(emptyList, pageNumber, pageSize, 0);
                paginatedResponseDto.setPayload(emptyList);
            }

            log.info("Successfully retrieved filtered products. Count: {}", total);
            return paginatedResponseDto;

        } catch (Exception e) {
            log.error("Error in getAllPageFilter: ", e);
            log.error("Error details - Message: {}, Cause: {}", e.getMessage(), 
                     e.getCause() != null ? e.getCause().getMessage() : "No cause");
            throw new RuntimeException("Error retrieving filtered products: " + e.getMessage(), e);
        }
    }

    private Criteria createFilteredCriteria(Boolean status, String category, String size, String brandName, String conditionType, String color) {
        try {
            Criteria criteria = getCurrentSession().createCriteria(Product.class, "p");
            
            // Create necessary aliases based on filters
            if (category != null && !category.trim().isEmpty()) {
                criteria.createAlias("p.productCategory", "pc", JoinType.LEFT_OUTER_JOIN);
                log.debug("Created alias for product category with value: {}", category);
            }
            if (brandName != null && !brandName.trim().isEmpty()) {
                criteria.createAlias("p.brand", "b", JoinType.LEFT_OUTER_JOIN);
            }
            if (conditionType != null && !conditionType.trim().isEmpty()) {
                try {
                    criteria.createAlias("p.conditions", "c", JoinType.LEFT_OUTER_JOIN);
                    log.debug("Successfully created alias for p.conditions");
                } catch (Exception e) {
                    log.debug("Failed to create alias for p.conditions, trying p.condition: {}", e.getMessage());
                    criteria.createAlias("p.condition", "c", JoinType.LEFT_OUTER_JOIN);
                }
            }

            // Apply filters
            if (status != null) {
                criteria.add(Restrictions.eq("p.isActive", status));
            }
            
            // Filter by category - try different possible column names
            if (category != null && !category.trim().isEmpty()) {
                try {
                    log.debug("Attempting to filter by category with value: {}", category);
                    try {
                        criteria.add(Restrictions.ilike("pc.name", "%" + category.trim() + "%"));
                        log.debug("Successfully filtered using pc.name");
                    } catch (Exception e1) {
                        log.debug("Failed with pc.name, trying pc.category: {}", e1.getMessage());
                        try {
                            criteria.add(Restrictions.ilike("pc.category", "%" + category.trim() + "%"));
                            log.debug("Successfully filtered using pc.category");
                        } catch (Exception e2) {
                            log.debug("Failed with pc.category, trying pc.categoryName: {}", e2.getMessage());
                            criteria.add(Restrictions.ilike("pc.categoryName", "%" + category.trim() + "%"));
                            log.debug("Successfully filtered using pc.categoryName");
                        }
                    }
                } catch (Exception e) {
                    log.error("Error applying category filter: {}", e.getMessage());
                    throw e;
                }
            }
            
            if (size != null && !size.trim().isEmpty()) {
                criteria.add(Restrictions.ilike("p.size", "%" + size.trim() + "%"));
            }
            
            if (brandName != null && !brandName.trim().isEmpty()) {
                criteria.add(Restrictions.ilike("b.brandName", "%" + brandName.trim() + "%"));
            }
            
            if (conditionType != null && !conditionType.trim().isEmpty()) {
                try {
                    criteria.add(Restrictions.ilike("c.conditionType", "%" + conditionType.trim() + "%"));
                } catch (Exception e) {
                    criteria.add(Restrictions.ilike("c.type", "%" + conditionType.trim() + "%"));
                }
            }
            
            if (color != null && !color.trim().isEmpty()) {
                criteria.add(Restrictions.ilike("p.color", "%" + color.trim() + "%"));
            }

            return criteria;
        } catch (Exception e) {
            log.error("Error in createFilteredCriteria: {}", e.getMessage());
            log.error("Stack trace: ", e);
            throw new RuntimeException("Error creating filtered criteria: " + e.getMessage(), e);
        }
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

    @Override
    @Transactional
    public void updateProductIsActiveBasedOnQuantity(Integer productId) {
        log.info("ProductDaoImpl.updateProductIsActiveBasedOnQuantity() invoked for productId: {}", productId);
        Criteria criteria = getCurrentSession().createCriteria(Product.class, "product");
        criteria.add(Restrictions.eq("id", productId));
        Product product = (Product) criteria.uniqueResult();
        if (product != null && product.getQuentity() != null && product.getQuentity() == 0 && Boolean.TRUE.equals(product.getIsActive())) {
            product.setIsActive(false);
            saveOrUpdate(product);
            log.info("Product id {} is now inactive due to zero quantity.", productId);
        }
    }

}