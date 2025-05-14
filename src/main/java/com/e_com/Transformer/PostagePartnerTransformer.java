package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.PostagePartner;
import com.e_com.Dto.PostagePartnerDto;

/**
 * Title: PostagePartnerTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 14, 2025
 * @version 1.0
 **/

@Component
public class PostagePartnerTransformer implements BaseTransformer<PostagePartner, PostagePartnerDto> {
	
	@Autowired
	UserTransfomer userTransformer;

	@Override
    public PostagePartnerDto transform(PostagePartner postagePartner) {
		PostagePartnerDto postagePartnerDto = null;
        if (postagePartner != null) {
        	postagePartnerDto = new PostagePartnerDto();
        	postagePartnerDto.setId(postagePartner.getId());
        	postagePartnerDto.setPartnerName(postagePartner.getPartnerName());
        	if (postagePartner.getUser() != null) {
        		postagePartnerDto.setUserDto(userTransformer.transform(postagePartner.getUser()));
			}
        	postagePartnerDto.setIsActive(postagePartner.getIsActive());
        }
        return postagePartnerDto;
    }

    @Override
    public PostagePartner reverseTransform(PostagePartnerDto postagePartnerDto) {
    	PostagePartner postagePartner = null;
        if (postagePartnerDto != null) {
        	postagePartner = new PostagePartner();
        	postagePartner.setId(postagePartnerDto.getId());
        	postagePartner.setPartnerName(postagePartnerDto.getPartnerName());
        	if (postagePartnerDto.getUserDto() != null) {
        		postagePartner.setUser(userTransformer.reverseTransform(postagePartnerDto.getUserDto()));
			}
        	postagePartner.setIsActive(postagePartnerDto.getIsActive());
        }
        return postagePartner;
    }
	
}
