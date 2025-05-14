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

import com.e_com.Dao.PostagePartnerDao;
import com.e_com.Domain.PostagePartner;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.PostagePartnerDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.PostagePartnerTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: PostagePartnerDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 14, 2025
 * @version 1.0
 **/

@Slf4j
@Repository
public class PostagePartnerDaoImpl extends BaseDaoImpl<PostagePartner> implements PostagePartnerDao {

	@Autowired
	PostagePartnerTransformer postagePartnerTransformer;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public PostagePartnerDto save(PostagePartnerDto postagePartnerDto) {
        log.info("PostagePartnerDaoImpl.save() invoked.");
        PostagePartner postagePartner = postagePartnerTransformer.reverseTransform(postagePartnerDto);
        PostagePartner savedPostagePartner = saveOrUpdate(postagePartner);
        return postagePartnerTransformer.transform(savedPostagePartner);
    }
    
    @Transactional
    public PostagePartnerDto update(PostagePartnerDto postagePartnerDto) {
        log.info("PostagePartnerDaoImpl.update() invoked.");
        PostagePartner postagePartner = postagePartnerTransformer.reverseTransform(postagePartnerDto);
        PostagePartner updatedPostagePartner = saveOrUpdate(postagePartner);
        return postagePartnerTransformer.transform(updatedPostagePartner);
    }
    
    @Transactional
    public PostagePartnerDto checkAvailability(Integer postagePartnerId) {
        log.info("PostagePartnerDaoImpl.checkAvailability() invoked with postagePartnerId: {}", postagePartnerId);
        Criteria criteria = getCurrentSession().createCriteria(PostagePartner.class, "postagePartner");
        criteria.add(Restrictions.eq("postagePartner.id", postagePartnerId));
        PostagePartner postagePartner = (PostagePartner) criteria.uniqueResult();
        PostagePartnerDto postagePartnerDto = null;
        if (postagePartner != null) {
        	postagePartnerDto = postagePartnerTransformer.transform(postagePartner);
        }
        return postagePartnerDto;
    }
    
    @Transactional
    public PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("PostagePartnerDaoImpl.getAllPage() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<PostagePartner> postagePartnerList = null;
        int recordCount = 0;

        String countString = "SELECT COUNT(*) FROM postage_partner";
        if (status != null) {
            countString += " WHERE is_active = " + (status ? "true" : "false");
        }
        int count = jdbcTemplate.queryForObject(countString, Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(PostagePartner.class, "postagePartner");

        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        postagePartnerList = criteria.list();

        if (postagePartnerList != null && !postagePartnerList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(postagePartnerList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(postagePartnerList.stream().map(postagePartner -> {
                return postagePartnerTransformer.transform(postagePartner);
            }).collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }
    
    @Override
    @Transactional
    public List<PostagePartnerDto> getAll(String partnerName) {
        log.info("PostagePartnerDaoImpl.getAll() invoked with partnerName: {}", partnerName);
        Criteria criteria = getCurrentSession().createCriteria(PostagePartner.class, "postagePartner");

        // Add brandName filter if provided
        if (partnerName != null && !partnerName.isEmpty()) {
            criteria.add(Restrictions.ilike("partnerName", "%" + partnerName + "%"));
        }

        List<PostagePartner> postagePartnerList = criteria.list();
        return postagePartnerList.stream()
                       .map(postagePartner -> postagePartnerTransformer.transform(postagePartner))
                       .collect(Collectors.toList());
    }
	
}
