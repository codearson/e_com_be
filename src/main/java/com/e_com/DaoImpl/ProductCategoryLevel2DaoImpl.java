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

import com.e_com.Dao.ProductCategoryLevel2Dao;
import com.e_com.Domain.ProductCategoryLevel2;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel2Dto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.ProductCategoryLevel2Transformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductSubCategoryDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 15, 2025
 * @time 8:20:03 PM
 * @version 1.0
 **/

@Slf4j
@Repository
public class ProductCategoryLevel2DaoImpl extends BaseDaoImpl<ProductCategoryLevel2> implements ProductCategoryLevel2Dao {


	@Autowired
	ProductCategoryLevel2Transformer productCategoryLevel2Transformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public PaginatedResponseDto getAllPageProductCategoryLevel2(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
		log.info("ProductCategoryLevel2DaoImpl.getAllPageProductCategoryLevel2() invoked with pageNumber: {}, pageSize: {}, status: {}", 
				 pageNumber, pageSize, status);
		PaginatedResponseDto paginatedResponseDto = null;
		List<ProductCategoryLevel2> productCategoryLevel2List = null;

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

		Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel2.class, "productCategoryLevel2");

		// Add status filter if provided
		if (status != null) {
			criteria.add(Restrictions.eq("isActive", status));
		}

		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		productCategoryLevel2List = criteria.list();

		if (productCategoryLevel2List != null && !productCategoryLevel2List.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(productCategoryLevel2List, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(productCategoryLevel2List.stream()
				.map(productCategoryLevel2Transformer::transform)
				.collect(Collectors.toList()));
		}

		return paginatedResponseDto;
	}

	@Override
	@Transactional
	public ProductCategoryLevel2Dto save(ProductCategoryLevel2Dto productCategoryLevel2Dto) {
		log.info("ProductCategoryLevel2DaoImpl.save() invoked.");
		ProductCategoryLevel2 productCategoryLevel2 = productCategoryLevel2Transformer.reverseTransform(productCategoryLevel2Dto);
		ProductCategoryLevel2 saveProductCategoryLevel2 = saveOrUpdate(productCategoryLevel2);
		return productCategoryLevel2Transformer.transform(saveProductCategoryLevel2);
	}

	@Override
	@Transactional
	public ProductCategoryLevel2Dto update(ProductCategoryLevel2Dto productCategoryLevel2Dto) {
	    log.info("ProductCategoryLevel2DaoImpl.update() invoked.");
	    ProductCategoryLevel2 productCategoryLevel2 = productCategoryLevel2Transformer.reverseTransform(productCategoryLevel2Dto);
	    ProductCategoryLevel2 updatedProductCategoryLevel2 = saveOrUpdate(productCategoryLevel2);
	    return productCategoryLevel2Transformer.transform(updatedProductCategoryLevel2);
	}
		
	@Override
	@Transactional
	public List<ProductCategoryLevel2Dto> getProductCategoryLevel2ByName(String productCategoryLevel2Name) {
	    log.info("ProductCategoryLevel2DaoImpl.getProductCategoryLevel2ByName() invoked");

	    CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<ProductCategoryLevel2> cq = cb.createQuery(ProductCategoryLevel2.class);
	    Root<ProductCategoryLevel2> root = cq.from(ProductCategoryLevel2.class);

	    cq.select(root).where(cb.equal(root.get("productCategoryLevel2Name"), productCategoryLevel2Name));
	    
	    Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel2.class, "ProductCategoryLevel2");
	 	criteria.add(Restrictions.eq("isActive", true));

	    List<ProductCategoryLevel2> productCategoryLevel2List = getCurrentSession().createQuery(cq).getResultList();

	    return productCategoryLevel2List.stream()
	        .map(productCategoryLevel2Transformer::transform)
	        .collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public ProductCategoryLevel2Dto checkProductCategoryLevel2Availability(Integer productCategoryLevel2Id) {
		Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel2.class, "productCategoryLevel2");
		criteria.add(Restrictions.eq("productCategoryLevel2.id", productCategoryLevel2Id));
		ProductCategoryLevel2 productCategoryLevel2 = (ProductCategoryLevel2) criteria.uniqueResult();
		ProductCategoryLevel2Dto productCategoryLevel2Dto = null;
		if (productCategoryLevel2 != null) {
			productCategoryLevel2Dto = productCategoryLevel2Transformer.transform(productCategoryLevel2);
		}
		return productCategoryLevel2Dto;
	}
	
	@Override
	@Transactional
	public List<ProductCategoryLevel2Dto> getProductCategoryLevel2ById(Integer id) {
	    log.info("ProductCategoryLevel2DaoImpl.getProductCategoryLevel2ById() invoked");

	    CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<ProductCategoryLevel2> cq = cb.createQuery(ProductCategoryLevel2.class);
	    Root<ProductCategoryLevel2> root = cq.from(ProductCategoryLevel2.class);

	    cq.select(root).where(cb.equal(root.get("id"), id));
	    
	    Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel2.class, "productCategoryLevel2");
	 	criteria.add(Restrictions.eq("isActive", true));

	    List<ProductCategoryLevel2> productCategoryLevel2List = getCurrentSession().createQuery(cq).getResultList();

	    return productCategoryLevel2List.stream()
	        .map(productCategoryLevel2Transformer::transform)
	        .collect(Collectors.toList());
	}
	
	 @Override
	 @Transactional
	 public List<ProductCategoryLevel2Dto> getAllProductCategoryLevel2(String productCategoryLevel2Name) {
	        log.info("ProductCategoryLevel2DaoImpl.getAllProductCategoryLevel2() invoked with productCategoryLevel2Name: {}", productCategoryLevel2Name);
	        Criteria criteria = getCurrentSession().createCriteria(ProductCategoryLevel2.class, "productCategoryLevel2");

	        if (productCategoryLevel2Name != null && !productCategoryLevel2Name.isEmpty()) {
	            criteria.add(Restrictions.ilike("productCategoryLevel2Name", "%" + productCategoryLevel2Name + "%"));
	        }

	        List<ProductCategoryLevel2> productCategoryLevel2List = criteria.list();
	        return productCategoryLevel2List.stream()
	                       .map(productCategoryLevel2 -> productCategoryLevel2Transformer.transform(productCategoryLevel2))
	                       .collect(Collectors.toList());
	    }
}
