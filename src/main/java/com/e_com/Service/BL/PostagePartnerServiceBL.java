package com.e_com.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.PostagePartnerDao;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.PostagePartnerDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: PostagePartnerServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 14, 2025
 * @version 1.0
 **/

@Slf4j
@Service
public class PostagePartnerServiceBL {

	@Autowired
    private PostagePartnerDao postagePartnerDao;
	
	public PostagePartnerDto save(PostagePartnerDto postagePartnerDto) {
        log.info("PostagePartnerServiceBL.save() invoked.");
        return postagePartnerDao.save(postagePartnerDto);
    }
    
    public PostagePartnerDto update(PostagePartnerDto postagePartnerDto) {
        log.info("PostagePartnerServiceBL.update() invoked.");
        return postagePartnerDao.update(postagePartnerDto);
    }
    
    public PostagePartnerDto updateStatus(Integer postagePartnerId, Boolean status) {
        log.info("PostagePartnerServiceBL.updateStatus() invoked with postagePartnerId: {}, status: {}", postagePartnerId, status);
        PostagePartnerDto postagePartnerDto = postagePartnerDao.checkAvailability(postagePartnerId);
        if (postagePartnerDto != null) {
        	postagePartnerDto.setIsActive(status);
            return postagePartnerDao.update(postagePartnerDto);
        } else {
            return null;
        }
    }
    
    public PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("PostagePartnerServiceBL.getAllPage() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return postagePartnerDao.getAllPage(pageNumber, pageSize, status, searchParameters);
    }
    
    public List<PostagePartnerDto> getAll(String partnerName) {
        log.info("PostagePartnerServiceBL.getAll() invoked with brandName: {}", partnerName);
        return postagePartnerDao.getAll(partnerName);
    }
	
}
