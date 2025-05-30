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

import com.e_com.Dao.ProductImageDao;
import com.e_com.Domain.Brand;
import com.e_com.Domain.ProductImage;
import com.e_com.Dto.ProductImageDto;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.ProductImageTransformer;

import lombok.extern.slf4j.Slf4j;
/**
 * Title: ProductImageDaiImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 28, 2025
 * @time 10:30:52 PM
 * @version 1.0
 **/

@Slf4j
@Repository
public class ProductImageDaoImpl extends BaseDaoImpl<ProductImage> implements ProductImageDao {

    @Autowired
    ProductImageTransformer productImageTransformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public ProductImageDto saveProductImage(ProductImageDto productImageDto) {
        log.info("ProductImageDaoImpl.saveProductImage() invoked.");
        ProductImage productImage = productImageTransformer.reverseTransform(productImageDto);
        ProductImage savedProductImage = saveOrUpdate(productImage);
        return productImageTransformer.transform(savedProductImage);
    }

    @Override
    @Transactional
    public ProductImageDto updateProductImage(ProductImageDto productImageDto) {
        log.info("ProductImageDaoImpl.updateProductImage() invoked.");
        ProductImage productImage = productImageTransformer.reverseTransform(productImageDto);
        ProductImage updatedProductImage = saveOrUpdate(productImage);
        return productImageTransformer.transform(updatedProductImage);
    }

    @Override
    @Transactional
    public ProductImageDto checkProductImageAvailability(Integer productImageId) {
        log.info("ProductImageDaoImpl.checkProductImageAvailability() invoked with productImageId: {}", productImageId);
        Criteria criteria = getCurrentSession().createCriteria(ProductImage.class, "productImage");
        criteria.add(Restrictions.eq("productImage.id", productImageId));
        ProductImage productImage = (ProductImage) criteria.uniqueResult();
        ProductImageDto productImageDto = null;
        if (productImage != null) {
            productImageDto = productImageTransformer.transform(productImage);
        }
        return productImageDto;
    }

    @Override
    @Transactional
    public PaginatedResponseDto getAllPageProductImage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ProductImageDaoImpl.getAllPageProductImage() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<ProductImage> productImageList = null;

        String countString = "SELECT COUNT(*) FROM product_image";
        if (status != null) {
            countString += " WHERE is_active = " + (status ? "true" : "false");
        }
        int count = jdbcTemplate.queryForObject(countString, Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(ProductImage.class, "productImage");
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        productImageList = criteria.list();

        if (productImageList != null && !productImageList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(productImageList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(productImageList.stream().map(productImage -> {
                return productImageTransformer.transform(productImage);
            }).collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }
    
    @Override
    @Transactional
    public List<ProductImageDto> getAllProductImage(String productImageUrl) {
        log.info("BrandDaoImpl.getAllProductImageUrl() invoked with brandName: {}", productImageUrl);
        Criteria criteria = getCurrentSession().createCriteria(ProductImage.class, "brand");

        // Add brandName filter if provided
        if (productImageUrl != null && !productImageUrl.isEmpty()) {
            criteria.add(Restrictions.ilike("productImageUrl", "%" + productImageUrl + "%"));
        }

        List<ProductImage> productImageList = criteria.list();
        return productImageList.stream()
                       .map(productImage -> productImageTransformer.transform(productImage))
                       .collect(Collectors.toList());
    }


}

