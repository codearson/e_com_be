package com.e_com.DaoImpl;

import com.e_com.Dao.ProductCategoryDao;
import com.e_com.Domain.ProductCategory;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryDto;
import com.e_com.Transformer.ProductCategoryTransformer;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ProductCategoryDaoImpl implements ProductCategoryDao {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProductCategoryTransformer transformer;

    @Override
    @Transactional
    public ProductCategoryDto saveCategory(ProductCategoryDto dto) {
        Session session = entityManager.unwrap(Session.class);
        ProductCategory category = transformer.reverseTransform(dto);
        if (dto.getParentId() != null) {
            ProductCategory parent = session.get(ProductCategory.class, dto.getParentId());
            category.setParent(parent);
        } else {
            category.setParent(null);
        }
        session.save(category);
        return transformer.transform(category);
    }

    @Override
    @Transactional
    public ProductCategoryDto updateCategory(ProductCategoryDto dto) {
        Session session = entityManager.unwrap(Session.class);
        ProductCategory category = session.get(ProductCategory.class, dto.getId());
        if (category == null) return null;
        category.setName(dto.getName());
        category.setLevel(dto.getLevel());
        category.setIsActive(dto.getIsActive());
        if (dto.getParentId() != null) {
            if (dto.getParentId().equals(dto.getId())) {
                throw new IllegalArgumentException("A category cannot be its own parent.");
            }
            ProductCategory parent = session.get(ProductCategory.class, dto.getParentId());
            category.setParent(parent);
        } else {
            category.setParent(null);
        }
        session.update(category);
        return transformer.transform(category, false);
    }

    @Override
    @Transactional
    public ProductCategoryDto getCategoryById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        ProductCategory category = session.get(ProductCategory.class, id);
        return transformer.transform(category, false);
    }

    @Override
    @Transactional
    public List<ProductCategoryDto> getAllCategories() {
        Session session = entityManager.unwrap(Session.class);
        List<ProductCategory> categories = session.createQuery("FROM ProductCategory", ProductCategory.class).getResultList();
        return categories.stream().map(transformer::transform).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PaginatedResponseDto getAllCategoriesPage(int pageNumber, int pageSize, Integer level, Long parentId) {
        Session session = entityManager.unwrap(Session.class);
        StringBuilder hql = new StringBuilder("FROM ProductCategory c WHERE 1=1");
        if (level != null) hql.append(" AND c.level = :level");
        if (parentId != null) hql.append(" AND c.parent.id = :parentId");
        Query<ProductCategory> query = session.createQuery(hql.toString(), ProductCategory.class);
        if (level != null) query.setParameter("level", level);
        if (parentId != null) query.setParameter("parentId", parentId);
        int total = query.getResultList().size();
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<ProductCategory> paged = query.getResultList();
        List<ProductCategoryDto> dtos = paged.stream().map(transformer::transform).collect(Collectors.toList());
        PaginatedResponseDto resp = new PaginatedResponseDto();
        resp.setPayload(dtos);
        resp.setPageNumber(pageNumber);
        resp.setPageSize(pageSize);
        resp.setTotalRecords(total);
        return resp;
    }

    @Override
    @Transactional
    public List<ProductCategoryDto> getCategoryTree() {
        Session session = entityManager.unwrap(Session.class);
        List<ProductCategory> all = session.createQuery("FROM ProductCategory", ProductCategory.class).getResultList();

        // Map of id -> DTO
        Map<Long, ProductCategoryDto> dtoMap = new HashMap<>();
        for (ProductCategory cat : all) {
            dtoMap.put(cat.getId(), transformer.transform(cat, false)); // no children yet
        }

        // Build tree: assign children
        for (ProductCategory cat : all) {
            if (cat.getParent() != null) {
                ProductCategoryDto parentDto = dtoMap.get(cat.getParent().getId());
                if (parentDto.getChildren() == null) parentDto.setChildren(new ArrayList<>());
                parentDto.getChildren().add(dtoMap.get(cat.getId()));
            }
        }

        // Collect roots (parent == null)
        List<ProductCategoryDto> roots = dtoMap.values().stream()
            .filter(dto -> dto.getParentId() == null)
            .collect(Collectors.toList());

        return roots;
    }

    @Override
    @Transactional
    public boolean updateStatus(Long id, Boolean isActive) {
        Session session = entityManager.unwrap(Session.class);
        ProductCategory category = session.get(ProductCategory.class, id);
        if (category == null) return false;
        category.setIsActive(isActive);
        session.update(category);
        return true;
    }

    @Override
    @Transactional
    public PaginatedResponseDto getAllCategoriesPageBySearch(int pageNumber, int pageSize, String search, Integer level, Long parentId) {
        Session session = entityManager.unwrap(Session.class);
        javax.persistence.criteria.CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery<ProductCategory> cq = cb.createQuery(ProductCategory.class);
        javax.persistence.criteria.Root<ProductCategory> root = cq.from(ProductCategory.class);
        List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
        if (search != null && !search.trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + search.toLowerCase() + "%"));
        }
        if (level != null) {
            predicates.add(cb.equal(root.get("level"), level));
        }
        if (parentId != null) {
            predicates.add(cb.equal(root.get("parent").get("id"), parentId));
        }
        cq.where(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        cq.orderBy(cb.asc(root.get("id")));
        javax.persistence.TypedQuery<ProductCategory> query = entityManager.createQuery(cq);
        int total = query.getResultList().size();
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<ProductCategory> paged = query.getResultList();
        List<ProductCategoryDto> dtos = paged.stream().map(transformer::transform).collect(Collectors.toList());
        PaginatedResponseDto resp = new PaginatedResponseDto();
        resp.setPayload(dtos);
        resp.setPageNumber(pageNumber);
        resp.setPageSize(pageSize);
        resp.setTotalRecords(total);
        return resp;
    }
} 