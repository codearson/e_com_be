package com.e_com.Transformer;

import com.e_com.Domain.Inquiry;
import com.e_com.Dto.InquiryDto;
import org.springframework.stereotype.Component;

@Component
public class InquiryTransformer implements BaseTransformer<Inquiry, InquiryDto> {

    @Override
    public InquiryDto transform(Inquiry inquiry) {
        InquiryDto inquiryDto = new InquiryDto();
        inquiryDto.setId(inquiry.getId());
        if (inquiry.getUser() != null) {
            inquiryDto.setUserId(inquiry.getUser().getId());
        }
        inquiryDto.setUserName(inquiry.getUserName());
        inquiryDto.setUserEmail(inquiry.getUserEmail());
        inquiryDto.setUserRole(inquiry.getUserRole());
        inquiryDto.setSubject(inquiry.getSubject());
        inquiryDto.setMessage(inquiry.getMessage());
        inquiryDto.setStatus(inquiry.getStatus());
        inquiryDto.setAdminReply(inquiry.getAdminReply());
        inquiryDto.setRepliedAt(inquiry.getRepliedAt());
        inquiryDto.setCreatedAt(inquiry.getCreatedAt());
        inquiryDto.setUpdatedAt(inquiry.getUpdatedAt());
        inquiryDto.setIsActive(inquiry.getIsActive());
        return inquiryDto;
    }

    @Override
    public Inquiry reverseTransform(InquiryDto inquiryDto) {
        Inquiry inquiry = new Inquiry();
        inquiry.setId(inquiryDto.getId());
        // Note: Setting the user from userId would require a database lookup, 
        // which is typically handled in the service layer.
        inquiry.setUserName(inquiryDto.getUserName());
        inquiry.setUserEmail(inquiryDto.getUserEmail());
        inquiry.setUserRole(inquiryDto.getUserRole());
        inquiry.setSubject(inquiryDto.getSubject());
        inquiry.setMessage(inquiryDto.getMessage());
        inquiry.setStatus(inquiryDto.getStatus());
        inquiry.setAdminReply(inquiryDto.getAdminReply());
        inquiry.setRepliedAt(inquiryDto.getRepliedAt());
        inquiry.setCreatedAt(inquiryDto.getCreatedAt());
        inquiry.setUpdatedAt(inquiryDto.getUpdatedAt());
        inquiry.setIsActive(inquiryDto.getIsActive());
        return inquiry;    }
}