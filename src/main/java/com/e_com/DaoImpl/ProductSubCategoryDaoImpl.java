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

import com.e_com.Dao.ProductSubCategoryDao;
import com.e_com.Domain.ProductSubCategory;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductSubCategoryDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.ProductSubCategoryTransformer;

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
public class ProductSubCategoryDaoImpl extends BaseDaoImpl<ProductSubCategory> implements ProductSubCategoryDao {


	@Autowired
	ProductSubCategoryTransformer productSubCategoryTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("ProductSubCategoryDaoImpl.getAll()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<ProductSubCategory> allProductSubCategoryList = null;
		int recordCount = 0;
		String countString = "SELECT COUNT(*) FROM productSubCategory";
		if (searchParams != null && searchParams.containsKey("status")) {
			Boolean status = Boolean.parseBoolean(searchParams.get("status"));
			countString += " WHERE is_active = " + (status ? "true" : "false");
		}
		int count = jdbcTemplate.queryForObject(countString, Integer.class);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(ProductSubCategory.class, "productSubCategory");
		
		// Check if status is provided in searchParams
		if (searchParams != null && searchParams.containsKey("status")) {
			Boolean status = Boolean.parseBoolean(searchParams.get("status"));
			criteria.add(Restrictions.eq("isActive", status));
		} else {
			criteria.add(Restrictions.eq("isActive", true));
		}
		
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allProductSubCategoryList = criteria.list();
		recordCount = allProductSubCategoryList.size();
		if (allProductSubCategoryList != null && !allProductSubCategoryList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allProductSubCategoryList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allProductSubCategoryList.stream().map(Invoice -> {
				return productSubCategoryTransformer.transform(Invoice);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}

	@Override
	@Transactional
	public ProductSubCategoryDto save(ProductSubCategoryDto productSubCategoryDto) {
		log.info("ProductSubCategoryDaoImpl.save() invoked.");
		ProductSubCategory productSubCategory = productSubCategoryTransformer.reverseTransform(productSubCategoryDto);
		ProductSubCategory saveProductSubCategory = saveOrUpdate(productSubCategory);
		return productSubCategoryTransformer.transform(saveProductSubCategory);
	}

	@Override
	@Transactional
	public ProductSubCategoryDto update(ProductSubCategoryDto productSubCategoryDto) {
	    log.info("ProductSubCategoryDaoImpl.update() invoked.");
	    ProductSubCategory productSubCategory = productSubCategoryTransformer.reverseTransform(productSubCategoryDto);
	    ProductSubCategory updatedProductSubCategory = saveOrUpdate(productSubCategory);
	    return productSubCategoryTransformer.transform(updatedProductSubCategory);
	}
		
	@Override
	@Transactional
	public List<ProductSubCategoryDto> getProductSubCategoryByName(String productSubCategoryName) {
	    log.info("ProductSubCategoryDaoImpl.getProductSubCategoryByName() invoked");

	    CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<ProductSubCategory> cq = cb.createQuery(ProductSubCategory.class);
	    Root<ProductSubCategory> root = cq.from(ProductSubCategory.class);

	    cq.select(root).where(cb.equal(root.get("productSubCategoryName"), productSubCategoryName));
	    
	    Criteria criteria = getCurrentSession().createCriteria(ProductSubCategory.class, "ProductSubCategory");
	 	criteria.add(Restrictions.eq("isActive", true));

	    List<ProductSubCategory> productSubCategoryList = getCurrentSession().createQuery(cq).getResultList();

	    return productSubCategoryList.stream()
	        .map(productSubCategoryTransformer::transform)
	        .collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public ProductSubCategoryDto checkProductSubCategoryAvailability(Integer productSubCategoryId) {
		Criteria criteria = getCurrentSession().createCriteria(ProductSubCategory.class, "productSubCategory");
		criteria.add(Restrictions.eq("productSubCategory.id", productSubCategoryId));
		ProductSubCategory productSubCategory = (ProductSubCategory) criteria.uniqueResult();
		ProductSubCategoryDto productSubCategoryDto = null;
		if (productSubCategory != null) {
			productSubCategoryDto = productSubCategoryTransformer.transform(productSubCategory);
		}
		return productSubCategoryDto;
	}
	
	@Override
	@Transactional
	public List<ProductSubCategoryDto> getProductSubCategoryById(Integer id) {
	    log.info("ProductSubCategoryDaoImpl.getProductSubCategoryById() invoked");

	    CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<ProductSubCategory> cq = cb.createQuery(ProductSubCategory.class);
	    Root<ProductSubCategory> root = cq.from(ProductSubCategory.class);

	    cq.select(root).where(cb.equal(root.get("id"), id));
	    
	    Criteria criteria = getCurrentSession().createCriteria(ProductSubCategory.class, "productSubCategory");
	 	criteria.add(Restrictions.eq("isActive", true));

	    List<ProductSubCategory> productSubCategoryList = getCurrentSession().createQuery(cq).getResultList();

	    return productSubCategoryList.stream()
	        .map(productSubCategoryTransformer::transform)
	        .collect(Collectors.toList());
	}
	
	 @Override
	 @Transactional
	 public List<ProductSubCategoryDto> getAllProductSubCategory(String productSubCategoryName) {
	        log.info("ProductSubCategoryDaoImpl.getAllProductSubCategory() invoked with productSubCategoryName: {}", productSubCategoryName);
	        Criteria criteria = getCurrentSession().createCriteria(ProductSubCategory.class, "productSubCategory");

	        if (productSubCategoryName != null && !productSubCategoryName.isEmpty()) {
	            criteria.add(Restrictions.ilike("productSubCategoryName", "%" + productSubCategoryName + "%"));
	        }

	        List<ProductSubCategory> productSubCategoryList = criteria.list();
	        return productSubCategoryList.stream()
	                       .map(productSubCategory -> productSubCategoryTransformer.transform(productSubCategory))
	                       .collect(Collectors.toList());
	    }
}
