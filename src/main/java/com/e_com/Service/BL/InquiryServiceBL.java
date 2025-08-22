package com.e_com.Service.BL;

import com.e_com.Dao.InquiryDao;
import com.e_com.Dao.UserDao;
import com.e_com.Domain.Inquiry;
import com.e_com.Domain.User;
import com.e_com.Dto.InquiryDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Transformer.InquiryTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class InquiryServiceBL {

    @Autowired
    private InquiryDao inquiryDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    private InquiryTransformer inquiryTransformer;

    @Transactional
    public InquiryDto createInquiry(InquiryDto inquiryDto) {
        Inquiry inquiry = inquiryTransformer.reverseTransform(inquiryDto);
        inquiry.setCreatedAt(LocalDateTime.now());
        inquiry.setUpdatedAt(LocalDateTime.now());
        inquiry.setStatus("pending");
        inquiry.setIsActive(true);
        User user = userDao.findUserById(inquiryDto.getUserId());
        inquiry.setUser(user);
        Inquiry savedInquiry = inquiryDao.saveOrUpdate(inquiry);
        return inquiryTransformer.transform(savedInquiry);
    }

    @Transactional(readOnly = true)
    public PaginatedResponseDto getAllInquiries(int page, int size) {
        return inquiryDao.getAll(page, size);
    }

    @Transactional(readOnly = true)
    public InquiryDto getInquiryById(int id) {
        Inquiry inquiry = inquiryDao.getById(id);
        return inquiry != null ? inquiryTransformer.transform(inquiry) : null;
    }

    @Transactional
    public InquiryDto updateInquiry(int id, InquiryDto inquiryDto) {
        Inquiry existingInquiry = inquiryDao.getById(id);
        if (existingInquiry != null) {
            existingInquiry.setAdminReply(inquiryDto.getAdminReply());
            existingInquiry.setStatus(inquiryDto.getStatus());
            existingInquiry.setRepliedAt(LocalDateTime.now());
            existingInquiry.setUpdatedAt(LocalDateTime.now());
            Inquiry updatedInquiry = inquiryDao.saveOrUpdate(existingInquiry);
            return inquiryTransformer.transform(updatedInquiry);
        }
        return null;
    }

    @Transactional
    public void deleteInquiry(int id) {
        Inquiry inquiry = inquiryDao.getById(id);
        if (inquiry != null) {
            inquiry.setIsActive(false);
            inquiryDao.saveOrUpdate(inquiry);
        }
    }
}
