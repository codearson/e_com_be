package com.e_com.Service;

import java.util.Map;

import com.e_com.Dto.PostagePartnerDto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: PostagePartnerService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 14, 2025
 * @version 1.0
 **/

public interface PostagePartnerService {

	ResponseDto save(PostagePartnerDto postagePartnerDto);
    
    ResponseDto update(PostagePartnerDto postagePartnerDto);
    
    ResponseDto updateStatus(Integer postagePartnerId, Boolean status);
    
    ResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);	
    
    ResponseDto getAll(String partnerName);
	
}
