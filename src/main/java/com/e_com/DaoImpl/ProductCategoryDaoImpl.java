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

import com.e_com.Dao.ProductCategoryDao;
import com.e_com.Domain.ProductCategory;
import com.e_com.Dto.ProductCategoryDto;
import com.e_com.Transformer.ProductCategoryTransformer;

import lombok.extern.slf4j.Slf4j;


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
        ProductCategory productCategory = productCategoryTransformer.reverseTransform(productCategoryDto);
        ProductCategory savedProductCategory = saveOrUpdate(productCategory);
        return productCategoryTransformer.transform(savedProductCategory);
    }
}