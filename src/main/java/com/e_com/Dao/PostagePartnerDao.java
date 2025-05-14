package com.e_com.Dao;

import java.util.List;
import java.util.Map;

import com.e_com.Domain.PostagePartner;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.PostagePartnerDto;

/**
 * Title: PostagePartnerDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 14, 2025
 * @version 1.0
 **/

public interface PostagePartnerDao extends BaseDao<PostagePartner> {

	PostagePartnerDto save(PostagePartnerDto postagePartnerDto);
    
	PostagePartnerDto update(PostagePartnerDto postagePartnerDto);
    
	PostagePartnerDto checkAvailability(Integer postagePartnerId);
    
    PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    List<PostagePartnerDto> getAll(String partnerName);
	
}
