package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.Product;
import com.e_com.Domain.ShippingPreferences;
import com.e_com.Domain.User;
import com.e_com.Dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 18:33:28
 * @version 1.0
 **/

@Slf4j
@Component
public class ProductTransformer implements BaseTransformer<Product, ProductDto> {


    @Autowired
    private BrandTransformer brandTransformer;

    @Autowired
    private ConditionsTransformer conditionsTransformer;

    @Autowired
    private StatusTransformer statusTransformer;

    @Autowired
    private ProductCategoryTransformer productCategoryTransformer;

    @Autowired
    private UserTransfomer userTransfomer;
    
    @Autowired
    private ShippingPreferencesTransformer shippingPreferencesTransformer;

    @Override
    public ProductDto transform(Product product) {
        log.debug("Transforming Product to ProductDto for id: {}", product != null ? product.getId() : null);
        ProductDto productDto = null;
        if (product != null) {
            productDto = new ProductDto();
            productDto.setId(product.getId());
            if (product.getProductCategory() != null) {
                productDto.setProductCategoryDto(productCategoryTransformer.transform(product.getProductCategory()));
                // Build category chain
                java.util.LinkedList<com.e_com.Dto.ProductCategoryDto> chain = new java.util.LinkedList<>();
                com.e_com.Domain.ProductCategory cat = product.getProductCategory();
                while (cat != null) {
                    chain.addFirst(productCategoryTransformer.transform(cat, false));
                    cat = cat.getParent();
                }
                productDto.setCategoryChain(chain);
            }
            if (product.getBrand() != null) {
                productDto.setBrandDto(brandTransformer.transform(product.getBrand()));
            }
            if (product.getConditions() != null) {
                productDto.setConditionsDto(conditionsTransformer.transform(product.getConditions()));
            }
            if (product.getStatus() != null) {
                productDto.setStatusDto(statusTransformer.transform(product.getStatus()));
            }
            if (product.getProductCategory() != null) {
                productDto.setProductCategoryDto(productCategoryTransformer.transform(product.getProductCategory()));
            }
            if (product.getShippingPreferences() != null) {
                productDto.setShippingPreferencesDto(shippingPreferencesTransformer.transform(product.getShippingPreferences()));
            }
            productDto.setTitle(product.getTitle());
            productDto.setDescription(product.getDescription());
            productDto.setSize(product.getSize());
            productDto.setColor(product.getColor());
            productDto.setPrice(product.getPrice());
            productDto.setQuentity(product.getQuentity());
            productDto.setCreatedAt(product.getCreatedAt());
            productDto.setUpdatedAt(product.getUpdatedAt());
            productDto.setImageUrl(product.getImageUrl());
            productDto.setIsActive(product.getIsActive());
            if (product.getUser() != null) {
				productDto.setUserDto(userTransfomer.transform(product.getUser()));
			}
        }
        return productDto;
    }

    @Override
    public Product reverseTransform(ProductDto productDto) {
        log.debug("Reverse transforming ProductDto to Product for id: {}", productDto != null ? productDto.getId() : null);
        Product product = null;
        if (productDto != null) {
            product = new Product();
            product.setId(productDto.getId());
            if (productDto.getProductCategoryDto() != null) {
                product.setProductCategory(productCategoryTransformer.reverseTransform(productDto.getProductCategoryDto()));
            }
            if (productDto.getBrandDto() != null) {
                product.setBrand(brandTransformer.reverseTransform(productDto.getBrandDto()));
            }
            if (productDto.getConditionsDto() != null) {
                product.setConditions(conditionsTransformer.reverseTransform(productDto.getConditionsDto()));
            }
            if (productDto.getStatusDto() != null) {
                product.setStatus(statusTransformer.reverseTransform(productDto.getStatusDto()));
            }
            if (productDto.getShippingPreferencesDto() != null) {
                product.setShippingPreferences(shippingPreferencesTransformer.reverseTransform(productDto.getShippingPreferencesDto()));
            }
            product.setTitle(productDto.getTitle());
            product.setDescription(productDto.getDescription());
            product.setSize(productDto.getSize());
            product.setColor(productDto.getColor());
            product.setPrice(productDto.getPrice());
            product.setQuentity(productDto.getQuentity());
            product.setCreatedAt(productDto.getCreatedAt());
            product.setUpdatedAt(productDto.getUpdatedAt());
            product.setImageUrl(productDto.getImageUrl());
            product.setIsActive(productDto.getIsActive());
            if (productDto.getUserDto() != null) {
				product.setUser(userTransfomer.reverseTransform(productDto.getUserDto()));
			}
        }
        return product;
    }
}