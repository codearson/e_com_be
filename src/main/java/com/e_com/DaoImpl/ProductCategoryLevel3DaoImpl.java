package com.e_com.DaoImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.e_com.Dao.ProductCategoryLevel3Dao;
import com.e_com.Domain.ProductCategoryLevel3;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel3Dto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.ProductCategoryLevel3Transformer;
import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductCategoryLevel3DaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date Jun 27, 2025
 * @time 6:26:30 PM
 * @version 1.0
 **/


@Slf4j
@Repository
public class ProductCategoryLevel3DaoImpl extends BaseDaoImpl<ProductCategoryLevel3> implements ProductCategoryLevel3Dao {

    @Autowired
    ProductCategoryLevel3Transformer productCategoryLevel3Transformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public PaginatedResponseDto getAllPageProductCategoryLevel3(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ProductCategoryLevel3DaoImpl.getAllPageProductCategoryLevel3() invoked with pageNumber: {}, pageSize: {}, status: {}", pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<ProductCategoryLevel3> productCategoryLevel3List = null;

        StringBuilder countString = new StringBuilder("SELECT COUNT(*) FROM product_category_level3");
        if (status != null) {
            countString.append(" WHERE is_active = ?");
        }
        int count = status != null
                ? jdbcTemplate.queryForObject(countString.toString(), new Object[]{status}, Integer.class)
                : jdbcTemplate.queryForObject(countString.toString(), Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel3.class, "productCategoryLevel3");
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        productCategoryLevel3List = criteria.list();

        if (productCategoryLevel3List != null && !productCategoryLevel3List.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(productCategoryLevel3List, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(productCategoryLevel3List.stream()
                    .map(productCategoryLevel3Transformer::transform)
                    .collect(Collectors.toList()));
        }
        return paginatedResponseDto;
    }

    @Override
    @Transactional
    public ProductCategoryLevel3Dto save(ProductCategoryLevel3Dto productCategoryLevel3Dto) {
        log.info("ProductCategoryLevel3DaoImpl.save() invoked.");
        ProductCategoryLevel3 productCategoryLevel3 = productCategoryLevel3Transformer.reverseTransform(productCategoryLevel3Dto);
        ProductCategoryLevel3 saveProductCategoryLevel3 = saveOrUpdate(productCategoryLevel3);
        return productCategoryLevel3Transformer.transform(saveProductCategoryLevel3);
    }

    @Override
    @Transactional
    public ProductCategoryLevel3Dto update(ProductCategoryLevel3Dto productCategoryLevel3Dto) {
        log.info("ProductCategoryLevel3DaoImpl.update() invoked.");
        ProductCategoryLevel3 productCategoryLevel3 = productCategoryLevel3Transformer.reverseTransform(productCategoryLevel3Dto);
        ProductCategoryLevel3 updatedProductCategoryLevel3 = saveOrUpdate(productCategoryLevel3);
        return productCategoryLevel3Transformer.transform(updatedProductCategoryLevel3);
    }

    @Override
    @Transactional
    public List<ProductCategoryLevel3Dto> getProductCategoryLevel3ByName(String productCategoryLevel3Name) {
        log.info("ProductCategoryLevel3DaoImpl.getProductCategoryLevel3ByName() invoked");
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<ProductCategoryLevel3> cq = cb.createQuery(ProductCategoryLevel3.class);
        Root<ProductCategoryLevel3> root = cq.from(ProductCategoryLevel3.class);
        cq.select(root).where(cb.equal(root.get("productCategoryLevel3Name"), productCategoryLevel3Name));
        Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel3.class, "ProductCategoryLevel3");
        criteria.add(Restrictions.eq("isActive", true));
        List<ProductCategoryLevel3> productCategoryLevel3List = getCurrentSession().createQuery(cq).getResultList();
        return productCategoryLevel3List.stream()
                .map(productCategoryLevel3Transformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductCategoryLevel3Dto checkProductCategoryLevel3Availability(Integer productCategoryLevel3Id) {
        Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel3.class, "productCategoryLevel3");
        criteria.add(Restrictions.eq("productCategoryLevel3.id", productCategoryLevel3Id));
        ProductCategoryLevel3 productCategoryLevel3 = (ProductCategoryLevel3) criteria.uniqueResult();
        ProductCategoryLevel3Dto productCategoryLevel3Dto = null;
        if (productCategoryLevel3 != null) {
            productCategoryLevel3Dto = productCategoryLevel3Transformer.transform(productCategoryLevel3);
        }
        return productCategoryLevel3Dto;
    }

    @Override
    @Transactional
    public List<ProductCategoryLevel3Dto> getProductCategoryLevel3ById(Integer id) {
        log.info("ProductCategoryLevel3DaoImpl.getProductCategoryLevel3ById() invoked");
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<ProductCategoryLevel3> cq = cb.createQuery(ProductCategoryLevel3.class);
        Root<ProductCategoryLevel3> root = cq.from(ProductCategoryLevel3.class);
        cq.select(root).where(cb.equal(root.get("id"), id));
        Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel3.class, "productCategoryLevel3");
        criteria.add(Restrictions.eq("isActive", true));
        List<ProductCategoryLevel3> productCategoryLevel3List = getCurrentSession().createQuery(cq).getResultList();
        return productCategoryLevel3List.stream()
                .map(productCategoryLevel3Transformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ProductCategoryLevel3Dto> getAllProductCategoryLevel3(String productCategoryLevel3Name) {
        log.info("ProductCategoryLevel3DaoImpl.getAllProductCategoryLevel3() invoked with productCategoryLevel3Name: {}", productCategoryLevel3Name);
        Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel3.class, "productCategoryLevel3");
        if (productCategoryLevel3Name != null && !productCategoryLevel3Name.isEmpty()) {
            criteria.add(Restrictions.ilike("productCategoryLevel3Name", "%" + productCategoryLevel3Name + "%"));
        }
        List<ProductCategoryLevel3> productCategoryLevel3List = criteria.list();
        return productCategoryLevel3List.stream()
                .map(productCategoryLevel3Transformer::transform)
                .collect(Collectors.toList());
    }
}
