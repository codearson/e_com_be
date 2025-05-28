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

import com.e_com.Dao.BranchDao;
import com.e_com.Domain.Branch;
import com.e_com.Dto.BranchDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.BranchTransformer;
import com.e_com.DaoImpl.BranchDaoImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BranchDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 13, 2025
 * @time 11:43:31 PM
 * @version 1.0
 **/

@Slf4j
@Repository
public class BranchDaoImpl extends BaseDaoImpl<Branch> implements BranchDao{

	@Autowired
	BranchTransformer branchTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public PaginatedResponseDto getAllPageBranch(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
		log.info("BranchDaoImpl.getAllPageBranch() invoked with pageNumber: {}, pageSize: {}, status: {}", 
				 pageNumber, pageSize, status);
		PaginatedResponseDto paginatedResponseDto = null;
		List<Branch> branchList = null;

		// Build the count query with status filter
		StringBuilder countString = new StringBuilder("SELECT COUNT(*) FROM branch");
		if (status != null) {
			countString.append(" WHERE is_active = ?");
		}
		int count = status != null 
			? jdbcTemplate.queryForObject(countString.toString(), new Object[]{status}, Integer.class)
			: jdbcTemplate.queryForObject(countString.toString(), Integer.class);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(Branch.class, "branch");

		// Add status filter if provided
		if (status != null) {
			criteria.add(Restrictions.eq("isActive", status));
		}

		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		branchList = criteria.list();

		if (branchList != null && !branchList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(branchList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(branchList.stream()
				.map(branchTransformer::transform)
				.collect(Collectors.toList()));
		}

		return paginatedResponseDto;
	}

	@Override
	@Transactional
	public BranchDto save(BranchDto branchDto) {
		log.info("BranchDaoImpl.save() invoked.");
		Branch branch = branchTransformer.reverseTransform(branchDto);
		Branch saveBranch = saveOrUpdate(branch);
		return branchTransformer.transform(saveBranch);
	}

	@Override
	@Transactional
	public BranchDto update(BranchDto branchDto) {
	    log.info("BranchDaoImpl.update() invoked.");
	    Branch branch = branchTransformer.reverseTransform(branchDto);
	    Branch updatedBranch = saveOrUpdate(branch);
	    return branchTransformer.transform(updatedBranch);
	}
		
	@Override
	@Transactional
	public List<BranchDto> getBranchByName(String branchName) {
	    log.info("BranchDaoImpl.getBranchByName() invoked");

	    CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<Branch> cq = cb.createQuery(Branch.class);
	    Root<Branch> root = cq.from(Branch.class);

	    cq.select(root).where(cb.equal(root.get("branchName"), branchName));
	    
	    Criteria criteria = getCurrentSession().createCriteria(Branch.class, "branch");
	 	criteria.add(Restrictions.eq("isActive", true));

	    List<Branch> branchList = getCurrentSession().createQuery(cq).getResultList();

	    return branchList.stream()
	        .map(branchTransformer::transform)
	        .collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public BranchDto checkBranchAvailability(Integer branchId) {
		Criteria criteria = getCurrentSession().createCriteria(Branch.class, "branch");
		criteria.add(Restrictions.eq("branch.id", branchId));
		Branch branch = (Branch) criteria.uniqueResult();
		BranchDto branchDto = null;
		if (branch != null) {
			branchDto = branchTransformer.transform(branch);
		}
		return branchDto;
	}
	
	@Override
	@Transactional
	public List<BranchDto> getBranchById(Integer id) {
	    log.info("BranchDaoImpl.getBranchById() invoked");

	    CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<Branch> cq = cb.createQuery(Branch.class);
	    Root<Branch> root = cq.from(Branch.class);

	    cq.select(root).where(cb.equal(root.get("id"), id));
	    
	    Criteria criteria = getCurrentSession().createCriteria(Branch.class, "branch");
	 	criteria.add(Restrictions.eq("isActive", true));

	    List<Branch> branchList = getCurrentSession().createQuery(cq).getResultList();

	    return branchList.stream()
	        .map(branchTransformer::transform)
	        .collect(Collectors.toList());
	}
	
	@Override
    @Transactional
    public List<BranchDto> getAllBySearch(String branchName) {
        log.info("BranchDaoImpl.getAllBySearch() invoked with branchName: {}", branchName);
        Criteria criteria = getCurrentSession().createCriteria(Branch.class, "branch");

        // Add branchName filter if provided and not empty
        if (branchName != null && !branchName.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("branchName", "%" + branchName.trim() + "%"));
        }

        List<Branch> branchList = criteria.list();
        return branchList.stream()
                        .map(branchTransformer::transform)
                        .collect(Collectors.toList());
    }
}
